package ar.edu.utn.frc.tup.lciii.templateSpring.services.genericSegregation.basicCRUD;

import ar.edu.utn.frc.tup.lciii.templateSpring.repositories.GenericRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public interface GenericGetById<E, I, M> {
    ModelMapper getMapper();

    GenericRepository<E, I> getRepository();

    Class<M> modelClass();

    default E getById(I id) {
        return getRepository().findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Not found any object with id: " + id));
    }

    default M getModelById(I id) {
        return getRepository().findById(id)
            .map(entity -> getMapper().map(entity, modelClass()))
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Not found any object with id: " + id));
    }
}
