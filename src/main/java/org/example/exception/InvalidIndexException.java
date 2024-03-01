package org.example.exception;

/**
 * Класс-исключение, если введен неправильный индекс элемента. <br>
 * Наследуется от класса {@link RuntimeException}
 */
public class InvalidIndexException extends RuntimeException {
    public InvalidIndexException(String message) {
        super(message);
    }
}
