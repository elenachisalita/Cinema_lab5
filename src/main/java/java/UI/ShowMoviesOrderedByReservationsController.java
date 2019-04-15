package java.UI;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javax.swing.table.TableColumn;
import javax.swing.text.TableView;
import java.Service.MovieService;
import java.Service.movieByReservationsVM;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShowMoviesOrderedByReservationsController {

    public TableColumn tableColumnReservationsNumber;
    public TableView tableViewMovies;
    public TableColumn tableColumnNameMovie;
    private MovieService movieService;

    private ObservableList<movieByReservationsVM> movies = FXCollections.observableArrayList();

    public void setService(MovieService movieService) {
        this.movieService = movieService;
    }

    @FXML
    private void initialize() {
        Platform.runLater(() -> {
            try {
                List<movieByReservationsVM> orderedMovies = movieService.sortByBookings();
                movies.addAll(orderedMovies);
                tableViewMovies.setItems(movies);
            } catch (RuntimeException e) {
                Logger logger = Logger.getLogger(getClass().getName());
                logger.log(Level.SEVERE, "Failed to create new Window: Movies By Reservations.", e);
            }
        });
    }
}
