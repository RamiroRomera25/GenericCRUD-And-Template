package ar.edu.utn.frc.tup.lciii.templateSpring.services.genericSegregation;

import ar.edu.utn.frc.tup.lciii.templateSpring.entities.base.BaseEntity;
import ar.edu.utn.frc.tup.lciii.templateSpring.repositories.GenericRepository;
import org.modelmapper.ModelMapper;

public interface GenericSoftDelete<E extends BaseEntity, I, M> extends GenericGetById<E, I, M> {
    ModelMapper getMapper();

    GenericRepository<E, I> getRepository();

    Class<M> modelClass();

    default M delete(I id) {
        return changeActiveStatus(id, false);
    }

    default M reactivate(I id) {
        return changeActiveStatus(id, true);
    }

    private M changeActiveStatus(I id, boolean isActive) {
        E entity = this.getById(id);
        entity.setIsActive(isActive);
        return getMapper().map(getRepository().save(entity), modelClass());
    }
}
