package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.lang.String.format;

public class WordCount {
    private final static String STOP_WORDS_PATH = "src/main/resources/stopwords.txt";
    private final List<String> stopWords = new ArrayList<>();
    private final String phrase;

    public WordCount(String phrase) {
        this.phrase = phrase;
        var content = readFile(STOP_WORDS_PATH);
        content.forEach((stopWord) -> this.stopWords.add(format(" %s ", stopWord)));
    }

    public static List<String> readFile(String path) {
        Objects.requireNonNull(path, "Path must not be null.");

        List<String> content = new ArrayList<>();
        try {
            //ToDo: FileRead with java api
            //  Files.line(Path.get(...))
            //  Files.readAllLine(Path.get(...))
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                content.add(line.trim());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return content;
    }

    public int getWordCount() {
        List<String> allWords = Arrays.asList(phrase.split(" "));

        var filteredWords = validate(allWords);
        filteredWords.replaceAll(s -> String.format(" %s ", s.trim()));
        var stopWordsCount = amountOfStopWords(filteredWords);
        return filteredWords.size() - stopWordsCount;
    }

    private List<String> validate(List<String> allWords) {
        var p = Pattern.compile("[^A-Za-z ]");
        return allWords.stream().filter(word -> {
            if (word.isEmpty()) {
                return false;
            }
            var m = p.matcher(word);
            return !m.find();
        }).collect(Collectors.toList());
    }

    private int amountOfStopWords(List<String> words) {
        //ToDo: using var
        var p = Pattern.compile(String.join("|", stopWords));
        var matcher = p.matcher(String.join("", words));

        var count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }
}
