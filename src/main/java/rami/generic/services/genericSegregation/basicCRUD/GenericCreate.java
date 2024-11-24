package rami.generic.services.genericSegregation.basicCRUD;

import org.modelmapper.ModelMapper;
import rami.generic.repositories.GenericRepository;
import rami.generic.services.genericSegregation.GenericService;

public interface GenericCreate<E, I, M, DTOPOST> extends GenericService {
    ModelMapper getMapper();

    GenericRepository<E, I> getRepository();

    Class<E> entityClass();

    Class<M> modelClass();

    default M create(DTOPOST dtoPost) {
        E entityToSave = getMapper().map(dtoPost, entityClass());
        return getMapper().map(getRepository().save(entityToSave), modelClass());
    }
}
