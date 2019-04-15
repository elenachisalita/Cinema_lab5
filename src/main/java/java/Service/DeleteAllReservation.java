package java.Service;

import java.Domain.Entity;
import java.Repository.IRepository;

import java.util.List;

public class DeleteAllReservation<T extends Entity> extends UndoRedoOperation {
    private List<T> deletedList;

    DeleteAllReservation(IRepository<T> repository, List<T> deletedList) {
        super(repository);
        this.deletedList = deletedList;
    }

    @Override
    public void doUndo() {
        for (T deletedEntity : deletedList) {
            repository.insert(deletedEntity);
        }
    }

    @Override
    public void doRedo() {
        for (T deletedEntity : deletedList) {
            repository.remove(deletedEntity.getId());
        }
    }
}
