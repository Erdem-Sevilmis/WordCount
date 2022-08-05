package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class WordCount {
    private static final int INVALID_INPUT = -1;
    private static final String STOP_WORDS_PATH = "src/main/resources/stopwords.txt";
    private final String Phrase;
    private final List<String> stopWords = new ArrayList<>();

    public WordCount(String phrase) {
        Objects.requireNonNull(phrase, "user input must not be null");
        Phrase = phrase.trim();

        var content = readFile(STOP_WORDS_PATH);
        content.forEach((stopWord) -> this.stopWords.add(String.format(" %s ", stopWord)));
    }

    public static List<String> readFile(String path) {
        try {
            return Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public int GetWordCount() {
        if (Valid()) {
            var parts = Phrase.split(" ");
            var stopWordsCount = AmountOfStopWords();
            return parts.length - stopWordsCount;
        } else {
            return INVALID_INPUT;
        }
    }

    private int AmountOfStopWords() {
        var pattern = Pattern.compile(String.join("|", stopWords));
        var matcher = pattern.matcher(Phrase);

        var count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    private boolean Valid() {
        var pattern = Pattern.compile("[a-zA-Z]");
        var matcher = pattern.matcher(Phrase);

        return matcher.find();
    }
}
