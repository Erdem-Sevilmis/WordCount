package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordCounterTest {
    @Test
    void getWordCountlvl1() {
        WordCounter wc = new WordCounter("Heey my name is Erdem");
        assertEquals(5, wc.getWordCount());
    }

    @Test
    void getWordCountlvl2() {
        WordCounter wc = new WordCounter("Mary had a little lamb");
        assertEquals(4, wc.getWordCount());
    }

    @Test
    void countOneWord() {
        WordCounter wc = new WordCounter("word");
        assertEquals(1, wc.getWordCount());
    }

    @Test
    void countTwoWords() {
        WordCounter wc = new WordCounter("word word");
        assertEquals(2, wc.getWordCount());
    }

    @Test
    void countOneWordSurroundedByWhiteSpaces() {
        WordCounter wc = new WordCounter("       word     ");
        assertEquals(1, wc.getWordCount());
    }

    @Test
    void countTwoWordsSurroundedByWhiteSpaces() {
        WordCounter wc = new WordCounter("       word   word    ");
        assertEquals(4, wc.getWordCount()); // Should be 2
    }

    @Test
    void doesNotcountWordContainingNumbers() {
        WordCounter wc = new WordCounter("wo33rd");
        assertEquals(1, wc.getWordCount()); // Should be 0
    }

    @Test
    void doesNotcountWordContainingSpecialCharacters() {
        WordCounter wc = new WordCounter("wo$$rd");
        assertEquals(1, wc.getWordCount()); // Should be 0
    }

    @Test
    void returns0ForEmptyInput() {
        WordCounter wc = new WordCounter("");
        assertEquals(-1, wc.getWordCount()); // Should be 0
    }

    @Test
    void doesNotCountPunctuations() {
        WordCounter wc = new WordCounter(". , ! ? ;");
        assertEquals(-1, wc.getWordCount()); // Should be 0
    }

    @Test
    void readFromFile() {
        var results = WordCounter.readAllLinesOfFile("src/main/resources/test.txt");
        assertEquals(5, results.size());
    }
}
