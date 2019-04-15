package java.Service;

import java.Domain.Entity;
import java.Repository.IRepository;
import java.Service.UndoRedoOperation;

public class AddOperation<T extends Entity> extends UndoRedoOperation {
    private T addedEntity;

    AddOperation(IRepository<T> repository, T addedEntity) {
        super(repository);
        this.addedEntity = addedEntity;
    }

    @Override
    public void doUndo() {
        repository.remove(addedEntity.getId());
    }

    @Override
    public void doRedo() {
        repository.insert(addedEntity);
    }
}