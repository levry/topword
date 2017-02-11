package ru.levry.topword.cli;

import org.junit.Test;
import ru.levry.topword.TopWord;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author levry
 */
public class ToperCliTest {

    @Test
    public void readWordsNTestFromFile() throws Exception {

        ToperCli cli = new ToperCli();

        String file = getFile("test.txt");
        cli.readFrom(file);

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

    private String getFile(String filename) {
        ClassLoader classLoader = getClass().getClassLoader();
        return classLoader.getResource(filename).getFile();
    }

}