package exceptions;

public class EdificioNoEncontradoException extends Exception {

    public EdificioNoEncontradoException() {
    }
    
    public EdificioNoEncontradoException(String message) {
        super(message);
    }
    
    public EdificioNoEncontradoException(Throwable cause) {
        super(cause);
    }
    
    public EdificioNoEncontradoException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
