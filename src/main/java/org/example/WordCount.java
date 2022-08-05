package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class WordCount {
    private String Phrase;
    private String stopWordsPath = "src/main/resources/stopwords.txt";
    private boolean stopWord = false;
    List<String> stopWords = new ArrayList<>();

    public WordCount(String phrase, boolean stopWords) {
        Phrase = phrase.trim();
        stopWord = stopWords;
        if (stopWords) {
            var content = ReadFile(stopWordsPath);
            content.stream().forEach((stopWord) -> {
                this.stopWords.add(" " + stopWord + " ");
            });
        }
    }

    public static List<String> ReadFile(String path) {
        List<String> content = new ArrayList<>();
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                content.add(line);
            }
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return content;
    }

    public int GetWordCount() {
        if (!Valid()) return -1;

        var parts = Phrase.split(" ");

        if (!stopWord) return parts.length;

        var stopWordsCount = AmountOfStopWords(parts);
        return parts.length - stopWordsCount;
    }

    private int AmountOfStopWords(String[] parts) {
        int count = 0;
        Pattern pattern = Pattern.compile(stopWords.stream().collect(Collectors.joining("|")));
        Matcher matcher = pattern.matcher(Phrase);

        while (matcher.find()) {
            count++;
        }
        return count;
    }

    private boolean Valid() {
        Pattern pattern = Pattern.compile("[a-zA-Z]");
        Matcher matcher = pattern.matcher(Phrase);

        if (matcher.find()) return true;
        return false;
    }
}
