package java.UI;

import javafx.scene.control.Spinner;
import javafx.stage.Stage;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.Domain.Client;
import java.Service.ClientService;
import java.awt.*;
import java.awt.event.ActionEvent;
public class ClientController {


    public TextField txtClientLastName;
    public TextField txtClientFirstname;
    public TextField txtCardCNP;
    public TextField txtClientDateOfBirth;
    public TextField txtClientDateOfRegistration;
    public TextField txtClientBonusPoints;
    public Button btnAdd;
    public Button btnUpdate;
    public Button btnCancel;
    public Spinner spnId;

    private ClientService clientService;

    public void setService(ClientService cardService) {
        this.clientService = clientService;
    }

    public void btnAddClick(ActionEvent actionEvent) {
        try {
            Client c = upsertClick();
            clientService.insert(c.getId(),c.getLastName(),c.getFirstName(),c.getCNP(),c.getDateOfBirth(),c.getDateOfRegistration(),c.getBonusPoints());
            btnCancelClick(actionEvent);
        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }
    }

    public void btnUpdateClick(ActionEvent actionEvent) {
        try {
            Client c = upsertClick();
            clientService.update(c.getId(),c.getLastName(),c.getFirstName(),c.getCNP(),c.getDateOfBirth(),c.getDateOfRegistration(),c.getBonusPoints());
            btnCancelClick(actionEvent);
        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }
    }

    public void btnCancelClick(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    private Client upsertClick(){
        try {
            String id = String.valueOf(spnId.getValue());
            String lastName = txtClientLastName.getText();
            String firstName = txtClientFirstname.getText();
            String CNP = txtCardCNP.getText();
            String dateOfBirth = txtClientDateOfBirth.getText();
            String dateOfRegistration = txtClientDateOfRegistration.getText();
            int bonusPoints = Integer.parseInt(txtClientBonusPoints.getText());

            return new Client(id, firstName, lastName, CNP, dateOfBirth, dateOfRegistration, bonusPoints);
        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }

        return null;

    }

}
