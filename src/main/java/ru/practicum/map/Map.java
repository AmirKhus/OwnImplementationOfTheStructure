package ru.practicum.map;

/**
 *  В этом интерфейсе представлены базовы методы для реализации Map
 * @param <K>
 * @param <V>
 */
public interface Map<K, V> {
    void put(K k, V v);

    int size();

    V get(K key);

    void delete(K k);

    void resizeArray();
}
