package ru.levry.topword;

import java.util.Objects;

/**
 * @author levry
 */
public class TopWord {

    final String word;
    final Integer top;

    public TopWord(String word, Integer top) {
        this.word = word;
        this.top = top;
    }

    String getWord() {
        return word;
    }

    Integer getTop() {
        return top;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TopWord w = (TopWord) o;
        return Objects.equals(word, w.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word);
    }

    @Override
    public String toString() {
        return "TopWord{" +
                "word='" + word + '\'' +
                ", top=" + top +
                '}';
    }
}
