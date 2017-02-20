package ru.levry.topword.cli;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import ru.levry.topword.TopWord;
import ru.levry.topword.Toper;
import ru.levry.topword.support.WordReader;

/**
 * @author levry
 */
public class ToperCli {

    Collection<TopWord> words = Collections.emptyList();
    Collection<String> tests = Collections.emptyList();
    private int size = 10;

    public static void main(String[] args) throws Exception {
        ToperCli cli = new ToperCli();
        cli.readFrom(System.in);
        cli.run(System.out);
    }

    public void setSize(int size) {
        this.size = size;
    }

    void readFrom(InputStream inputStream) throws Exception {
        try (WordReader reader = new WordReader(inputStream)) {
            words = reader.read();
            tests = reader.readTest();
        }
    }

    void run(PrintStream out) {
        final Toper toper = new Toper(words);

        for (String test : tests) {
            List<String> words = toper.most(test, size);
            words.forEach(out::println);
            out.println();
        }
    }

}
