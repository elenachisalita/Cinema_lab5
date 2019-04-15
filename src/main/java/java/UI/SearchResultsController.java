package java.UI;

import javafx.stage.Stage;

import java.Domain.Client;
import java.Domain.Movie;
import java.Domain.Reservation;
import java.Service.ClientService;
import java.Service.MovieService;
import java.Service.ReservationService;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SearchResultsController {

    public Label result;
    public TextField textToSearch;
    public Button btnSearch;
    public Button btnCancel;
    private MovieService movieService;
    private ClientService clientService;
    private ReservationService reservationService;

    public void setService(MovieService movieService, ClientService clientService, ReservationService reservationService) {
        this.movieService = movieService;
        this.clientService = clientService;
        this.reservationService = reservationService;
    }

    public void btnCancelClick(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    public void btnSearchClick(ActionEvent actionEvent) {
        String txt = textToSearch.getText();
        String found = txt + " found here:\n" + movieSearch(txt) + "\n" + clientSearch(txt) + "\n" + reservationSearch(txt);
        if(txt.length()>=1)
            result.setText(found);
    }

    private String reservationSearch(String text) {
        String reservationsTextFound = "";
        for (Reservation b : reservationService.fullTextSearch(text)) {
            reservationsTextFound += b + "\n";
        }
        return reservationsTextFound;
    }

    private String clientSearch(String text) {
        String clientTextFound = "";
        for (Client c : clientService.fullTextSearch(text)) {
            clientTextFound += c + "\n";
        }
        return clientTextFound;
    }

    private String movieSearch(String text) {
        String moviesTextFound = "";
        for (Movie m : movieService.fullTextSearch(text)) {
            moviesTextFound += m + "\n";
        }
        return moviesTextFound;
    }
}
}
