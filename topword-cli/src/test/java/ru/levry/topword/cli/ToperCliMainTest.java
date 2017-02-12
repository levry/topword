package ru.levry.topword.cli;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author levry
 */
public class ToperCliMainTest {

    private PrintStream sout;
    private ByteArrayOutputStream output;

    @Before
    public void setUp() throws Exception {
        sout = System.out;
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(sout);
    }

    @Test
    public void runMain() throws Exception {
        String[] args = {
                getFile("test.txt")
        };
        ToperCli.main(args);

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

    private String getFile(String filename) {
        ClassLoader classLoader = getClass().getClassLoader();
        return classLoader.getResource(filename).getFile();
    }

}