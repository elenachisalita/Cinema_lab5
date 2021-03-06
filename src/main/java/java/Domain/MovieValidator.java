package java.Domain;



import java.util.Calendar;

public class MovieValidator implements IValidator<Movie> {
    /**
     * validates a movie
     * @param movie to validate
     * @throws MovieValidatorException if there are validation errors
     */
    public void validate(Movie movie) {

        String errors = "";
        if (movie.getPrice() <= 0) {
            errors += "The price must be greater than 0!\n";
        }

        if (movie.getYear() < 0 || movie.getYear() > Calendar.getInstance().get(Calendar.YEAR)+1) {
            errors += "The movie's year must be valid! \n";
        }

        if (!errors.isEmpty()) {
            throw new MovieValidatorException(errors);
        }
    }
}