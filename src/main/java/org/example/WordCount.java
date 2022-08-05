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
    private static final String STOP_WORDS_FILE_PATH = "src/main/resources/stopwords.txt";
    private final String phrase;
    private final List<String> stopWords = new ArrayList<>();

    public WordCount(String phrase) {
        Objects.requireNonNull(phrase, "user input must not be null");
        this.phrase = phrase.trim();

        var content = readAllLinesOfFile(STOP_WORDS_FILE_PATH);
        content.forEach((stopWord) -> this.stopWords.add(String.format(" %s ", stopWord)));
    }

    public static List<String> readAllLinesOfFile(String path) {
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
            var parts = phrase.split(" ");
            var stopWordsCount = stopWordsCount();
            return parts.length - stopWordsCount;
        } else {
            return INVALID_INPUT;
        }
    }

    private int stopWordsCount() {
        var pattern = Pattern.compile(String.join("|", stopWords));
        var matcher = pattern.matcher(phrase);

        var count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    private boolean Valid() {
        var pattern = Pattern.compile("[a-zA-Z]");
        var matcher = pattern.matcher(phrase);

        return matcher.find();
    }
}
