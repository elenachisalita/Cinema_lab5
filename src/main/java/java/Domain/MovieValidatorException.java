package java.Domain;

public class MovieValidatorException extends RuntimeException{

    public MovieValidatorException(String message) {
        super("MovieValidatiorException ||| " + message);
    }

}
