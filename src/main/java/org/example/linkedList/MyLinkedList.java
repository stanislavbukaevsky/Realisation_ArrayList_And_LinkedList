package org.example.linkedList;

import org.example.MyList;
import org.example.arrayList.MyArrayList;
import org.example.exception.InvalidIndexException;
import org.example.exception.NullPointerElementException;
import org.example.iterator.MyIterator;

import java.util.Iterator;

import static org.example.constant.ExceptionTextMessageConstant.*;

/**
 * Реализация собственного LinkedList.
 * Этот класс реализовывает интерфейс {@link MyList}
 *
 * @param <E> обобщенный параметр
 */
public class MyLinkedList<E> implements MyList<E> {
    private Node<E> firstNode;
    private Node<E> lastNode;
    private int size = 0;

    public MyLinkedList() {
    }

    /**
     * Этот метод добавляет элемент в список
     *
     * @param element обобщенный элемент
     * @return Возвращает true, если добавление элемента произошло успешно
     */
    @Override
    public boolean add(E element) {
        checkingForElementList(element);
        Node<E> prevNode = lastNode;
        Node<E> newNode = new Node<>(element, prevNode, null);
        lastNode = newNode;
        if (prevNode == null) {
            firstNode = newNode;
        } else {
            prevNode.nextElement = newNode;
        }
        size++;
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
        if (index < 0) {
            throw new InvalidIndexException(INVALID_INDEX_EXCEPTION_TWO_MESSAGE);
        }
        if (size == 0 || index == 0) {
            Node<E> newNode = new Node<>(element, null, firstNode);
            if (firstNode != null) {
                firstNode.prevElement = newNode;
            } else {
                lastNode = newNode;
            }
            firstNode = newNode;
            size++;
            return;
        }
        Node<E> currentNode = firstNode;
        for (int i = 1; i < index && i < size; i++) {
            currentNode = currentNode.nextElement;
        }
        Node<E> newNode = new Node<>(element, currentNode, currentNode.nextElement);
        if (currentNode.nextElement == null) {
            lastNode = newNode;
        } else {
            currentNode.nextElement.prevElement = newNode;
        }
        currentNode.nextElement = newNode;
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
        return getNode(index).currentElement;
    }

    /**
     * Этот метод удаляет элемент по его индексу
     *
     * @param index индекс элемента
     */
    @Override
    public void delete(int index) {
        Node<E> currentNode = getNode(index);
        if (currentNode == null) {
            return;
        }
        Node<E> prev = currentNode.prevElement;
        Node<E> next = currentNode.nextElement;
        if (prev != null) {
            prev.nextElement = next;
            currentNode.prevElement = null;
        } else {
            firstNode = currentNode.nextElement;
        }
        if (next != null) {
            next.prevElement = prev;
            currentNode.nextElement = null;
        } else {
            lastNode = null;
        }
        currentNode.currentElement = null;
        size--;
    }

    /**
     * Этот метод позволяет очистить весь список
     */
    @Override
    public void clear() {
        for (Node<E> i = firstNode; i != null; ) {
            Node<E> next = i.nextElement;
            i.currentElement = null;
            i.prevElement = null;
            i.nextElement = null;
            i = next;
        }
        firstNode = null;
        lastNode = null;
        size = 0;
    }

    /**
     * Этот метод позволяет отсортировать список по возрастанию
     */
    @Override
    public void sort() {
        MyList<E> myList = new MyArrayList<>();
        for (int i = 0; i < size; i++) {
            myList.add(getNode(i).currentElement);
        }
        myList.sort();
        int count = 0;
        for (int i = 0; i < size; i++) {
            set(count, myList.get(count));
            count++;
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
        Node<E> currentNode = getNode(index);
        currentNode.currentElement = element;
        return element;
    }

    /**
     * Этот метод позволяет получить длину списка
     *
     * @return Возвращает целое число, равное длине списка
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Этот метод позволяет вывести в консоль весь список
     */
    @Override
    public void print() {
        for (int i = 0; i < size(); i++) {
            if (i == 0) {
                System.out.print("[" + get(i) + ", ");
            } else if (i == size() - 1) {
                System.out.print(get(i) + "]");
            } else {
                System.out.print(get(i) + ", ");
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
        E[] elements = (E[]) new Object[size];
        for (int i = 0; i < size; i++) {
            elements[i] = getNode(i).currentElement;
        }
        return new MyIterator<>(elements);
    }

    private void checkingForSizeList(int index) {
        if (index < 0 || index >= size) {
            throw new InvalidIndexException(INVALID_INDEX_EXCEPTION_MESSAGE);
        }
    }

    private void checkingForElementList(E element) {
        if (element == null) {
            throw new NullPointerElementException(NULL_POINTER_ELEMENT_EXCEPTION_MESSAGE);
        }
    }

    private Node<E> getNode(int index) {
        checkingForSizeList(index);
        Node<E> currentNode;
        if (index < size / 2) {
            currentNode = firstNode;
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.nextElement;
            }
        } else {
            currentNode = lastNode;
            for (int i = size - 1; i > index; i--) {
                currentNode = currentNode.prevElement;
            }
        }
        return currentNode;
    }

    /**
     * Внутренний класс объекта Node для двусвязанного списка
     *
     * @param <E> обобщенный параметр
     */
    private static class Node<E> {
        private E currentElement;
        private Node<E> prevElement;
        private Node<E> nextElement;

        public Node(E currentElement, Node<E> prevElement, Node<E> nextElement) {
            this.currentElement = currentElement;
            this.prevElement = prevElement;
            this.nextElement = nextElement;
        }
    }
}
