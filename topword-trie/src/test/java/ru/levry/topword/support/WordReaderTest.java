package ru.levry.topword.support;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import ru.levry.topword.TopWord;

/**
 * @author levry
 */
public class WordReaderTest {

    @Test
    public void readFromFile() throws Exception {
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

    @Test
    public void readFromStream() throws Exception {
        InputStream inputStream = openStream("test.txt");
        WordReader reader = new WordReader(inputStream);

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
        return new File(getResource(filename).getFile());
    }

    private InputStream openStream(String filename) throws IOException {
        return getResource(filename).openStream();
    }

    private URL getResource(String filename) {
        ClassLoader classLoader = getClass().getClassLoader();
        return classLoader.getResource(filename);
    }

}