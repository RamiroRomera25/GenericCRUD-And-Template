package rami.generic.services.genericSegregation.basicCRUD;

import org.modelmapper.ModelMapper;
import rami.generic.repositories.GenericRepository;


public interface ServiceCreate<E, I, M, DTOPOST> {
    ModelMapper getMapper();

    GenericRepository<E, I> getRepository();

    Class<E> entityClass();

    Class<M> modelClass();

    default M create(DTOPOST dtoPost) {
        E entityToSave = getMapper().map(dtoPost, entityClass());
        return getMapper().map(getRepository().save(entityToSave), modelClass());
    }

    default M createWithEntity(E entity) {
        E entityToSave = getMapper().map(entity, entityClass());
        return getMapper().map(getRepository().save(entityToSave), modelClass());
    }
}
