package ru.levry.topword.support;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import ru.levry.topword.TopWord;

/**
 * @author levry
 */
public class WordReaderTest {

    @Test
    public void read() throws Exception {
        WordReader reader = new WordReader(getFile("test.txt"));

        Collection<TopWord> words = reader.read();

        assertThat(words, notNullValue());
        assertThat(words, is(Arrays.asList(
                new TopWord("awokado", 2),
                new TopWord("orange", 100),
                new TopWord("lingerie", 22),
                new TopWord("linear", 300),
                new TopWord("xenail", 1000)
        )));
    }

    private File getFile(String filename) {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(classLoader.getResource(filename).getFile());
    }

}