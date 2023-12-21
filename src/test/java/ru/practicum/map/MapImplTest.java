package ru.practicum.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MapImplTest {

    private MapImpl<String, Integer> map;

    @BeforeEach
    void setUp() {
        map = new MapImpl<>();
    }

    @Test
    void testPutAndGet() {
        map.put("one", 1);
        map.put("two", 2);

        assertEquals(1, map.get("one"));
        assertEquals(2, map.get("two"));
    }

    @Test
    void testPutDuplicateKey() {
        map.put("one", 1);
        map.put("one", 10);

        assertEquals(10, map.get("one"));
        assertEquals(1, map.size());
    }

    @Test
    void testGetNonExistingKey() {
        assertNull(map.get("nonexistent"));
    }

    @Test
    void testDelete() {
        map.put("one", 1);
        map.delete("one");

        assertNull(map.get("one"));
        assertEquals(0, map.size());
    }

    @Test
    void testSize() {
        map.put("one", 1);
        map.put("two", 2);

        assertEquals(2, map.size());

        map.delete("one");

        assertEquals(1, map.size());
    }

    @Test
    void testResizeArray() {
        for (int i = 0; i < 20; i++) {
            map.put("key" + i, i);
        }

        assertEquals(20, map.size());
    }
}
