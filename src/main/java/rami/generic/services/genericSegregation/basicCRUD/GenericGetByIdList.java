package rami.generic.services.genericSegregation.basicCRUD;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import rami.generic.repositories.GenericRepository;

import java.util.ArrayList;
import java.util.List;

public interface GenericGetByIdList<E, I, M> {
    ModelMapper getMapper();

    GenericRepository<E, I> getRepository();

    Class<M> modelClass();

    default List<E> getByIdList(List<I> ids) {
        List<E> entityList = new ArrayList<>();
        for (I id : ids) {
            entityList.add(getRepository().findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Not found any object with id: " + id)));
        }
        return entityList;
    }

    default M getModelByIdList(List<I> ids) {
        List<E> entityList = new ArrayList<>();
        for (I id : ids) {
            entityList.add(getRepository().findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Not found any object with id: " + id)));
        }
        return getMapper().map(entityList, new TypeToken<List<M>>(){}.getType());
    }
}
