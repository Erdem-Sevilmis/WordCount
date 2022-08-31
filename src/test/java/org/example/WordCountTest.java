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
        WordCount wordCount = new WordCount();
        assertEquals(1, wordCount.countLatinAlphabeticWords(phrase));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Word word word"
    })
    void getWordWithMultipleWords(String phrase) {
        WordCount wordCount = new WordCount();
        assertEquals(3, wordCount.countLatinAlphabeticWords(phrase));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "123 123 word 123",
            "1Word 2word word 3word",
            "Word1 word2 word3 word",
            "Word w1ord wo2rd word3"
    })
    void getWordWithNumbers(String phrase) {
        WordCount wordCount = new WordCount();
        assertEquals(1, wordCount.countLatinAlphabeticWords(phrase));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Word word word       ",
            "        Word word word",
            "Word      word     word     "
    })
    void getWordWithWhitespaces(String phrase) {
        WordCount wordCount = new WordCount();
        assertEquals(3, wordCount.countLatinAlphabeticWords(phrase));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "123    123 word   123",
            "1Word     2word word    3word     ",
            "   Word1 word2    word3 word",
            "Word   w1ord    wo2rd word3    "
    })
    void getWordWithWhitespacesAndNumbers(String phrase) {
        WordCount wordCount = new WordCount();
        assertEquals(1, wordCount.countLatinAlphabeticWords(phrase));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Wo!rd word& word",
            "!Word word word$",
            "word W§§ord",
            "W$ord §$ word"
    })
    void getWordWithSpecialCharacters(String phrase) {
        WordCount wordCount = new WordCount();
        assertEquals(1, wordCount.countLatinAlphabeticWords(phrase));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "     Wo2rd     wod& word    ",
            "   !Word word word4",
            "   wo1r§d    word W§§ord         ",
            "W$ord         §$ word         "
    })
    void getWordWithSpecialCharactersAndWhitespacesAndNumbers(String phrase) {
        WordCount wordCount = new WordCount();
        assertEquals(1, wordCount.countLatinAlphabeticWords(phrase));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "off a the on",
    })
    void getWordWithStopWords(String phrase) {
        WordCount wordCount = new WordCount();
        assertEquals(0, wordCount.countLatinAlphabeticWords(phrase));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Humpty-Dumpty sat on a wall. Humpty-Dumpty had a great fall.",
    })
    void getUniqueWordCount(String phrase) {
        WordCount wordCount = new WordCount();
        assertEquals(9,wordCount.countLatinAlphabeticWords(phrase));
        assertEquals(7, wordCount.uniqueWords);
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