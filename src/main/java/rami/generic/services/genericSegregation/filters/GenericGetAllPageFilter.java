package rami.generic.services.genericSegregation.filters;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import rami.generic.repositories.GenericRepository;
import rami.generic.repositories.specs.SpecificationBuilder;
import rami.generic.services.genericSegregation.GenericMapper;
import rami.generic.services.genericSegregation.GenericService;

public interface GenericGetAllPageFilter<E, I, M, DTOFILTER> extends GenericMapper, GenericService {

    ModelMapper getMapper();

    GenericRepository<E, I> getRepository();

    SpecificationBuilder<E> specificationBuilder();

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
