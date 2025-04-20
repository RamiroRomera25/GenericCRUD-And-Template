package rami.generic.services.genericSegregation.uniqueAtt;

import org.modelmapper.ModelMapper;
import rami.generic.repositories.GenericRepository;
import rami.generic.repositories.specs.SpecificationBuilder;

import java.util.List;
import java.util.stream.Collectors;

public interface ServiceGetAllByUniqueAtt<E, I, M> {
    ModelMapper getMapper();

    GenericRepository<E, I> getRepository();

    Class<E> entityClass();

    Class<M> modelClass();

    SpecificationBuilder<E> specificationBuilder();

    default List<E> getAllByUniqueFields(String uniqueField, Object value) {
        return getRepository().findAll(specificationBuilder().uniqueValue(uniqueField, value).build());
    }

    default List<M> getAllModelByUniqueFields(String uniqueField, Object value) {
        return getRepository().findAll(specificationBuilder().uniqueValue(uniqueField, value).build()).stream()
                .map((entity) -> getMapper().map(entity, modelClass()))
                .collect(Collectors.toList());
    }
}
