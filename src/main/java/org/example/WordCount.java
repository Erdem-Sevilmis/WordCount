package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class WordCount {
    private static final int INVALID_INPUT = -1;
    private final String Phrase;
    private final String stopWordsPath = "src/main/resources/stopwords.txt";
    List<String> stopWords = new ArrayList<>();

    public WordCount(String phrase) {
        Phrase = phrase.trim();
        var content = ReadFile(stopWordsPath);
        content.forEach((stopWord) -> this.stopWords.add(" " + stopWord + " "));
    }

    public static List<String> ReadFile(String path) {
        List<String> content = new ArrayList<>();
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                content.add(line);
            }
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return content;
    }

    public int GetWordCount() {
        if (Valid()) {
            var parts = Phrase.split(" ");
            var stopWordsCount = AmountOfStopWords(parts);
            return parts.length - stopWordsCount;
        } else {
            return INVALID_INPUT;
        }
    }

    private int AmountOfStopWords(String[] parts) {
        int count = 0;
        Pattern pattern = Pattern.compile(String.join("|", stopWords));
        Matcher matcher = pattern.matcher(Phrase);

        while (matcher.find()) {
            count++;
        }
        return count;
    }

    private boolean Valid() {
        Pattern pattern = Pattern.compile("[a-zA-Z]");
        Matcher matcher = pattern.matcher(Phrase);

        return matcher.find();
    }
}
