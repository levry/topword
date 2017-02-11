package ru.levry.topword.cli;

import ru.levry.topword.TopWord;
import ru.levry.topword.Toper;
import ru.levry.topword.support.WordReader;

import java.io.File;
import java.io.PrintStream;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author levry
 */
public class ToperCli {

    private int size = 10;

    Collection<TopWord> words = Collections.emptyList();
    Collection<String> tests = Collections.emptyList();

    public void setSize(int size) {
        this.size = size;
    }

    void readFrom(String filename) throws Exception {
        File file = new File(filename);
        try(WordReader reader = new WordReader(file)) {
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

    public static void main(String[] args) throws Exception {
        if(args.length != 1) {
            return;
        }

        String filename = args[0];

        ToperCli cli = new ToperCli();
        cli.readFrom(filename);
        cli.run(System.out);
    }

}
