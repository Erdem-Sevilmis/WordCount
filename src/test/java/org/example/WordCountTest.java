package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordCountTest {
    @Test
    void getWordCountlvl1() {
        WordCount wc = new WordCount("Heey my name is Erdem");
        assertEquals(5, wc.getWordCount());
    }

    @Test
    void getWordCountlvl2() {
        WordCount wc = new WordCount("Mary had a little lamb");
        assertEquals(4, wc.getWordCount());
    }

    @Test
    void readFromFile() {
        var results = WordCount.readFile("src/main/resources/test.txt");
        assertEquals(5, results.size());
    }
}