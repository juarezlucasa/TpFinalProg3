package exceptions;

public class CamposVaciosException extends Exception {
	
    public CamposVaciosException() {
    }
    
    public CamposVaciosException(String message) {
        super(message);
    }
    
    public CamposVaciosException(Throwable cause) {
        super(cause);
    }
    
    public CamposVaciosException(String message, Throwable cause) {
        super(message, cause);
    }

}
