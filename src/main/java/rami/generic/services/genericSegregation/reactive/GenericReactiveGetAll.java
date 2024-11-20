package rami.generic.services.genericSegregation.reactive;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import rami.generic.repositories.GenericRepository;
import rami.generic.repositories.GenericRepositoryReactive;
import rami.generic.repositories.specs.SpecificationBuilder;
import rami.generic.services.genericSegregation.GenericMapper;
import reactor.core.publisher.Flux;

import java.util.List;

public interface GenericReactiveGetAll<E, I, M, DTOFILTER> extends GenericMapper {
    ModelMapper getMapper();

    GenericRepositoryReactive<E, I> getRepository();

    SpecificationBuilder<E> specificationBuilder();

//    default Flux<M> getAll() {
//        Flux<E> entityList = getRepository().findAll();
//        if (!entityList.defaultIfEmpty()) {
//            return getMapper().map(entityList, new TypeToken<List<M>>() {}.getType());
//        } else {
//            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No content retrieved.");
//        }
//    }
//
//    default Flux<M> getAll(DTOFILTER filter) {
//        Flux<E> entityList =
//                getRepository()
//                        .findAll(specificationBuilder()
//                                .withDynamicFilter(this.getFilterMap(filter))
//                                .build());
//        if (!entityList.isEmpty()) {
//            return getMapper().map(entityList, new TypeToken<List<M>>() {}.getType());
//        } else {
//            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No content retrieved.");
//        }
//    }
}
