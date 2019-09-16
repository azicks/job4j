package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class SimpleHashMap<K, V> implements Iterable<V> {
    private int modCount;
    private int capacity = 16;
    private int size;
    private final float loadFactor = 0.75f;
    private int threshold = (int) (capacity * loadFactor);
    private Entry<K, V>[] map = new Entry[capacity];


    public boolean insert(K key, V value) {
        boolean result = false;
        if (key != null) {
            int hash = hash(key.hashCode());
            int idx = indexFor(hash, capacity);
            if (map[idx] == null) {
                checkSise();
                map[idx] = new Entry<>(key, value);
                size++;
                modCount++;
                result = true;
            }
        }
        return result;
    }

    public V get(K key) {
        V value = null;
        int hash = key.hashCode();
        int idx = indexFor(hash, capacity);
        if (map[idx] != null) {
            value = map[idx].getValue();
        }
        return value;

    }

    public boolean delete(K key) {
        boolean result = false;
        int hash = key.hashCode();
        int idx = indexFor(hash, capacity);
        if (map[idx] != null && map[idx].getKey().equals(key)) {
            map[idx] = null;
            size--;
            modCount++;
            result = true;
        }
        return result;
    }

    private int hash(int h) {
        return h % capacity;
    }

    private int indexFor(int h, int length) {
        return h & (length - 1);
    }

    private void checkSise() {
        if (size == threshold) {
            resize(map.length << 1);
        }
    }

    private void resize(int newCapacity) {
        Entry<K, V>[] newMap = new Entry[newCapacity];
        for (Entry<K, V> e : map) {
            if (e == null) {
                continue;
            }
            int hash = hash(e.hashCode());
            int idx = indexFor(hash, newCapacity);
            newMap[idx] = e;
        }
        map = newMap;
        capacity = newCapacity;
        threshold = (int) (newCapacity * loadFactor);
        modCount++;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Entry e : map) {
            if (e == null) {
                continue;
            }
            str.append(e.getKey())
                    .append(" : ")
                    .append(e.getValue())
                    .append(System.lineSeparator());
        }
        return str.toString();
    }

    @Override
    public Iterator<V> iterator() {
        return new Iterator<>() {
            private int currentModCount = modCount;
            private int position;

            @Override
            public boolean hasNext() {
                return position < size;
            }

            @Override
            public V next() {
                Entry<K, V> e;
                if (currentModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                do {
                    e = map[position];
                    position++;
                } while (e == null && position != size);
                return e.getValue();
            }
        };
    }

    public int getCapacity() {
        return capacity;
    }
}
