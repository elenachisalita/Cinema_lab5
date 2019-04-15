package java.UI;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;
import javafx.stage.Stage;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.Domain.Movie;
import java.Service.MovieService;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MovieController {

    public TextField txtMovieTitle;
    public TextField txtMovieYear;
    public TextField txtMoviePrice;
    public CheckBox chkOnScreen;
    public Button btnAdd;
    public Button btnUpdate;
    public Button btnCancel;
    public Spinner spnId;
    private MovieService movieService;

    public void setService(MovieService movieService) {
        this.movieService = movieService;
    }

    public void btnCancelClick(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    public void btnAddClick(ActionEvent actionEvent) {
        try {
            Movie m = upsertClick();
            movieService.insert(m.getId(),m.getTitle(),m.getYear(),m.getPrice(),m.isAvalible());
            btnCancelClick(actionEvent);
        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }
    }

    public void btnUpdateClick(ActionEvent actionEvent) {
        try {
            Movie m = upsertClick();
            movieService.update(m.getId(),m.getTitle(),m.getYear(),m.getPrice(),m.isAvalible());
            btnCancelClick(actionEvent);
        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }
    }

    private Movie upsertClick(){
        try {
            String id = String.valueOf(spnId.getValue());
            String title = txtMovieTitle.getText();
            int year = Integer.parseInt(txtMovieYear.getText());
            double price = Double.parseDouble(txtMoviePrice.getText());
            boolean onScreen = chkOnScreen.isSelected();

            return new Movie(id, title, year, price, onScreen);
        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }

        return null;

    }
}
