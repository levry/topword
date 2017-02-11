package ru.levry.topword.support;

import ru.levry.topword.TopWord;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

/**
 * @author levry
 */
public class WordReader implements AutoCloseable {

    private final Scanner scanner;

    public WordReader(File file) throws FileNotFoundException {
        this.scanner = new Scanner(file);
    }

    public Collection<TopWord> read() {

        int n = scanner.nextInt();

        List<TopWord> words = new ArrayList<>(n);
        for (int i = 0; i < n; i++){
            String word = scanner.next();
            int top = scanner.nextInt();
            words.add(new TopWord(word, top));
        }
        return words;
    }

    public Collection<String> readTest() {
        int n = scanner.nextInt();

        List<String> tests = new ArrayList<>(n);
        for (int i = 0; i < n && scanner.hasNext(); n++) {
            tests.add(scanner.next());
        }

        return tests;
    }

    @Override
    public void close() throws Exception {
        scanner.close();
    }
}
