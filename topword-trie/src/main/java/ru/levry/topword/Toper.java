package ru.levry.topword;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author levry
 */
public class Toper {

    static final Comparator<TopWord> TOP_COMPARATOR = Comparator
            .comparingInt(TopWord::getTop).reversed()
            .thenComparing(TopWord::getWord);

    private final Trie<Integer> trie;

    public Toper(Trie<Integer> trie) {
        this.trie = trie;
    }

    public Toper(Collection<TopWord> collection) {
        this(new Trie<>());
        putWords(collection);
    }

    public void putWords(Collection<TopWord> collection) {
        for (TopWord word : collection) {
            this.trie.put(word.word, word.top);
        }
    }

    public List<String> most(String prefix, int size) {

        if (trie.size() == 0) {
            return Collections.emptyList();
        }

        PriorityQueue<TopWord> queue = collectTopWords(prefix);

        return convertToWords(queue, size);
    }

    private PriorityQueue<TopWord> collectTopWords(String prefix) {
        PriorityQueue<TopWord> queue = new PriorityQueue<>(TOP_COMPARATOR);

        trie.collectWithPrefix(prefix, (s, v) -> queue.add(new TopWord(s, v)));

        return queue;
    }

    private List<String> convertToWords(PriorityQueue<TopWord> queue, int size) {
        List<String> words = new ArrayList<>(size);

        while (!queue.isEmpty() && words.size() < size) {
            TopWord word = queue.poll();
            words.add(word.word);
        }
        return words;
    }

}
