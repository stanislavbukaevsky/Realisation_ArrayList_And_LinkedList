package org.example.arrayList;

import org.example.MyList;
import org.example.exception.InvalidIndexException;
import org.example.exception.NullPointerElementException;
import org.example.iterator.MyIterator;

import java.util.Arrays;
import java.util.Iterator;

import static org.example.constant.ExceptionTextMessageConstant.INVALID_INDEX_EXCEPTION_MESSAGE;
import static org.example.constant.ExceptionTextMessageConstant.NULL_POINTER_ELEMENT_EXCEPTION_MESSAGE;

/**
 * Реализация собственного ArrayList.
 * Этот класс реализовывает интерфейс {@link MyList}
 *
 * @param <E> обобщенный параметр
 */
public class MyArrayList<E> implements MyList<E> {
    private E[] values;
    private int size;
    private final int DEFAULT_CAPACITY = 0;

    public MyArrayList() {
        this.values = (E[]) new Object[DEFAULT_CAPACITY];
    }

    /**
     * Этот метод добавляет элемент в список
     *
     * @param element обобщенный элемент
     * @return Возвращает true, если добавление элемента произошло успешно
     */
    @Override
    public boolean add(E element) {
        if (checkingForCompletionList()) {
            grow();
        }
        checkingForElementList(element);
        values[size++] = element;
        return true;
    }

    /**
     * Этот метод добавляет элемент в список по индексу ячейки
     *
     * @param index   индекс ячейки
     * @param element обобщенный элемент
     */
    @Override
    public void add(int index, E element) {
        checkingForSizeList(index);
        checkingForElementList(element);
        if (checkingForCompletionList()) {
            grow();
        }
        for (int i = size; i > index; i--) {
            values[i] = values[i - 1];
        }
        values[index] = element;
        size++;
    }

    /**
     * Этот метод позволяет получить элемент по его индексу
     *
     * @param index индекс элемента
     * @return Возвращает найденный элемент
     */
    @Override
    public E get(int index) {
        checkingForSizeList(index);
        return values[index];
    }

    /**
     * Этот метод удаляет элемент по его индексу
     *
     * @param index индекс элемента
     */
    @Override
    public void delete(int index) {
        checkingForSizeList(index);
        try {
            E[] temp = values;
            values = (E[]) new Object[temp.length - 1];
            System.arraycopy(temp, 0, values, 0, index);
            System.arraycopy(temp, index + 1, values, index, temp.length - index - 1);
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    /**
     * Этот метод позволяет очистить весь список
     */
    @Override
    public void clear() {
        size = 0;
        this.values = (E[]) new Object[size];
    }

    /**
     * Этот метод позволяет отсортировать список по возрастанию
     */
    @Override
    public void sort() {
        E[] array = values;
        for (int i = 0; i < array.length - 1; i++) {
            int minIndexElement = i;
            for (int j = i + 1; j < array.length; j++) {
                Comparable<E> currentElement = (Comparable<E>) array[minIndexElement];
                Comparable<E> nextElement = (Comparable<E>) array[j];
                if (currentElement.compareTo((E) nextElement) > 0) {
                    minIndexElement = j;
                }
            }
            E element = array[i];
            array[i] = array[minIndexElement];
            array[minIndexElement] = element;
        }
    }

    /**
     * Этот метод позволяет изменить значение элемента в списке по индексу
     *
     * @param index   индекс элемента
     * @param element обобщенный элемент
     * @return Возвращает измененный элемент
     */
    @Override
    public E set(int index, E element) {
        checkingForSizeList(index);
        checkingForElementList(element);
        values[index] = element;
        return element;
    }

    /**
     * Этот метод позволяет получить длину списка
     *
     * @return Возвращает целое число, равное длине списка
     */
    @Override
    public int size() {
        return values.length;
    }

    /**
     * Этот метод позволяет вывести в консоль весь список
     */
    @Override
    public void print() {
        for (int i = 0; i < values.length; i++) {
            if (i == values.length - 1) {
                System.out.print(values[i]);
            } else {
                System.out.print(values[i] + ", ");
            }
        }
        System.out.println();
    }

    /**
     * Этот метод позволяет итерироваться по списку
     *
     * @return Возвращает объект {@link MyIterator}
     */
    @Override
    public Iterator<E> iterator() {
        return new MyIterator<>(values);
    }

    private void checkingForSizeList(int index) {
        if (index < 0 || index > size) {
            throw new InvalidIndexException(INVALID_INDEX_EXCEPTION_MESSAGE);
        }
    }

    private void checkingForElementList(E element) {
        if (element == null) {
            throw new NullPointerElementException(NULL_POINTER_ELEMENT_EXCEPTION_MESSAGE);
        }
    }

    private boolean checkingForCompletionList() {
        return size == values.length;
    }

    private void grow() {
        int capacityOld = size;
        int capacityNew = capacityOld + (DEFAULT_CAPACITY / 2) + 1;
        values = Arrays.copyOf(values, capacityNew);
    }
}
