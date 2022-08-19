package org.example;

import java.util.Objects;

public class WordCountResult {
    private final int regularWordCount;
    private final int uniqueWords;

    public WordCountResult(int regularWordCount, int uniqueWords) {
        this.regularWordCount = regularWordCount;
        this.uniqueWords = uniqueWords;
    }

    public int getRegularWordCount() {
        return regularWordCount;
    }

    public int getUniqueWords() {
        return uniqueWords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WordCountResult that = (WordCountResult) o;
        return regularWordCount == that.regularWordCount && uniqueWords == that.uniqueWords;
    }

    @Override
    public int hashCode() {
        return Objects.hash(regularWordCount, uniqueWords);
    }

    @Override
    public String toString() {
        return "WordCountResult{" +
            "regularWordCount=" + regularWordCount +
            ", uniqueWords=" + uniqueWords +
            '}';
    }
}
