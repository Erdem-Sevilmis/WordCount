package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.lang.String.format;
public class WordCount {
    /**
     * Stop words are stored in a file named stopwords.txt which is bundled with the
     * application and loaded from classpath. Stop words are: "on", "the", "off", "a" which are defined in the
     * requirements.
     */
    private final List<String> stopWords = new ArrayList<>();

    private final static String STOP_WORDS_PATH = "src/main/resources/stopwords.txt";
    public int uniqueWords;

    /**
     * Uses internally {@link WordCount#readFile(String)} method to create stopWords. For further information about stopWords read {@link WordCount#stopWords}.
     *
     * @throws IOException       if an I/O error occurs reading from the file or malformed or unmappable byte sequence is read
     * @throws SecurityException In the case of the default provider, and a security manager is installed, the {@link SecurityManager#checkRead(String) checkRead} method is invoked to check read access to the file.
     */
    public WordCount() {
        var content = readFile(STOP_WORDS_PATH);
        content.forEach((stopWord) -> this.stopWords.add(format(" %s ", stopWord)));
    }

    /**
     * Read all lines from a file and save them into a {@code Liat}.
     * Uses internally {@link Files#readAllLines(Path)}
     *
     * @param path The absolut path to the file.
     * @return the lines from the file as a {@code List}; whether the {@code
     * List} is modifiable or not is implementation dependent and
     * therefore not specified
     *
     * @throws IOException       if an I/O error occurs reading from the file or malformed or unmappable byte sequence is read
     * @throws SecurityException In the case of the default provider, and a security manager is installed, the {@link SecurityManager#checkRead(String) checkRead} method is invoked to check read access to the file.
     */

    public static List<String> readFile(String path) {
        Objects.requireNonNull(path, "Path must not be null.");

        List<String> result;
        try {
            result = Files.readAllLines(Paths.get(path));
        } catch (IOException | SecurityException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * Counts words in text which are separated by white-space(s) and only contains letters from the latin alphabet
     * (A-Z,a-Z). Otherwise, words are not counted.
     * <p>
     * {@link WordCount#stopWords} are also not counted. If the stop words file can't be loaded, stop words are ignored.
     * <br /><br />
     * Examples:
     * <pre>
     *    "word" => 1
     *    "word word" => 2
     *    "word wo3rd" => 1 // "3" not in A-Z or a-z
     *    "word, word" => 1 // "," not in A-Z or a-z
     *    "word on" => 1 // "on" is a stop word
     * </pre>
     * @param text containing words which will be counted; must not be null
     * @return word count of words which are separted by white-space(s) and only contain letters from the latin alphabet and are not stop words.
     * @throws NullPointerException if text is null
     */
    public int wordCount(String text) {
        Objects.requireNonNull(text);

        var allWords = Arrays.asList(text.split(" "));

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
        var newAllWords = allWords.stream()
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
        return words.stream()
                .filter(word -> !word.matches(String.join("|", stopWords)))
                .collect(Collectors.toList());
    }
}
