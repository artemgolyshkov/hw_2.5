package exeption;


public class InvalidNameException extends RuntimeException {
    public InvalidNameException(String name) {
        super(name);
    }
}
