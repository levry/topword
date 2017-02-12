package ru.levry.topword;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;

/**
 * @author levry
 */
public class Trie<T> {

    private int n;
    private Node<T> root;

    public int size() {
        return n;
    }

    public boolean contains(String key) {
        Objects.requireNonNull(key, "Key must by not null");

        return get(key) != null;
    }

    public T get(String key) {
        Objects.requireNonNull(key, "Key must by not null");

        if (key.length() == 0) {
            throw new IllegalArgumentException("Key must have length >= 1");
        }
        Node<T> x = get(root, key, 0);
        if (x == null) {
            return null;
        }
        return x.value;
    }

    private Node<T> get(Node<T> x, String key, int d) {
        if (x == null) {
            return null;
        }
        if (key.length() == 0) {
            throw new IllegalArgumentException("Key must have length >= 1");
        }
        char c = key.charAt(d);
        if (c < x.c) {
            return get(x.left, key, d);
        } else if (c > x.c) {
            return get(x.right, key, d);
        } else if (d < key.length() - 1) {
            return get(x.mid, key, d + 1);
        } else {
            return x;
        }
    }

    public void put(String key, T val) {
        Objects.requireNonNull(key, "Key must by not null");

        if (!contains(key)) {
            n++;
        }
        root = put(root, key, val, 0);
    }

    private Node<T> put(Node<T> x, String key, T val, int d) {
        char c = key.charAt(d);
        if (x == null) {
            x = new Node<>(c);
        }
        if (c < x.c) {
            x.left = put(x.left, key, val, d);
        } else if (c > x.c) {
            x.right = put(x.right, key, val, d);
        } else if (d < key.length() - 1) {
            x.mid = put(x.mid, key, val, d + 1);
        } else {
            x.value = val;
        }
        return x;
    }

    public Iterable<String> keys() {
        List<String> queue = new ArrayList<>();
        collect(root, new StringBuilder(), (s, v) -> queue.add(s));
        return queue;
    }

    public void collectWithPrefix(String prefix, BiConsumer<String, T> consumer) {
        Objects.requireNonNull(prefix, "Prefix must be not null");

        Node<T> x = get(root, prefix, 0);
        if (x == null) {
            return;
        }
        if (x.value != null) {
            consumer.accept(prefix, x.value);
        }
        collect(x.mid, new StringBuilder(prefix), consumer);
    }

    private void collect(Node<T> x, StringBuilder prefix, BiConsumer<String, T> consumer) {
        if (x == null) {
            return;
        }
        collect(x.left, prefix, consumer);
        if (x.value != null) {
            consumer.accept(prefix.toString() + x.c, x.value);
        }
        collect(x.mid, prefix.append(x.c), consumer);
        prefix.deleteCharAt(prefix.length() - 1);
        collect(x.right, prefix, consumer);
    }

    private static class Node<T> {

        private final char c;
        private Node<T> left, mid, right;
        private T value;

        Node(char c) {
            this.c = c;
        }
    }

}