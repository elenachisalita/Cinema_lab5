package java.Service;

public class ReservationServiceException extends RuntimeException {
    public ReservationServiceException(String message){
        super("BookingServiceException ||| " + message);
    }
}
