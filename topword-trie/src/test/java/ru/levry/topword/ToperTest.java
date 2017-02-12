package ru.levry.topword;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.junit.Test;

/**
 * @author levry
 */
public class ToperTest {

    @Test
    public void shouldBeReturnTopWords() throws Exception {
        Collection<TopWord> tops = Arrays.asList(
                new TopWord("awokado", 2),
                new TopWord("orange", 100),
                new TopWord("lingerie", 22),
                new TopWord("linear", 300),
                new TopWord("last", 10),
                new TopWord("lingerie2", 20),
                new TopWord("xenail", 1000)
        );
        Toper toper = new Toper(tops);

        List<String> words = toper.most("l", 10);

        assertThat(words, notNullValue());
        assertThat(words, is(Arrays.asList("linear", "lingerie", "lingerie2", "last")));
    }

    @Test
    public void shouldBeRangeOfSize() throws Exception {
        Collection<TopWord> tops = Arrays.asList(
                new TopWord("lingerie", 22),
                new TopWord("linear", 300),
                new TopWord("last", 10),
                new TopWord("lingerie2", 20)
        );
        Toper toper = new Toper(tops);
        List<String> words = toper.most("l", 2);

        assertThat(words, notNullValue());
        assertThat(words, is(Arrays.asList("linear", "lingerie")));
    }

    @Test
    public void shouldBeRangeIfWordsMore() throws Exception {
        Collection<TopWord> tops = Arrays.asList(
                new TopWord("lingerie", 22),
                new TopWord("linear", 300),
                new TopWord("last", 10),
                new TopWord("lingerie2", 20)
        );
        Toper toper = new Toper(tops);
        List<String> words = toper.most("l", 6);

        assertThat(words, notNullValue());
        assertThat(words, is(Arrays.asList("linear", "lingerie", "lingerie2", "last")));
    }

    @Test
    public void shouldBeReturnEmptyIfNoWords() throws Exception {
        Toper toper = new Toper(Collections.emptyList());

        List<String> words = toper.most("e", 10);

        assertThat(words, notNullValue());
        assertThat(words.isEmpty(), is(true));
    }

    @Test
    public void shouldBePutAllWords() throws Exception {
        Trie<Integer> trie = new Trie<>();
        Toper toper = new Toper(trie);

        toper.putWords(Arrays.asList(new TopWord("key1", 1), new TopWord("key2", 2)));

        assertThat(trie.size(), is(2));
        assertThat(trie.get("key1"), is(1));
        assertThat(trie.get("key2"), is(2));
    }

}