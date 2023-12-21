package ru.practicum.list;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArrayListImplTest {

    @Test
    public void testSize() {
        ArrayListImpl<String> list = new ArrayListImpl<>();
        assertEquals(0, list.size());

        list.add("Item 1");
        assertEquals(1, list.size());

        list.add("Item 2");
        assertEquals(2, list.size());

        list.remove("Item 1");
        assertEquals(1, list.size());
    }

    @Test
    public void testIsEmpty() {
        ArrayListImpl<Integer> list = new ArrayListImpl<>();
        assertTrue(list.isEmpty());

        list.add(42);
        assertFalse(list.isEmpty());

        list.remove(0);
        assertTrue(list.isEmpty());
    }


    @Test
    public void testAddAndGet() {
        ArrayListImpl<Integer> list = new ArrayListImpl<>();
        list.add(10);
        list.add(20);
        list.add(30);

        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(30, list.get(2));
    }

    @Test
    public void testRemove() {
        ArrayListImpl<String> list = new ArrayListImpl<>();
        list.add("Item 1");
        list.add("Item 2");
        list.add("Item 3");

        assertTrue(list.remove("Item 2"));
        assertEquals(2, list.size());
        assertEquals("Item 1", list.get(0));
        assertEquals("Item 3", list.get(1));

        assertFalse(list.remove("Non-existent Item"));
        assertEquals(2, list.size());
    }

    @Test
    public void testIterator() {
        ArrayListImpl<Integer> list = new ArrayListImpl<>();
        list.add(1);
        list.add(2);
        list.add(3);

        int sum = 0;
        for (int value : list) {
            sum += value;
        }

        assertEquals(6, sum);
    }
}
