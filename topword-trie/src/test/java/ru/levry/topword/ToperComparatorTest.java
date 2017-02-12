package ru.levry.topword;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * @author levry
 */
public class ToperComparatorTest {

    @Test
    public void shouldBeGreaterByTop() throws Exception {
        TopWord w1 = new TopWord("al", 2);
        TopWord w2 = new TopWord("al", 3);

        int compare = Toper.TOP_COMPARATOR.compare(w1, w2);

        assertThat(compare > 0, is(true));
    }

    @Test
    public void shouldBeGreaterByWordIfEqualsTop() throws Exception {
        TopWord w1 = new TopWord("first", 3);
        TopWord w2 = new TopWord("second", 3);

        int compare = Toper.TOP_COMPARATOR.compare(w1, w2);

        assertThat(compare < 0, is(true));
    }
}