package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.stream.Collectors;

public class Main {

    private static final int INVALID_INPUT = -1;

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
        } else {
            var content = WordCount.ReadFile("src/main/resources/" + args[0]);
            phrase = content.stream().collect(Collectors.joining(" "));
        }

        WordCount wc = new WordCount(phrase, true);
        var result = wc.GetWordCount();
        if (result == INVALID_INPUT) {
            System.out.println("Phrase is not valid!");
        } else {
            System.out.println("Number of words: " + result);
        }
    }
}
