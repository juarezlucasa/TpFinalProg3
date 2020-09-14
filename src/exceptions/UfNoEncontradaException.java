package exceptions;

public class UfNoEncontradaException extends Exception {
	
    public UfNoEncontradaException() {
    }
    
    public UfNoEncontradaException(String message) {
        super(message);
    }
    
    public UfNoEncontradaException(Throwable cause) {
        super(cause);
    }
    
    public UfNoEncontradaException(String message, Throwable cause) {
        super(message, cause);
    }

}
