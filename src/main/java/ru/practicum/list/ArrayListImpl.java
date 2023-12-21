package ru.practicum.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 *  В этом классе реализован простой класс ArrayListImpl, который представляет собой реализацию списка,
 *  похожую на ArrayList из стандартной библиотеки Java
 * */
public class ArrayListImpl<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private T[] elements;
    private int size;

    public ArrayListImpl() {
        this.elements = (T[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }
    /**
     * Возвращает текущий размер списка.
     * */
    @Override
    public int size() {
        return size;
    }

    /**
     *Возвращает true, если список пуст (размер равен 0), и false в противном случае
     * */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    /**
     *Добавляет элемент в список. При необходимости увеличивает емкость списка с использованием ensureCapacity().
     * */
    @Override
    public boolean add(T element) {
        ensureCapacity();
        elements[size++] = element;
        return true;
    }

    /**
     *Добавляет элемент в список. При необходимости увеличивает емкость списка с использованием ensureCapacity().
     * */
    @Override
    public boolean remove(T t) {
        for (int i = 0; i < size; i++) {
            if (t.equals(elements[i])) {
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Возвращает элемент по указанному индексу.
     * */
    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) elements[index];
    }

    /**
     * Метод remove(int index): Удаляет элемент по указанному индексу и возвращает удаленное значение
     * */
    @Override
    public T remove(int index) {
        checkIndex(index);
        T oldValue = (T) elements[index];
        removeAt(index);
        return oldValue;
    }

    /**
     *Проверяет, достигнута ли текущая емкость списка, и если да,
     *увеличивает ее в два раза с использованием Arrays.copyOf()
     * */
    private void ensureCapacity() {
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, size * 2);
        }
    }

    /**
     * Удаляет элемент по указанному индексу, сдвигая оставшиеся элементы
     * */
    private void removeAt(int index) {
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null;
    }

    /**
     *Проверяет, находится ли указанный индекс в пределах допустимых значений. Если индекс недопустим,
     * выбрасывает IndexOutOfBoundsException
     * */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    /**
     *Возвращает итератор для последовательного доступа к элементам списка
     * */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return get(currentIndex++);
            }
        };
    }
}
