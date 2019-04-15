package java.Service;

public class ClientServiceException extends RuntimeException {
    public  ClientServiceException(String message){
        super("ClientServiceException ||| " + message);
    }

}
