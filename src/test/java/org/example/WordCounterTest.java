package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class WordCounterTest {
    @ParameterizedTest
    @ValueSource(strings = {
            "Word"
    })
    void getWordWithSingleWord(String phrase) {
        WordCounter wordCounter = new WordCounter();
        assertEquals(1, wordCounter.countLatinAlphabeticWords(phrase));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Word word word"
    })
    void getWordWithMultipleWords(String phrase) {
        WordCounter wordCounter = new WordCounter();
        assertEquals(3, wordCounter.countLatinAlphabeticWords(phrase));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "123 123 word 123",
            "1Word 2word word 3word",
            "Word1 word2 word3 word",
            "Word w1ord wo2rd word3"
    })
    void getWordWithNumbers(String phrase) {
        WordCounter wordCounter = new WordCounter();
        assertEquals(1, wordCounter.countLatinAlphabeticWords(phrase));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Word word word       ",
            "        Word word word",
            "Word      word     word     "
    })
    void getWordWithWhitespaces(String phrase) {
        WordCounter wordCounter = new WordCounter();
        assertEquals(3, wordCounter.countLatinAlphabeticWords(phrase));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "123    123 word   123",
            "1Word     2word word    3word     ",
            "   Word1 word2    word3 word",
            "Word   w1ord    wo2rd word3    "
    })
    void getWordWithWhitespacesAndNumbers(String phrase) {
        WordCounter wordCounter = new WordCounter();
        assertEquals(1, wordCounter.countLatinAlphabeticWords(phrase));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Wo!rd word& word",
            "!Word word word$",
            "word W§§ord",
            "W$ord §$ word"
    })
    void getWordWithSpecialCharacters(String phrase) {
        WordCounter wordCounter = new WordCounter();
        assertEquals(1, wordCounter.countLatinAlphabeticWords(phrase));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "     Wo2rd     wod& word    ",
            "   !Word word word4",
            "   wo1r§d    word W§§ord         ",
            "W$ord         §$ word         "
    })
    void getWordWithSpecialCharactersAndWhitespacesAndNumbers(String phrase) {
        WordCounter wordCounter = new WordCounter();
        assertEquals(1, wordCounter.countLatinAlphabeticWords(phrase));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "off a the on",
    })
    void getWordWithStopWords(String phrase) {
        WordCounter wordCounter = new WordCounter();
        assertEquals(0, wordCounter.countLatinAlphabeticWords(phrase));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Humpty-Dumpty sat on a wall. Humpty-Dumpty had a great fall.",
    })
    void getUniqueWordCount(String phrase) {
        WordCounter wordCounter = new WordCounter();
        assertEquals(7, wordCounter.countLatinAlphabeticWords(phrase));
        assertEquals(6, wordCounter.uniqueWords);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "src/main/resources/test.txt"
    })
    void readFromFile(String path) {
        var results = WordCounter.readFile(path);
        assertEquals(5, results.size());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "ASD/ASD/"
    })
    void readFromFileWithInvalidPath(String path) {
        assertThrows(RuntimeException.class, () -> WordCounter.readFile(path));
    }

    @Test
    void readFromFileWithNULLPath() {
        assertThrows(NullPointerException.class, () -> WordCounter.readFile(null));
    }
}