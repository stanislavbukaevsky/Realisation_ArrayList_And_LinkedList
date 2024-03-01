package org.example.exception;

/**
 * Класс-исключение, если введен null элемент. <br>
 * Наследуется от класса {@link RuntimeException}
 */
public class NullPointerElementException extends RuntimeException {
    public NullPointerElementException(String message) {
        super(message);
    }
}
