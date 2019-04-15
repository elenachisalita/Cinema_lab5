package java.Service;

import java.Domain.Client;
import java.Domain.Movie;
import java.Repository.IRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

public class ClientService {

    private IRepository<Client> clientRepository;

    private Stack<UndoRedoOperation<Movie>> undoableOperations = new Stack<>();
    private Stack<UndoRedoOperation<Movie>> redoableeOperations = new Stack<>();


    public ClientService(IRepository<Client> clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void insert(String id, String lastName, String firstName, String CNP, String dateOfBirth, String dateOfRegistration, int bonusPoints) {
       Client client = new Client(id, lastName, firstName, CNP, dateOfBirth, dateOfRegistration, bonusPoints);
        List<Client> all = new ArrayList<>(clientRepository.getAll());
        for (Client cardToTestCNP : all) {
            if (CNP.equals(cardToTestCNP.getCNP())) {
                throw new ClientServiceException(String.format("The %s CNP already exists", CNP));
            }
        }
        clientRepository.insert(client);
        undoableOperations.add(new AddOperation<>(clientRepository, client));
        redoableeOperations.clear();
    }
    public void update(String id, String lastName, String firstName, String CNP, String dateOfBirth, String dateOfRegistration, int bonusPoints) {
        Client actualClient= clientRepository.getById(id);
        Client clientUpdate = new Client(id, lastName, firstName, CNP, dateOfBirth, dateOfRegistration, bonusPoints);
        List<Client> all = new ArrayList<>(clientRepository.getAll());
        for (Client clientToTestCNP : all) {
            if (CNP.equals(clientToTestCNP.getCNP()) && !CNP.equals(clientUpdate.getCNP())) {
                throw new ClientServiceException(String.format("The %s CNP already exists", CNP));
            }
        }
        clientRepository.update(clientUpdate);
        undoableOperations.add(new UpdateOperation(clientRepository, clientUpdate, actualClient));
        redoableeOperations.clear();
    }

    public void remove(String id) {
        Client client = clientRepository.getById(id);
        clientRepository.remove(id);
        undoableOperations.add(new DeleteOperation<>(clientRepository, client));
        redoableeOperations.clear();
    }

    public List<Client> getAll() {
        return clientRepository.getAll();
    }

    public IRepository<Client> getClientRepository() {
        return clientRepository;
    }


    public List<Client> fullTextSearch(String text) {
        List<Client> found = new ArrayList<>();
        for (Client c : clientRepository.getAll()) {
            if ((c.getId().contains(text) || c.getLastName().contains(text) || c.getFirstName().contains(text) ||
                    c.getCNP().contains(text) || c.getDateOfBirth().contains(text) || c.getDateOfRegistration().contains(text) ||
                    Integer.toString(c.getBonusPoints()).contains(text)) && !found.contains(c)) {
                found.add(c);
            }
        }
        return found;
    }



    public List<Client> sortedCardsByBonusPoints(){
        List<Client> cardsOrdered = clientRepository.getAll();
        cardsOrdered.sort(Comparator.comparing(Client::getBonusPoints).reversed());

        return cardsOrdered;
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
}
