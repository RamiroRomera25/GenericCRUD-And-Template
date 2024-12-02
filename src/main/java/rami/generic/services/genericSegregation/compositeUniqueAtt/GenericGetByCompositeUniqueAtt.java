package rami.generic.services.genericSegregation.compositeUniqueAtt;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import rami.generic.repositories.GenericRepository;
import rami.generic.repositories.specs.SpecificationBuilder;

import java.util.Map;

public interface GenericGetByCompositeUniqueAtt<E, I, M> {
    ModelMapper getMapper();

    GenericRepository<E, I> getRepository();

    Class<E> entityClass();

    Class<M> modelClass();

    SpecificationBuilder<E> specificationBuilder();

    default E getByCompositeUniqueFields(Map<String, Object> uniqueFields) {
        return getRepository().findOne(specificationBuilder().compositeUniqueValues(uniqueFields).build())
                .orElseThrow(() -> new EntityNotFoundException(buildErrorMessage(uniqueFields)));
    }

    default M getModelByCompositeUniqueFields(Map<String, Object> uniqueFields) {
        return getRepository().findOne(specificationBuilder().compositeUniqueValues(uniqueFields).build())
                .map((entity) -> getMapper().map(entity, modelClass()))
                .orElseThrow(() -> new EntityNotFoundException(buildErrorMessage(uniqueFields)));
    }

    private String buildErrorMessage(Map<String, Object> uniqueFields) {
        String fields = uniqueFields.entrySet().stream()
                .map(entry -> entry.getKey() + " = " + entry.getValue())
                .reduce((field1, field2) -> field1 + ", " + field2)
                .orElse("Unknown fields");

        return String.format("%s entity not found for %s.",
                entityClass().getSimpleName(), fields);
    }
}
