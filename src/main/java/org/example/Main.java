package org.example;

import java.util.Scanner;

public class Main {

    private static final int INVALID_INPUT = -1;
    private static final int INDEX_OF_USER_INPUT_FILE = 0;
    private static final String RESOURCES_DIRECTORY_PATH = "src/main/resources/";

    public static void main(String[] args) {
        String userInput;

        if (args.length == 0) {
            System.out.print("Enter Text: ");
            try (Scanner in = new Scanner(System.in)) {
                userInput = in.nextLine();
            }
        } else {
            var userInputFileContent = WordCount.readAllLinesOfFile(String.format("%s%s",
                    RESOURCES_DIRECTORY_PATH,
                    args[INDEX_OF_USER_INPUT_FILE]));
            userInput = String.join(" ", userInputFileContent);
        }

        var wc = new WordCount(userInput);
        var wordCount = wc.GetWordCount();
        if (wordCount == INVALID_INPUT) {
            System.out.println("Phrase is not valid!");
        } else {
            System.out.println("Number of words: " + wordCount);
        }
    }
}
