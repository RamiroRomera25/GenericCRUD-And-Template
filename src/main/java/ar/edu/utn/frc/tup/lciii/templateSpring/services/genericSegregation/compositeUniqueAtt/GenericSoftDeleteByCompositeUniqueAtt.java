package ar.edu.utn.frc.tup.lciii.templateSpring.services.genericSegregation.compositeUniqueAtt;

import ar.edu.utn.frc.tup.lciii.templateSpring.entities.base.BaseEntity;
import ar.edu.utn.frc.tup.lciii.templateSpring.repositories.GenericRepository;
import org.modelmapper.ModelMapper;

import java.util.Map;

public interface GenericSoftDeleteByCompositeUniqueAtt<E extends BaseEntity, I, M> extends GenericFilterByCompositeUniqueAtt<E, I, M> {
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
