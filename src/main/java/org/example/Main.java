package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    private static final int STOP_WORDS_INDEX = 0;

    public static void main(String[] args) {
        String phrase = null;

        if (args.length == 0) {
            System.out.print("Enter Text: ");
            try {
                var reader = new BufferedReader(new InputStreamReader(System.in));
                phrase = reader.readLine();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } else if (Files.exists(Path.of(args[STOP_WORDS_INDEX]))) {
            var content = WordCount.readFile(args[STOP_WORDS_INDEX]);
            phrase = String.join(" ", content);
        }

        var wc = new WordCount(phrase);
        var result = wc.getWordCount();
        System.out.println("Number of words: " + result);
    }
}