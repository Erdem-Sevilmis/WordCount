package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String phrase = null;

        if (args.length == 0) {
            System.out.print("Enter Text: ");
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                phrase = reader.readLine();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } else if(Files.exists(Path.of(args[0])) ){
            var content = WordCount.readFile(args[0]);
            phrase = content.stream().collect(Collectors.joining(" "));
        }

        WordCount wc = new WordCount(phrase, true);
        var result = wc.getWordCount();
        System.out.println("Number of words: " + result);

    }
}