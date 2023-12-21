package ru.practicum.map;

import java.util.LinkedList;

/**
 * Данный код представляет собой простую реализацию хэш-мапы.
 * Он предоставляет основные методы для работы с map, такие как put, get, delete и size.
 *
 * @param <K> ключ
 * @param <V> значение
 **/
public class MapImpl<K, V> implements Map<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    private Entry<K, V>[] buckets;
    private int size;

    public MapImpl() {
        this(DEFAULT_CAPACITY);
    }

    public MapImpl(int initialCapacity) {
        buckets = new Entry[initialCapacity];
    }

    /**
     * Добавляет элемент в Map. Если элемент с указанным ключом уже существует, его значение обновляется.
     * Если размер map превышает определенный коэффициент загрузки (LOAD_FACTOR), вызывается метод resizeArray,
     * который увеличивает размер внутреннего массива и перераспределяет элементы.
     **/
    @Override
    public void put(K key, V value) {
        int index = getIndex(key);

        if (buckets[index] == null) {
            // Если корзина пуста, создаем новую
            buckets[index] = new Entry<>(key, value);
        } else {
            Entry<K, V> entry = buckets[index];
            while (entry != null) {
                if (entry.getKey().equals(key)) {
                    entry.setValue(value);
                    return;
                }
                entry = entry.getNext();
            }
            // Добавляем новый элемент в начало списка
            buckets[index] = new Entry<K, V>(key, value, buckets[index]);
        }

        size++;

        if ((double) size / buckets.length > LOAD_FACTOR) {
            resizeArray();
        }
    }

    /**
     * Возвращает значение, соответствующее указанному ключу. Если ключ не найден, возвращается null.
     **/
    public V get(K key) {
        int index = getIndex(key);
        Entry<K, V> entry = buckets[index];

        while (entry != null) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
            entry = entry.getNext();
        }
        return null;
    }

    /**
     * Удаляет элемент с указанным ключом из map, если он существует.
     **/
    @Override
    public void delete(K key) {
        int index = getIndex(key);

        Entry<K, V> entry = buckets[index];
        Entry<K, V> prevEntry = null;

        while (entry != null) {
            if (entry.getKey().equals(key)) {
                if (prevEntry == null) {
                    // Это первый элемент в корзине
                    buckets[index] = entry.getNext();
                } else {
                    // Убираем элемент из списка
                    prevEntry.setNext(entry.getNext());
                }
                size--;
                return;
            }
            prevEntry = entry;
            entry = entry.getNext();
        }
    }

    /**
     * Возвращает текущий размер map.
     **/
    @Override
    public int size() {
        return size;
    }

    /**
     * Увеличивает размер внутреннего массива и перераспределяет элементы в соответствии с новым размером.
     **/
    @Override
    public void resizeArray() {
        Entry<K, V>[] oldBuckets = buckets;
        buckets = new Entry[2 * oldBuckets.length];
        size = 0;

        for (Entry<K, V> entry : oldBuckets) {
            while (entry != null) {
                put(entry.getKey(), entry.getValue());
                entry = entry.getNext();
            }
        }
    }

    private int getIndex(K key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }

    /**
     * Структура для хранения типа ключ-значение
     **/
    private static class Entry<K, V> {
        private final K key;
        private V value;
        private Entry<K, V> next;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public Entry<K, V> getNext() {
            return next;
        }

        public void setNext(Entry<K, V> next) {
            this.next = next;
        }
    }
}
