package java.Service;

import java.Domain.Movie;
import java.Domain.Reservation;
import java.Repository.IRepository;
import java.util.*;

public class MovieService {

    private IRepository<Movie> movieRepository;
    private IRepository<Reservation> reservationRepository;


    private Stack<UndoRedoOperation<Movie>> undoableOperations = new Stack<>();
    private Stack<UndoRedoOperation<Movie>> redoableeOperations = new Stack<>();


    public MovieService(IRepository<Movie> movieRepository, IRepository<Reservation> reservationIRepository) {
        this.movieRepository = movieRepository;
        this.reservationRepository = reservationIRepository;
    }

    public void insert(String id, String name, int year, double price, boolean avalible) {
        Movie movie = new Movie(id, name, year, price, avalible);
        movieRepository.insert(movie);
        undoableOperations.add(new AddOperation<>(movieRepository, movie));
        redoableeOperations.clear();
    }

    public void update(String id, String name, int year, double price, boolean onScreens) {
        Movie actualMovie = movieRepository.getById(id);
        Movie movieUpdate = new Movie(id, name, year, price, onScreens);
        movieRepository.update(movieUpdate);
        undoableOperations.add(new UpdateOperation(movieRepository, movieUpdate, actualMovie));
        redoableeOperations.clear();
    }

    public void remove(String id) {
        Movie movie = movieRepository.getById(id);
        movieRepository.remove(id);
        undoableOperations.add(new DeleteOperation<>(movieRepository, movie));
        redoableeOperations.clear();
    }

    public List<Movie> getAll() {
        return movieRepository.getAll();
    }

    /**
     * searches a text in all movies
     *
     * @param text the text to find
     * @return a list with all movies where text was found
     */
    public List<Movie> fullTextSearch(String text) {
        List<Movie> found = new ArrayList<>();
        for (Movie m : movieRepository.getAll()) {
            if ((m.getId().contains(text) || m.getTitle().contains(text) ||
                    Integer.toString(m.getYear()).contains(text) || Double.toString(m.getPrice()).contains(text) ||
                    Boolean.toString(m.isAvalible()).contains(text)) && !found.contains(m)) {
                found.add(m);
            }
        }
        return found;
    }

    public void undo() {
        if (!undoableOperations.empty()) {
            UndoRedoOperation<Movie> lastOperation = undoableOperations.pop();
            lastOperation.doUndo();
            redoableeOperations.add(lastOperation);

        }
    }

    public void redo() {
        if (!redoableeOperations.empty()) {
            UndoRedoOperation<Movie> lastOperation = redoableeOperations.pop();
            lastOperation.doRedo();
            undoableOperations.add(lastOperation);
        }
    }

    public List<movieByReservationsVM> sortByBookings() {
        Map<String,Integer> frequences = new HashMap<>();
        for (Reservation b: reservationRepository.getAll()) {
            String movieId = b.getIdMovie();
            if(frequences.containsKey(movieId)){
                frequences.put(movieId,frequences.get(movieId)+1);
            } else {
                frequences.put(movieId,1);
            }
        }

        List<movieByReservationsVM> orderedMovies = new ArrayList<>();
        for (String movieId:frequences.keySet()) {
            Movie movie = movieRepository.getById(movieId);
            orderedMovies.add(new movieByReservationsVM(movie,frequences.get(movieId)));

        }
        orderedMovies.sort((m1,m2) -> Integer.compare(m2.getReservations(), m1.getReservations()));
        return orderedMovies;
    }



}
