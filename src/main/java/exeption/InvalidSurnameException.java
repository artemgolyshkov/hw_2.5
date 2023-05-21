package exeption;

public class InvalidSurnameException extends RuntimeException {
    public InvalidSurnameException(String surname) {
        super(surname);
    }
}