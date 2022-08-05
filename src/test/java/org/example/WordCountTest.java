package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordCountTest {
    @Test
    void getWordCountlvl1() {
        WordCount wc = new WordCount("Heey my name is Erdem", false);
        assertEquals(5, wc.GetWordCount());
    }

    @Test
    void getWordCountlvl2() {
        WordCount wc = new WordCount("Mary had a little lamb", true);
        assertEquals(4, wc.GetWordCount());
    }

    @Test
    void countOneWord() {
        WordCount wc = new WordCount("word", false);
        assertEquals(1, wc.GetWordCount());
    }

    @Test
    void readFromFile() {
        var results = WordCount.ReadFile("src/main/resources/test.txt");
        assertEquals(5, results.size());
    }
}
