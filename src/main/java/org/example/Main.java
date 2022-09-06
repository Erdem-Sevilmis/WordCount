package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    private static final int WORDS_FILE_INDEX = 0;

    public static void main(String[] args) {
        String phrase;

        if (args.length == 0) {
            System.out.print("Enter Text: ");
            try {
                var reader = new BufferedReader(new InputStreamReader(System.in));
                phrase = reader.readLine();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                return;
            }
        } else if (Files.exists(Path.of(args[WORDS_FILE_INDEX]))) {
            var content = WordCounter.readFile(args[WORDS_FILE_INDEX]);
            phrase = String.join(" ", content);
        } else {
            System.out.println("File not found.");
            return;
        }

        var wordCount = new WordCounter();
        var result = wordCount.countLatinAlphabeticWords(phrase);
        System.out.printf("Number of words: %d, unique: %d%n", result, wordCount.uniqueWords);
    }

}