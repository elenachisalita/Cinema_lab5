
package java.Service;

import java.Domain.Movie;

public class movieByReservationsVM {
    private Movie movie;
    private int bookings;

    public movieByReservationsVM(Movie movie, int bookings) {
        this.movie = movie;
        this.bookings = bookings;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getReservations() {
        return bookings;
    }
}