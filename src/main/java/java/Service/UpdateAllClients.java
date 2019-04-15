package java.Service;

import java.Domain.Entity;
import java.Repository.IRepository;

import java.util.List;

public class UpdateAllClients<T extends Entity> extends UndoRedoOperation {
    private List<T> updatedList;
    private List<T> actualList;

    UpdateAllClients(IRepository<T> repository, List<T> updatedList, List<T> actualList) {
        super(repository);
        this.updatedList = updatedList;
        this.actualList = actualList;
    }

    @Override
    public void doUndo() {
        for (T updatedEntity : actualList) {
            repository.update(updatedEntity);
        }
    }

    @Override
    public void doRedo() {
        for (T updatedEntity : updatedList) {
            repository.update(updatedEntity);
        }
    }
}
