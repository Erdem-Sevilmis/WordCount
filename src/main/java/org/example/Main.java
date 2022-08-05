package org.example;

import java.util.Scanner;

public class Main {

    private static final int INVALID_INPUT = -1;
    private static final int INDEX_OF_USER_INPUT_FILE = 0;
    private static final String RESOURCES_DIRECTORY_PATH = "src/main/resources/";

    public static void main(String[] args) {
        String phrase = null;

        if (args.length == 0) {
            System.out.print("Enter Text: ");
            try (Scanner in = new Scanner(System.in)) {
                phrase = in.nextLine();
            }
        } else {
            var content = WordCount.readFile(RESOURCES_DIRECTORY_PATH + args[INDEX_OF_USER_INPUT_FILE]);
            phrase = String.join(" ", content);
        }

        var wc = new WordCount(phrase);
        var result = wc.GetWordCount();
        if (result == INVALID_INPUT) {
            System.out.println("Phrase is not valid!");
        } else {
            System.out.println("Number of words: " + result);
        }
    }
}
