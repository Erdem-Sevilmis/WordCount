package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @ParameterizedTest
    @ValueSource(strings = {
            "Das ist ein test run huhuehu"
    })
    void mainConsoleInput(String phrase) {

        ByteArrayInputStream inputStream = new ByteArrayInputStream((phrase).getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        Main.main(new String[]{});

        String[] lines = outputStream.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 1];
        var expected = "Enter Text: Number of words: 6";
        assertEquals(expected, actual);
    }

    /*@ParameterizedTest
    @ValueSource(strings = {
            "Das ist ein test run huhuehu"
    })
    void mainConsoleInputException(String phrase) {


    }*/

    @ParameterizedTest
    @ValueSource(strings = {
            "/Users/erdemsevilmis/Documents/GitHub/WordCount/mytext.txt"
    })
    void mainArgument(String input) {
        Main.main(new String[]{input});
    }
}