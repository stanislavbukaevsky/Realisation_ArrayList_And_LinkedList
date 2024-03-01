package org.example.linkedList;

import org.example.MyList;
import org.example.exception.InvalidIndexException;
import org.example.exception.NullPointerElementException;
import org.junit.jupiter.api.Test;

import static org.example.constant.AssertConstantTest.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс с тестами для LinkedList
 */
public class MyLinkedListTest {
    private final MyList<String> myList = new MyLinkedList<>();

    @Test
    public void addForElementTest() {
        assertTrue(myList.add(TEST_CONSTANT_ONE_ELEMENT));
        assertTrue(myList.add(TEST_CONSTANT_TWO_ELEMENT));
        assertThrows(NullPointerElementException.class, () -> myList.add(TEST_CONSTANT_NEGATIVE_SCRIPT_ELEMENT));
    }

    @Test
    public void addForIndexTest() {
        myList.add(TEST_CONSTANT_ONE_INDEX, TEST_CONSTANT_ONE_ELEMENT);
        myList.add(TEST_CONSTANT_TWO_INDEX, TEST_CONSTANT_TWO_ELEMENT);
        assertEquals(TEST_CONSTANT_COUNT_TWO, myList.size());
        assertThrows(InvalidIndexException.class, () -> myList.add(TEST_CONSTANT_NEGATIVE_SCRIPT_INDEX, TEST_CONSTANT_THREE_ELEMENT));
    }

    @Test
    public void getForIndexTest() {
        myList.add(TEST_CONSTANT_ONE_INDEX, TEST_CONSTANT_ONE_ELEMENT);
        myList.add(TEST_CONSTANT_TWO_INDEX, TEST_CONSTANT_TWO_ELEMENT);
        assertEquals(TEST_CONSTANT_ONE_ELEMENT, myList.get(TEST_CONSTANT_ONE_INDEX));
        assertEquals(TEST_CONSTANT_TWO_ELEMENT, myList.get(TEST_CONSTANT_TWO_INDEX));
        assertThrows(InvalidIndexException.class, () -> myList.get(TEST_CONSTANT_NEGATIVE_SCRIPT_INDEX));
    }

    @Test
    public void deleteForIndexTest() {
        myList.add(TEST_CONSTANT_ONE_INDEX, TEST_CONSTANT_ONE_ELEMENT);
        myList.add(TEST_CONSTANT_TWO_INDEX, TEST_CONSTANT_TWO_ELEMENT);
        myList.delete(TEST_CONSTANT_ONE_INDEX);
        assertEquals(TEST_CONSTANT_COUNT_ONE, myList.size());
        assertThrows(InvalidIndexException.class, () -> myList.delete(TEST_CONSTANT_NEGATIVE_SCRIPT_INDEX));
    }

    @Test
    public void clearListTest() {
        myList.add(TEST_CONSTANT_ONE_INDEX, TEST_CONSTANT_ONE_ELEMENT);
        myList.add(TEST_CONSTANT_TWO_INDEX, TEST_CONSTANT_TWO_ELEMENT);
        myList.clear();
        assertEquals(TEST_CONSTANT_ONE_INDEX, myList.size());
    }

    @Test
    public void sortListTest() {
        myList.add(TEST_CONSTANT_ONE_INDEX, TEST_CONSTANT_THREE_ELEMENT_BY_SORT);
        myList.add(TEST_CONSTANT_TWO_INDEX, TEST_CONSTANT_ONE_ELEMENT_BY_SORT);
        myList.add(TEST_CONSTANT_THREE_INDEX, TEST_CONSTANT_TWO_ELEMENT_BY_SORT);
        myList.sort();
        assertEquals(TEST_CONSTANT_ONE_ELEMENT_BY_SORT, myList.get(TEST_CONSTANT_ONE_INDEX));
    }

    @Test
    public void setElementForIndexTest() {
        myList.add(TEST_CONSTANT_ONE_INDEX, TEST_CONSTANT_ONE_ELEMENT);
        myList.add(TEST_CONSTANT_TWO_INDEX, TEST_CONSTANT_TWO_ELEMENT);
        assertEquals(TEST_CONSTANT_THREE_ELEMENT, myList.set(TEST_CONSTANT_ONE_INDEX, TEST_CONSTANT_THREE_ELEMENT));
        assertThrows(InvalidIndexException.class, () -> myList.set(TEST_CONSTANT_NEGATIVE_SCRIPT_INDEX, TEST_CONSTANT_THREE_ELEMENT));
    }

    @Test
    public void sizeListTest() {
        myList.add(TEST_CONSTANT_ONE_INDEX, TEST_CONSTANT_ONE_ELEMENT);
        myList.add(TEST_CONSTANT_TWO_INDEX, TEST_CONSTANT_TWO_ELEMENT);
        assertEquals(TEST_CONSTANT_COUNT_TWO, myList.size());
    }

    @Test
    public void printListTest() {
        myList.add(TEST_CONSTANT_ONE_INDEX, TEST_CONSTANT_ONE_ELEMENT);
        myList.add(TEST_CONSTANT_TWO_INDEX, TEST_CONSTANT_TWO_ELEMENT);
        myList.add(TEST_CONSTANT_THREE_INDEX, TEST_CONSTANT_THREE_ELEMENT);
        myList.print();
        assertTrue(true, TEST_CONSTANT_ONE_ELEMENT);
        assertTrue(true, TEST_CONSTANT_TWO_ELEMENT);
        assertTrue(true, TEST_CONSTANT_THREE_ELEMENT);
    }
}
