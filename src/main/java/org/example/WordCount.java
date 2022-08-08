package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class WordCount {
    private boolean stopWord;
    private final String stopWordsPath = "src/main/resources/stopwords.txt";
    private final List<String> stopWords = new ArrayList<>();
    private final String regEx = "[^A-Za-z ]";
    private final String phrase;

    public WordCount(String phrase, boolean stopWords) {
        this.phrase = phrase;
        stopWord = stopWords;
        if (stopWords) {
            var content = readFile(stopWordsPath);
            content.forEach((stopWord) -> this.stopWords.add(" " + stopWord + " "));
        }
    }

    public static List<String> readFile(String path) {
        List<String> content = new ArrayList<>();
        try {
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

        if (!stopWord) return filteredWords.size();
        var stopWordsCount = amountOfStopWords();
        return filteredWords.size() - stopWordsCount;
    }

    private List<String> validate(List<String> allWords) {
        Pattern p = Pattern.compile(regEx);
        return allWords.stream().filter(word -> {
            if (word.isEmpty()) return false;

            Matcher m = p.matcher(word);
            return !m.find();
        }).collect(Collectors.toList());
    }

    private int amountOfStopWords() {
        int count = 0;
        Pattern p = Pattern.compile(String.join("|", stopWords));
        Matcher matcher = p.matcher(phrase);

        while (matcher.find()) {count++;}
        return count;
    }
}
