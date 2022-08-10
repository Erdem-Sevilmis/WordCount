package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @ParameterizedTest
    @ValueSource(strings = {
            "/Users/erdemsevilmis/Documents/GitHub/WordCount/mytext.txt"
    })
    void getWordWithSingleWord(String input) {
        //Main.main(new String[]{input});
    }
}