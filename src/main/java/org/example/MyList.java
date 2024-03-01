package org.example;

/**
 * Интерфейс собственного списка.
 * Наследуется от интерфейса {@link Iterable}
 *
 * @param <E> обобщенный параметр
 */
public interface MyList<E> extends Iterable<E> {
    boolean add(E element);

    void add(int index, E element);

    E get(int index);

    void delete(int index);

    void clear();

    void sort();

    E set(int index, E element);

    int size();

    void print();
}
