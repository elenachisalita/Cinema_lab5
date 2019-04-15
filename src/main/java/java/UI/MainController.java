package java.UI;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.table.TableColumn;
import javax.swing.text.TableView;
import java.Domain.Client;
import java.Domain.Movie;
import java.Domain.Reservation;
import java.Service.ClientService;
import java.Service.MovieService;
import java.Service.ReservationService;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;




public class MainController {

    public TableView tableViewMovies;
    public TableColumn tableColumnIdMovie;
    public TableColumn tableColumnTitleMovie;
    public TableColumn tableColumnPriceMovie;
    public TableColumn tableColumnYearMovie;
    public TableColumn tableColumnAvalibleMovie;

    public TableView tableViewClients;
    public TableColumn tableColumnIdClient;
    public TableColumn tableColumnLastNameClient;
    public TableColumn tableColumnFirstNameClient;
    public TableColumn tableColumnCnpClient;
    public TableColumn tableColumnDateOfBirthClient;
    public TableColumn tableColumnDateOfRegistrationClient;
    public TableColumn tableColumnBonusPointsClient;

    public TableView tableViewReservations;
    public TableColumn tableColumnIdReservation;
    public TableColumn tableColumnIdMovieReservation;
    public TableColumn tableColumnIdClientReservation;
    public TableColumn tableColumnDateOfReservation;
    public TableColumn tableColumnTimeOfReservation;

    private MovieService movieService;
    private ClientService clientService;
    private ReservationService reservationService;

    private ObservableList<Movie> movies = FXCollections.observableArrayList();
    private ObservableList<Client> clients = FXCollections.observableArrayList();
    private ObservableList<Reservation> reservations = FXCollections.observableArrayList();


    public void setServices(MovieService movieService, ClientService clientService, ReservationService reservationService) {
        this.movieService = movieService;
        this.clientService = clientService;
        this.reservationService = reservationService;
    }

    @FXML
    private void initialize() {
        Platform.runLater(this::run);
    }

    public void btnAddMovieClick(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/movieAdd.fxml"));
        upsertMovie(fxmlLoader, "Movie add");
    }


    public void btnMovieDeleteClick(ActionEvent actionEvent) {
        Movie selected = (Movie) tableViewMovies.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                movieService.remove(selected.getId());
                movies.clear();
                movies.addAll(movieService.getAll());
            } catch (RuntimeException rex) {
                Common.showValidationError(rex.getMessage());
            }
        }
    }

    public void btnUpdateMovieClick(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/movieUpdate.fxml"));
        upsertMovie(fxmlLoader, "Movie update");
    }

    public void upsertMovie(FXMLLoader fxmlLoader, String title) {
        try {
            Scene scene = new Scene(fxmlLoader.load(), 600, 200);
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            MovieController controller = fxmlLoader.getController();
            controller.setService(movieService);
            stage.showAndWait();
            movies.clear();
            movies.addAll(movieService.getAll());
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window: Movie update.", e);
        }
    }


    public void btnAddCardClick(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/clientAdd.fxml"));
        upsertCard(fxmlLoader, "Client add");
    }


    public void btnUpdateCardClick(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/clientUpdate.fxml"));
        upsertCard(fxmlLoader, "Client update");
    }


    public void upsertCard(FXMLLoader fxmlLoader, String title) {
        try {
            Scene scene = new Scene(fxmlLoader.load(), 600, 300);
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            ClientController controller = fxmlLoader.getController();
            controller.setService(clientService);
            stage.showAndWait();
            clients.clear();
            clients.addAll(clientService.getAll());
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window: Card ", e);
        }
    }

    public void btnDeleteClientClick(ActionEvent actionEvent) {
        Client selected = (Client) tableViewClients.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                clientService.remove(selected.getId());
                clients.clear();
                clients.addAll(clientService.getAll());
            } catch (RuntimeException rex) {
                Common.showValidationError(rex.getMessage());
            }
        }
    }


    public void btnAddReservationClick(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/reservationAdd.fxml"));
        upsertReservation(fxmlLoader, "Reservation add");
    }


    public void btnUpdateReservationClick(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/reservationUpdate.fxml"));
        upsertReservation(fxmlLoader, "Reservation update");
    }


    public void upsertReservation(FXMLLoader fxmlLoader, String title) {
        try {
            Scene scene = new Scene(fxmlLoader.load(), 600, 300);
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            ReservationController controller = fxmlLoader.getController();
            controller.setService(reservationService);
            stage.showAndWait();
            reservations.clear();
            reservations.addAll(reservationService.getAll());

            clients.clear();
            clients.addAll(clientService.getAll());
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window: Booking ", e);
        }
    }

    public void btnDeleteReservationClick(ActionEvent actionEvent) {
        Reservation selected = (Reservation) tableViewReservations.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                reservationService.remove(selected.getId());
                reservations.clear();
                reservations.addAll(reservationService.getAll());

                clients.clear();
                clients.addAll(clientService.getAll());
            } catch (RuntimeException rex) {
                Common.showValidationError(rex.getMessage());
            }
        }
    }

    public void searchClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/searchResults.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
            Stage stage = new Stage();
            stage.setTitle("Full Text Search");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            SearchResultsController controller = fxmlLoader.getController();
            controller.setService(movieService, clientService, reservationService);
            stage.showAndWait();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window: Full Text Search add.", e);
        }
    }



    public void moviesByReservations(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/showMoviesOrderedByReservations.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 600, 600);
            Stage stage = new Stage();
            stage.setTitle("Movies ordered by reservations");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            ShowMoviesOrderedByReservationsController controller = fxmlLoader.getController();
            controller.setService(movieService);
            stage.showAndWait();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window: Movies ordered by reservations add.", e);
        }
    }

    public void clientsByBonusPoints(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/showClientsByBonusPoints.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 900, 600);
            Stage stage = new Stage();
            stage.setTitle("Clients ordered by bonus points");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            ShowClientsByBonusPointsController controller = fxmlLoader.getController();
            controller.setService(clientService);
            stage.showAndWait();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window: Clients ordered by bonus points add.", e);
        }
    }



    public void btnMovieUndoClick(ActionEvent actionEvent) {
        movieService.undo();
        movies.clear();
        movies.addAll(movieService.getAll());
    }

    public void btnMovieRedoClick(ActionEvent actionEvent) {
        movieService.redo();
        movies.clear();
        movies.addAll(movieService.getAll());
    }

    public void btnClientUndoClick(ActionEvent actionEvent) {
        clientService.undo();
        clients.clear();
        clients.addAll(clientService.getAll());
    }

    public void btnClientRedoClick(ActionEvent actionEvent) {
        clientService.redo();
        clients.clear();
        clients.addAll(clientService.getAll());
    }

    public void btnReservationUndoClick(ActionEvent actionEvent) {
        reservationService.undo();
        reservations.clear();
        reservations.addAll(reservationService.getAll());
    }

    public void btnReservatuinRedoClick(ActionEvent actionEvent) {
        reservationService.redo();
        reservations.clear();
        reservations.addAll(reservationService.getAll());
    }

    private void run() {
        movies.addAll(movieService.getAll());
        tableViewMovies.setItems(movies);
        clients.addAll(clientService.getAll());
        tableViewClients.setItems(clients);
        reservations.addAll(reservationService.getAll());
        tableViewReservations.setItems(reservations);
    }
}
