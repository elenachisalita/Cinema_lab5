package java.Service;

import java.Domain.Client;
import java.Domain.Movie;
import java.Domain.Reservation;
import java.Repository.IRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ReservationService {

    private IRepository<Reservation> reservationRepository;
    private IRepository<Movie> movieRepository;
    private IRepository<Client> clientRepository;

    private Stack<UndoRedoOperation<Reservation>> undoableOperations = new Stack<>();
    private Stack<UndoRedoOperation<Reservation>> redoableeOperations = new Stack<>();

    public ReservationService(IRepository<Reservation> reservationRepository, IRepository<Movie> movieRepository, IRepository<Client> clientRepository) {
        this.reservationRepository = reservationRepository;
        this.movieRepository = movieRepository;
        this.clientRepository = clientRepository;
    }

    public void insert(String id, String idMovie, String idCard, String date, String time) {
        Movie movieSold = movieRepository.getById(idMovie);
        if (movieSold == null) {
            throw new ReservationServiceException("There is no movie with the given id!");
        }

        if (!movieSold.isAvalible()) {
            throw new ReservationServiceException("The movie isn't on the screen");
        }

        Reservation reservation = new Reservation(id, idMovie, idCard, date, time);
        reservationRepository.insert(reservation);
        Movie movie = movieRepository.getById(idMovie);

        movieRepository.update(movie);

        Client client = clientRepository.getById(idCard);

        if (client != null) {
            client.setBonusPoints((int) (client.getBonusPoints() + (movieSold.getPrice() / 10)));
            clientRepository.update(client);
        }

        undoableOperations.add(new AddOperation<>(reservationRepository, reservation));
        redoableeOperations.clear();
    }


    /**
     * updates a booking
     *
     * @param id      the id of booking we want to update
     * @param idMovie the id of the movie for booking update
     * @param idCard  the id of customer card booking update
     * @param date    the date booking update
     * @param time    the time booking update
     */
    public void update(String id, String idMovie, String idCard, String date, String time) {
        Reservation actualBooking = reservationRepository.getById(id);
        Reservation reservationUpdate = new Reservation(id, idMovie, idCard, date, time);
        reservationRepository.update(reservationUpdate);
        undoableOperations.add(new UpdateOperation(movieRepository, reservationUpdate, actualBooking));
        redoableeOperations.clear();
    }

    /**
     * removes a booking by id
     *
     * @param id the id of the booking we want to remove
     */
    public void remove(String id) {
        Reservation reservation = reservationRepository.getById(id);
        reservationRepository.remove(id);

        Client client = clientRepository.getById(reservation.getIdClient());
        Movie movieReservation = movieRepository.getById(reservation.getIdMovie());

        client.setBonusPoints((int) (client.getBonusPoints() - (movieReservation.getPrice() / 10)));
        clientRepository.update(client);

        undoableOperations.add(new DeleteOperation<>(reservationRepository, reservation));
        redoableeOperations.clear();
    }

    /**
     * list of all bookings
     *
     * @return an ArrayList list with all bookings
     */
    public List<Reservation> getAll() {
        return reservationRepository.getAll();
    }

    /**
     * searches a text in all bookings
     *
     * @param text the text to find
     * @return a list with all bookings where text was found
     */
    public List<Reservation> fullTextSearch(String text) {
        List<Reservation> found = new ArrayList<>();
        for (Reservation b : reservationRepository.getAll()) {
            if ((b.getId().contains(text) || b.getIdMovie().contains(text) || b.getIdClient().contains(text) ||
                    b.getDate().toString().contains(text) || b.getTime().toString().contains(text)) && !found.contains(b)) {
                found.add(b);
            }
        }
        return found;
    }



    public void undo() {
        if (!undoableOperations.empty()) {
            UndoRedoOperation<Reservation> lastOperation = undoableOperations.pop();
            lastOperation.doUndo();
            redoableeOperations.add(lastOperation);
        }
    }

    public void redo() {
        if (!redoableeOperations.empty()) {
            UndoRedoOperation<Reservation> lastOperation = redoableeOperations.pop();
            lastOperation.doRedo();
            undoableOperations.add(lastOperation);
        }
    }
}
