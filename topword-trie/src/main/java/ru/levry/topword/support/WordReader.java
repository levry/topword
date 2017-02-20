package ru.levry.topword.support;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Supplier;
import ru.levry.topword.TopWord;

/**
 * @author levry
 */
public class WordReader implements AutoCloseable {

    private final Scanner scanner;

    public WordReader(File file) throws FileNotFoundException {
        this.scanner = new Scanner(file);
    }

    public WordReader(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
    }

    public Collection<TopWord> read() {
        return read(ArrayList::new, () -> {
            String word = scanner.next();
            int top = scanner.nextInt();
            return new TopWord(word, top);
        });
    }

    public Collection<String> readTest() {
        return read(ArrayList::new, scanner::next);
    }

    private <T> List<T> read(Function<Integer, List<T>> supplier, Supplier<T> consumer) {
        int n = scanner.nextInt();
        List<T> list = supplier.apply(n);
        for (int i = 0; i < n && scanner.hasNext(); i++) {
            T item = consumer.get();
            list.add(item);
        }
        return list;
    }

    @Override
    public void close() throws Exception {
        scanner.close();
    }
}
