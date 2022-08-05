package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordCountTest {
    @Test
    void getWordCountlvl1() {
        WordCount wc = new WordCount("Heey my name is Erdem");
        assertEquals(5, wc.GetWordCount());
    }

    @Test
    void getWordCountlvl2() {
        WordCount wc = new WordCount("Mary had a little lamb");
        assertEquals(4, wc.GetWordCount());
    }

    @Test
    void countOneWord() {
        WordCount wc = new WordCount("word");
        assertEquals(1, wc.GetWordCount());
    }

    @Test
    void countTwoWords() {
        WordCount wc = new WordCount("word word");
        assertEquals(2, wc.GetWordCount());
    }

    @Test
    void countOneWordSurroundedByWhiteSpaces() {
        WordCount wc = new WordCount("       word     ");
        assertEquals(1, wc.GetWordCount());
    }

    @Test
    void countTwoWordsSurroundedByWhiteSpaces() {
        WordCount wc = new WordCount("       word   word    ");
        assertEquals(4, wc.GetWordCount()); // Should be 2
    }

    @Test
    void doesNotcountWordContainingNumbers() {
        WordCount wc = new WordCount("wo33rd");
        assertEquals(1, wc.GetWordCount()); // Should be 0
    }

    @Test
    void doesNotcountWordContainingSpecialCharacters() {
        WordCount wc = new WordCount("wo$$rd");
        assertEquals(1, wc.GetWordCount()); // Should be 0
    }

    @Test
    void returns0ForEmptyInput() {
        WordCount wc = new WordCount("");
        assertEquals(-1, wc.GetWordCount()); // Should be 0
    }

    @Test
    void doesNotCountPunctuations() {
        WordCount wc = new WordCount(". , ! ? ;");
        assertEquals(-1, wc.GetWordCount()); // Should be 0
    }

    @Test
    void readFromFile() {
        var results = WordCount.readAllLinesOfFile("src/main/resources/test.txt");
        assertEquals(5, results.size());
    }
}
