package rami.generic.services.genericSegregation.basicCRUD;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.transaction.annotation.Transactional;
import rami.generic.repositories.GenericRepository;

import java.util.List;


public interface GenericCreateList<E, I, M, DTOPOST> {
    ModelMapper getMapper();

    GenericRepository<E, I> getRepository();

    Class<M> modelClass();

    @Transactional
    default M createList(List<DTOPOST> dtoPosts) {
        List<E> entityToSave = getMapper().map(dtoPosts, new TypeToken<List<E>>() {}.getType());
        return getMapper().map(getRepository().saveAll(entityToSave), modelClass());
    }
}
