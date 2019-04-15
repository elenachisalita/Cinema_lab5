import com.sun.glass.ui.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Controller;

import java.Domain.*;
import java.Repository.IRepository;
import java.Service.ClientService;
import java.Service.MovieService;
import java.Service.ReservationService;

import static javafx.application.Application.launch;

public class main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Resources/mainWindow.fxml"));
        Parent root = fxmlLoader.load();

        Controller controller = fxmlLoader.getController();

        IValidator<Movie> movieValidator = new MovieValidator();
        IValidator<Client> clientValidator = new ClientValidator();
        IValidator<Reservation> reservationValidator = new ReservationValidator();

        IRepository<Movie> moviesRepository = new JsonFileRepository<>(movieValidator, "movies.json", Movie.class);
        IRepository<Client> cardsRepository = new JsonFileRepository<>(clientValidator, "clients.json", Client.class);
        IRepository<Reservation> bookingsRepository = new JsonFileRepository<>(reservationValidator, "reservations.json", Reservation.class);


        MovieService movieService = new MovieService(moviesRepository, bookingsRepository);
        ClientService clientService = new ClientService(cardsRepository);
        ReservationService reservationService = new ReservationService(bookingsRepository, moviesRepository, cardsRepository);


        controller.setServices(movieService, clientService, reservationService);

        primaryStage.setTitle("Cinema manager");
        primaryStage.setScene(new Scene(root, 1100, 600));
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }

}