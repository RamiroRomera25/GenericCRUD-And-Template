package ar.edu.utn.frc.tup.lciii.templateSpring.services.genericSegregation;

import ar.edu.utn.frc.tup.lciii.templateSpring.entities.base.BaseEntity;
import ar.edu.utn.frc.tup.lciii.templateSpring.repositories.GenericRepository;
import org.modelmapper.ModelMapper;

public interface GenericSoftDeleteByUniqueAtt<E extends BaseEntity, I, M> extends GenericFilterByUniqueAtt<E, I, M> {
    ModelMapper getMapper();

    GenericRepository<E, I> getRepository();

    Class<M> modelClass();

    default M delete(String field, Object value) {
        return changeActiveStatus(field, value, false);
    }

    default M reactivate(String field, Object value) {
        return changeActiveStatus(field, value, true);
    }

    private M changeActiveStatus(String field, Object value, boolean isActive) {
        E entity = this.getByUniqueField(field, value);
        entity.setIsActive(isActive);
        return getMapper().map(getRepository().save(entity), modelClass());
    }
}
