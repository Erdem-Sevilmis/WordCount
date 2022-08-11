package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class WordCountTest {
    @ParameterizedTest
    @ValueSource(strings = {
            "Word"
    })
    void getWordWithSingleWord(String phrase) {
        WordCount wc = new WordCount(phrase);
        assertEquals(1, wc.getWordCount());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Word word word"
    })
    void getWordWithMultipleWords(String phrase) {
        WordCount wc = new WordCount(phrase);
        assertEquals(3, wc.getWordCount());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "123 123 word 123",
            "1Word 2word word 3word",
            "Word1 word2 word3 word",
            "Word w1ord wo2rd word3"
    })
    void getWordWithNumbers(String phrase) {
        WordCount wc = new WordCount(phrase);
        assertEquals(1, wc.getWordCount());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Word word word       ",
            "        Word word word",
            "Word      word     word     "
    })
    void getWordWithWhitespaces(String phrase) {
        WordCount wc = new WordCount(phrase);
        assertEquals(3, wc.getWordCount());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "123    123 word   123",
            "1Word     2word word    3word     ",
            "   Word1 word2    word3 word",
            "Word   w1ord    wo2rd word3    "
    })
    void getWordWithWhitespacesAndNumbers(String phrase) {
        WordCount wc = new WordCount(phrase);
        assertEquals(1, wc.getWordCount());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Wo!rd word& word",
            "!Word word word$",
            "word W§§ord",
            "W$ord §$ word"
    })
    void getWordWithSpecialCharacters(String phrase) {
        WordCount wc = new WordCount(phrase);
        assertEquals(1, wc.getWordCount());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "     Wo2rd     wod& word    ",
            "   !Word word word4",
            "   wo1r§d    word W§§ord         ",
            "W$ord         §$ word         "
    })
    void getWordWithSpecialCharactersAndWhitespacesAndNumbers(String phrase) {
        WordCount wc = new WordCount(phrase);
        assertEquals(1, wc.getWordCount());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "off a the on",
    })
    void getWordWithStopWords(String phrase) {
        WordCount wc = new WordCount(phrase);
        assertEquals(0, wc.getWordCount());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Humpty-Dumpty sat on a wall. Humpty-Dumpty had a great fall.",
    })
    void getUniqueWordCount(String phrase) {
        WordCount wc = new WordCount(phrase);
        assertEquals(9,wc.getWordCount());
        assertEquals(7, wc.uniqueWords);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "src/main/resources/test.txt"
    })
    void readFromFile(String path) {
        var results = WordCount.readFile(path);
        assertEquals(5, results.size());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "ASD/ASD/"
    })
    void readFromFileWithInvalidPath(String path) {
        assertThrows(RuntimeException.class, () -> WordCount.readFile(path));
    }

    @Test
    void readFromFileWithNULLPath() {
        assertThrows(NullPointerException.class, () -> WordCount.readFile(null));
    }
}