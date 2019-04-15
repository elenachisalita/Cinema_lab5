package java.UI;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javax.swing.table.TableColumn;
import javax.swing.text.TableView;
import java.Domain.Client;
import java.Service.ClientService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShowClientsByBonusPointsController {


    public TableView tableViewOrderedClients;
    public TableColumn tableColumnIdClient;
    public TableColumn tableColumnLastNameClient;
    public TableColumn tableColumnFirstNameClient;
    public TableColumn tableColumnCnpClient;
    public TableColumn tableColumnDateOfBirthClient;
    public TableColumn tableColumnDateOfRegistrationClient;
    public TableColumn tableColumnBonusPointsCard;
    private ClientService clientService;

    private ObservableList<Client> clients = FXCollections.observableArrayList();

    public void setService(ClientService clientService) {
        this.clientService = clientService;
    }

    @FXML
    private void initialize() {
        Platform.runLater(() -> {
            try {
                List<Client> clientsOrdered = clientService.sortedCardsByBonusPoints();

                clients.addAll(clientsOrdered);
                tableViewOrderedClients.setItems(clients);
            } catch (RuntimeException e) {
                Logger logger = Logger.getLogger(getClass().getName());
                logger.log(Level.SEVERE, "Failed to create new Window: Movies By Reservations.", e);
            }
        });
    }


}
