package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class WordCount {
    private static final String STOP_WORDS_PATH = "src/main/resources/stopwords.txt";
    private final List<String> stopWords = new ArrayList<>();
    private final String phrase;

    public WordCount(String phrase) {
        this.phrase = phrase;
        stopWords.addAll(readFile(STOP_WORDS_PATH));
    }

    public static List<String> readFile(String path) {
        Objects.requireNonNull(path, "Path must not be null.");

        List<String> result;
        try {
            result = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public WordCountResult getWordCount() {
        var allWords = Arrays.asList(phrase.split(" "));
        var words = withoutStopWords(parseWords(allWords));
        return new WordCountResult(words.size(), new HashSet<>(words).size());
    }

    private List<String> parseWords(List<String> allWords) {
        /*
          Examples
          Matching: "word", "word-word", "word.", " "
          Not matching: ".", "-", "1", "wo4rd",
         */
        var regex = "[A-Za-z]+(-[A-Za-z]+)?\\.?";
        var p = Pattern.compile(regex);
        return allWords.stream()
            .filter(word -> !word.isEmpty())
            .filter(word -> p.matcher(word).matches())
            .flatMap(word -> stream(word.split("-")))
            .collect(Collectors.toList());
    }

    private List<String> withoutStopWords(List<String> words) {
        return words.stream()
            .filter(word -> !word.matches(String.join("|", stopWords)))
            .collect(Collectors.toList());
    }
}
