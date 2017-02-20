package ru.levry.topword.cli;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author levry
 */
public class ToperCliMainTest {

    private InputStream sin;
    private PrintStream sout;
    private InputStream input;
    private ByteArrayOutputStream output;

    @Before
    public void setUp() throws Exception {
        sin = System.in;
        input = getFile("test.txt");
        System.setIn(input);

        sout = System.out;
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    @After
    public void tearDown() throws Exception {
        System.setIn(sin);
        System.setOut(sout);
    }

    @Test
    public void runMain() throws Exception {
        ToperCli.main(new String[] {});

        assertThat(output.toString(), is("kanojo\n" +
                "kare\n" +
                "korosu\n" +
                "karetachi\n" +
                "\n" +
                "kanojo\n" +
                "kare\n" +
                "karetachi\n" +
                "\n" +
                "kare\n" +
                "karetachi\n\n"));
    }

    private InputStream getFile(String filename) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        return classLoader.getResource(filename).openStream();
    }

}