package org.example;

import org.junit.jupiter.api.Test;
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
        var actual = getActualOutput(inputStream, new String[]{});
        var expected = "Enter Text: Number of words: 6";
        assertEquals(expected, actual);
    }

    @Test
    void mainConsoleInputException() {
        String actual = getActualOutput(new InputStream() {
            @Override
            public int read() throws IOException {
                throw new IOException();
            }
        }, new String[]{});
        var expected = "Enter Text: An error occurred.";
        assertEquals(expected, actual);
    }

    private static String getActualOutput(InputStream inputStream, String[] arg) {
        System.setIn(inputStream);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        Main.main(arg);

        String[] lines = outputStream.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 1];
        return actual;
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "/Users/erdemsevilmis/Documents/GitHub/WordCount/mytext.txt"
    })
    void mainArgument(String input) {
        Main.main(new String[]{input});
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "this is not a path"
    })
    void mainArgumentWithWrongPath(String input) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream((input).getBytes());
        var actual = getActualOutput(inputStream, new String[]{input});
        var expected = "File not found.";
        assertEquals(expected, actual);
    }
}