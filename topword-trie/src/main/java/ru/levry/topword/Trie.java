package ru.levry.topword;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.function.BiConsumer;

/**
 * @author levry
 */
public class Trie<Value> {

    private int n;              // size
    private Node<Value> root;   // root of TST

    public int size() {
        return n;
    }

    public boolean contains(String key) {
        Objects.requireNonNull(key, "Key must by not null");

        return get(key) != null;
    }

    public Value get(String key) {
        Objects.requireNonNull(key, "Key must by not null");

        if (key.length() == 0) {
            throw new IllegalArgumentException("Key must have length >= 1");
        }
        Node<Value> x = get(root, key, 0);
        if (x == null) {
            return null;
        }
        return x.val;
    }

    private Node<Value> get(Node<Value> x, String key, int d) {
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

    public void put(String key, Value val) {
        Objects.requireNonNull(key, "Key must by not null");

        if (!contains(key)) {
            n++;
        }
        root = put(root, key, val, 0);
    }

    private Node<Value> put(Node<Value> x, String key, Value val, int d) {
        char c = key.charAt(d);
        if (x == null) {
            x = new Node<>();
            x.c = c;
        }
        if (c < x.c) {
            x.left = put(x.left, key, val, d);
        } else if (c > x.c) {
            x.right = put(x.right, key, val, d);
        } else if (d < key.length() - 1) {
            x.mid = put(x.mid, key, val, d + 1);
        } else {
            x.val = val;
        }
        return x;
    }

    public Iterable<String> keys() {
        Queue<String> queue = new LinkedList<>();
        collect(root, new StringBuilder(), (s, v) -> queue.add(s));
        return queue;
    }

    public void collectWithPrefix(String prefix, BiConsumer<String, Value> consumer) {
        Objects.requireNonNull(prefix, "Prefix must be not null");

        Node<Value> x = get(root, prefix, 0);
        if (x == null) {
            return;
        }
        if (x.val != null) {
            consumer.accept(prefix, x.val);
        }
        collect(x.mid, new StringBuilder(prefix), consumer);
    }

    private void collect(Node<Value> x, StringBuilder prefix, BiConsumer<String, Value> consumer) {
        if (x == null) {
            return;
        }
        collect(x.left, prefix, consumer);
        if (x.val != null) {
            consumer.accept(prefix.toString() + x.c, x.val);
        }
        collect(x.mid, prefix.append(x.c), consumer);
        prefix.deleteCharAt(prefix.length() - 1);
        collect(x.right, prefix, consumer);
    }

    private static class Node<Value> {

        private char c;                        // character
        private Node<Value> left, mid, right;  // left, middle, and right subtries
        private Value val;                     // value associated with string
    }

}