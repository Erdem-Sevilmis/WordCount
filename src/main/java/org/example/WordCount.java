package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
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
        for (int i = 0; i < filteredWords.size(); i++) {
            var trimedWord = filteredWords.get(i).trim();
            filteredWords.set(i, " " + trimedWord + " ");
        }

        if (!stopWord) return filteredWords.size();
        var stopWordsCount = amountOfStopWords(filteredWords);
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

    private int amountOfStopWords(List<String> words) {
        int count = 0;
        Pattern p = Pattern.compile(String.join("|", stopWords));
        Matcher matcher = p.matcher(String.join("",words));

        while (matcher.find()) {
            count++;
        }
        return count;
    }
}
