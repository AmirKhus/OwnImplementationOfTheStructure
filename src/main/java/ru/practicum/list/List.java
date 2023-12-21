package ru.practicum.list;

/**
 *Интерфейс для реализации базового Arraylist
 * **/
public interface List<T> extends Iterable<T> {
    int size();

    boolean isEmpty();

    boolean add(T element);

    boolean remove(T t);

    T remove(int index);

    T get(int index);
}
