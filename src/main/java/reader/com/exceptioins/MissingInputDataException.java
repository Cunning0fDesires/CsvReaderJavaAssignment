package reader.com.exceptioins;

public class MissingInputDataException extends RuntimeException {
    public MissingInputDataException(String message) {
        super(message);
    }
}
