package rami.generic.services.genericSegregation.compositeUniqueAtt;

import org.modelmapper.ModelMapper;
import rami.generic.entities.base.BaseEntity;
import rami.generic.repositories.GenericRepository;

import java.util.Map;

public interface GenericSoftDeleteByCompositeUniqueAtt<E extends BaseEntity, I, M> extends GenericGetByCompositeUniqueAtt<E, I, M> {
    ModelMapper getMapper();

    GenericRepository<E, I> getRepository();

    Class<M> modelClass();

    default M delete(Map<String, Object> uniqueFiels) {
        return changeActiveStatus(uniqueFiels, false);
    }

    default M reactivate(Map<String, Object> uniqueFiels) {
        return changeActiveStatus(uniqueFiels, true);
    }

    private M changeActiveStatus(Map<String, Object> uniqueFiels, boolean isActive) {
        E entity = this.getByCompositeUniqueFields(uniqueFiels);
        entity.setIsActive(isActive);
        return getMapper().map(getRepository().save(entity), modelClass());
    }
}
