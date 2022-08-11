package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.lang.String.format;

public class WordCount {
    private final static String STOP_WORDS_PATH = "src/main/resources/stopwords.txt";
    private final List<String> stopWords = new ArrayList<>();
    private final String phrase;
    public int uniqueWords;

    public WordCount(String phrase) {
        this.phrase = phrase;
        var content = readFile(STOP_WORDS_PATH);
        content.forEach((stopWord) -> this.stopWords.add(format(" %s ", stopWord)));
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

    public int getWordCount() {
        var allWords = Arrays.asList(phrase.split(" "));

        var validatedWords = validate(allWords);
        validatedWords.replaceAll(s -> String.format(" %s ", s.trim()));
        var filteredWords = filterStopWords(validatedWords);
        uniqueWords = getUniqueWords(filteredWords);
        return filteredWords.size();
    }

    private int getUniqueWords(List<String> filteredWords) {
        Set<String> uniqueWords = new HashSet<>(filteredWords);
        return uniqueWords.size();
    }

    private List<String> validate(List<String> allWords) {
        var p = Pattern.compile("[^A-Za-z .-]");
        var newAllWords= allWords.stream()
                .filter(word -> !word.isEmpty())
                .filter(word -> {
                    var m = p.matcher(word);
                    return !m.find();
                })
                .collect(Collectors.toList());

        List<String> words = new ArrayList<>();
        newAllWords.forEach(word -> {
            var newWord = (word.replaceAll("[.-]", " ")).trim();
            var parts = newWord.split(" ");
            Collections.addAll(words, parts);
        });
        return words;
    }

    private List<String> filterStopWords(List<String> words) {
        return words.stream().filter(word -> !word.matches(String.join("|", stopWords))).collect(Collectors.toList());
    }
}
