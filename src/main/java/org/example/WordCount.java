package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

import static java.util.Arrays.asList;

public class WordCount {
    private static final int INVALID_USER_INPUT = -1;
    private static final String STOP_WORDS_FILE_PATH = "src/main/resources/stopwords.txt";
    private final String userInput;
    private final List<String> stopWords = new ArrayList<>();

    public WordCount(String userInput) {
        Objects.requireNonNull(userInput, "user input must not be null");
        this.userInput = userInput.trim();
        this.stopWords.addAll(readAllLinesOfFile(STOP_WORDS_FILE_PATH));
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
            var words = userInput.split(" ");
            var stopWordsCount = stopWordsCount(words);
            return words.length - stopWordsCount;
        } else {
            return INVALID_USER_INPUT;
        }
    }

    private int stopWordsCount(String[] parts) {
        return stopWords.stream()
                .map(sw -> Collections.frequency(asList(parts), sw))
                .mapToInt(nr -> nr)
                .sum();
    }

    private boolean Valid() {
        var pattern = Pattern.compile("[a-zA-Z]");
        var matcher = pattern.matcher(userInput);

        return matcher.find();
    }
}
