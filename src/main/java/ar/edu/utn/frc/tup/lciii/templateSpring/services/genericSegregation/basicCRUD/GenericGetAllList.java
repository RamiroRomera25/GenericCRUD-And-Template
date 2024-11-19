package ar.edu.utn.frc.tup.lciii.templateSpring.services.genericSegregation.basicCRUD;

import ar.edu.utn.frc.tup.lciii.templateSpring.repositories.GenericRepository;
import ar.edu.utn.frc.tup.lciii.templateSpring.repositories.specs.SpecificationBuilder;
import ar.edu.utn.frc.tup.lciii.templateSpring.services.genericSegregation.GenericMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public interface GenericGetAllList<E, I, M, DTOFILTER> extends GenericMapper<DTOFILTER> {
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
        List<E> entityList = getRepository().findAll(specificationBuilder()
                .withDynamicFilter(this.getFilterMap(filter))
                .build());
        if (!entityList.isEmpty()) {
            return getMapper().map(entityList, new TypeToken<List<M>>() {}.getType());
        } else {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No content retrieved.");
        }
    }
}
