package ru.levry.topword;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

/**
 * @author levry
 */
public class TrieTest {

    @Test
    public void keys() throws Exception {

        Trie<Integer> trie = new Trie<>();
        trie.put("linergy", 40);
        trie.put("awokado", 14);
        trie.put("last", 20);
        trie.put("orange", 1);
        trie.put("linear", 330);
        trie.put("xenial", 20);

        Iterable<String> actual = trie.keys();

        assertThat(actual, is(Arrays.asList("awokado", "last", "linear", "linergy", "orange", "xenial")));
    }

    @Test
    public void collectWithPrefix() throws Exception {
        Trie<Integer> trie = new Trie<>();
        trie.put("lingerie", 40);
        trie.put("awokado", 14);
        trie.put("last", 20);
        trie.put("orange", 1);
        trie.put("linear", 330);
        trie.put("xenial", 20);

        List<Pair> list = new ArrayList<>();
        trie.collectWithPrefix("l", (s, v) -> list.add(new Pair(s, v)));

        assertThat(list, is(Arrays.asList(new Pair("last", 20), new Pair("linear", 330), new Pair("lingerie", 40))));
    }

    @Test
    public void putValue() throws Exception {
        Trie<Integer> trie = new Trie<>();

        trie.put("list", 30);

        assertThat(trie.get("list"), is(30));
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowIfKeyIsNull() throws Exception {
        Trie<Integer> trie = new Trie<>();

        trie.put(null, 30);

        fail("Key is not nullable");
    }

    @Test
    public void shouldReturnValue() throws Exception {
        Trie<Integer> trie = new Trie<>();
        trie.put("key", 20);

        Integer value = trie.get("key");

        assertThat(value, is(20));
    }

    @Test
    public void shouldReturnNullIfNotContains() throws Exception {
        Trie<Integer> trie = new Trie<>();

        Integer value = trie.get("key");

        assertThat(value, nullValue());
    }

    @Test
    public void shouldBeReturnTrueIfContains() throws Exception {
        Trie<Integer> trie = new Trie<>();
        trie.put("key", 2);

        boolean contains = trie.contains("key");

        assertThat(contains, is(true));
    }

    @Test
    public void shouldBeReturnFalseIfNotContains() throws Exception {
        Trie<Integer> trie = new Trie<>();

        boolean contains = trie.contains("key");

        assertThat(contains, is(false));
    }

    private static class Pair {
        private final String key;
        private final Integer value;

        Pair(String key, Integer value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return Objects.equals(key, pair.key) &&
                    Objects.equals(value, pair.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }
}