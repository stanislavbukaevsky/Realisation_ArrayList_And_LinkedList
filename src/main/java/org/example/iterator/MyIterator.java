package org.example.iterator;

import java.util.Iterator;

/**
 * Реализация итератора для списков.
 * Реализует интерфейс {@link Iterator}
 *
 * @param <E> обобщенный параметр
 */
public class MyIterator<E> implements Iterator<E> {
    private E[] values;
    private int index = 0;

    public MyIterator(E[] values) {
        this.values = values;
    }

    /**
     * Этот метод определяет наличие следующего элемента в списке
     *
     * @return Возвращает true, если следующий элемент присутствует в списке
     */
    @Override
    public boolean hasNext() {
        return index < values.length;
    }

    /**
     * Этот метод возвращает текущий элемент
     *
     * @return Возвращает текущий элемент
     */
    @Override
    public E next() {
        return values[index++];
    }
}
