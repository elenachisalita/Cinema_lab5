package java.UI;

import javafx.scene.control.Spinner;
import javafx.stage.Stage;

import java.Domain.Reservation;
import java.Service.ReservationService;
import java.awt.*;
import java.awt.event.ActionEvent;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

public class ReservationController {


    public Spinner spnId;
    public Spinner spnIdMovie;
    public Spinner spnIdClient;
    public TextField txtReservationDate;
    public TextField txtReservationTime;

    public Button btnAdd;
    public Button btnUpdate;
    public Button btnCancel;
    private ReservationService reservationService;

    public void setService(ReservationService reservationService    ) {
        this.reservationService = reservationService;
    }

    public void btnAddClick(ActionEvent actionEvent) {
        try {
            Reservation b = upsertClick();

            reservationService.insert(b.getId(), b.getIdMovie(), b.getIdClient(), b.getDate(), b.getTime());
            btnCancelClick(actionEvent);
        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }
    }

    public void btnUpdateClick(ActionEvent actionEvent) {
        try {
            Reservation b = upsertClick();

            reservationService.update(b.getId(), b.getIdMovie(), b.getIdClient(), b.getDate(), b.getTime());
            btnCancelClick(actionEvent);
        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }
    }

    public void btnCancelClick(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    private Reservation upsertClick(){
        try {
            String id = String.valueOf(spnId.getValue());
            String idMovie = String.valueOf(spnIdMovie.getValue());
            String idClient= String.valueOf(spnIdClient.getValue());
            String date= String.valueOf(txtReservationDate.getText());
            String time= String.valueOf(txtReservationTime.getText());


            return new Reservation(id, idMovie, idClient, date, time);
        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }

        return null;

    }
}
