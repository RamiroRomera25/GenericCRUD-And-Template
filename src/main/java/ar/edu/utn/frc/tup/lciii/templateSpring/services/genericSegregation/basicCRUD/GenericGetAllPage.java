package ar.edu.utn.frc.tup.lciii.templateSpring.services.genericSegregation.basicCRUD;

import ar.edu.utn.frc.tup.lciii.templateSpring.repositories.GenericRepository;
import ar.edu.utn.frc.tup.lciii.templateSpring.repositories.specs.SpecificationBuilder;
import ar.edu.utn.frc.tup.lciii.templateSpring.services.genericSegregation.GenericMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public interface GenericGetAllPage<E, I, M, DTOFILTER> extends GenericMapper<DTOFILTER> {

    ModelMapper getMapper();

    GenericRepository<E, I> getRepository();

    SpecificationBuilder<E> specificationBuilder();

    default Page<M> getAll(Pageable pageable) {
        Page<E> pageEntity = getRepository().findAll(pageable);
        if (!pageEntity.isEmpty()) {
            return getMapper().map(pageEntity, new TypeToken<Page<M>>() {
            }.getType());
        } else {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No content retrieved.");
        }
    }

    default Page<M> getAll(Pageable pageable, DTOFILTER filter) {
        Page<E> pageEntity = getRepository().findAll(specificationBuilder()
                        .withDynamicFilter(this.getFilterMap(filter))
                        .build(),
                pageable);
        if (!pageEntity.isEmpty()) {
            return getMapper().map(pageEntity, new TypeToken<Page<M>>() {
            }.getType());
        } else {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No content retrieved.");
        }
    }
}
