package ru.levry.topword.cli;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import org.junit.Test;
import ru.levry.topword.TopWord;

/**
 * @author levry
 */
public class ToperCliTest {

    @Test
    public void readWordsNTestFromFile() throws Exception {

        ToperCli cli = new ToperCli();

        InputStream is = getFile("test.txt");
        cli.readFrom(is);

        assertThat(cli.words, notNullValue());
        assertThat(cli.words, is(Arrays.asList(
                new TopWord("kare", 10),
                new TopWord("kanojo", 20),
                new TopWord("karetachi", 1),
                new TopWord("korosu", 7),
                new TopWord("sakura", 3)
        )));

        assertThat(cli.tests, notNullValue());
        assertThat(cli.tests, is(Arrays.asList("k", "ka", "kar")));
    }

    @Test
    public void runCli() throws Exception {
        ToperCli cli = new ToperCli();
        cli.words = Arrays.asList(
                new TopWord("kare", 10),
                new TopWord("kanojo", 20),
                new TopWord("karetachi", 1)
        );
        cli.tests = Arrays.asList("ka", "kar");

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        cli.run(new PrintStream(output));

        assertThat(output.toString(), is(
                "kanojo\n" +
                        "kare\n" +
                        "karetachi\n" +
                        "\n" +
                        "kare\n" +
                        "karetachi\n" +
                        "\n"));
    }

    private InputStream getFile(String filename) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        return classLoader.getResource(filename).openStream();
    }

}