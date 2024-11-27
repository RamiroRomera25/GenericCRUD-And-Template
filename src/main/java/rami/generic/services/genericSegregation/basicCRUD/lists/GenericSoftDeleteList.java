package rami.generic.services.genericSegregation.basicCRUD.lists;

import org.modelmapper.ModelMapper;
import rami.generic.entities.base.BaseEntity;
import rami.generic.repositories.GenericRepository;
import rami.generic.services.genericSegregation.basicCRUD.GenericGetById;

import java.util.ArrayList;
import java.util.List;

public interface GenericSoftDeleteList<E extends BaseEntity, I, M> extends GenericGetById<E, I, M> {
    ModelMapper getMapper();

    GenericRepository<E, I> getRepository();

    Class<M> modelClass();

    default List<M> delete(List<I> ids) {
        return changeActiveStatus(ids, false);
    }

    default List<M> reactivate(List<I> ids) {
        return changeActiveStatus(ids, true);
    }

    private List<M> changeActiveStatus(List<I> ids, boolean isActive) {
        List<M> modelList = new ArrayList<>();
        for (I id : ids) {
            E entity = this.getById(id);
            entity.setIsActive(isActive);
            modelList.add(getMapper().map(entity, modelClass()));
        }
        return modelList;
    }
}
