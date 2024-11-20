package rami.generic.services.genericSegregation.basicCRUD;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import rami.generic.repositories.GenericRepository;
import rami.generic.repositories.specs.SpecificationBuilder;
import rami.generic.services.genericSegregation.GenericMapper;

import java.util.List;

public interface GenericGetAllList<E, I, M, DTOFILTER> extends GenericMapper {
    ModelMapper getMapper();

    GenericRepository<E, I> getRepository();

    SpecificationBuilder<E> specificationBuilder();

    default List<M> getAll() {
        List<E> entityList = getRepository().findAll();
        if (!entityList.isEmpty()) {
            return getMapper().map(entityList, new TypeToken<List<M>>() {}.getType());
        } else {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No content retrieved.");
        }
    }

    default List<M> getAll(DTOFILTER filter) {
        List<E> entityList =
                getRepository()
                .findAll(specificationBuilder()
                        .withDynamicFilter(this.getFilterMap(filter))
                        .build());
        if (!entityList.isEmpty()) {
            return getMapper().map(entityList, new TypeToken<List<M>>() {}.getType());
        } else {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No content retrieved.");
        }
    }
}
