package ar.edu.utn.frc.tup.lciii.templateSpring.services.genericSegregation;

import ar.edu.utn.frc.tup.lciii.templateSpring.repositories.GenericRepository;
import ar.edu.utn.frc.tup.lciii.templateSpring.repositories.specs.SpecificationBuilder;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

public interface GenericFilterByUniqueAtt<E, I, M> {
    ModelMapper getMapper();

    GenericRepository<E, I> getRepository();

    Class<E> entityClass();

    Class<M> modelClass();

    SpecificationBuilder<E> specificationBuilder();

    default E getByUniqueField(String uniqueField, Object value) {
        return getRepository().findOne(specificationBuilder().uniqueValue(uniqueField, value).build())
                .orElseThrow(() -> new EntityNotFoundException(
                    entityClass().getSimpleName() + " not found with " + uniqueField + ": " + value));
    }

    default E getByCompositeUniqueFields(Map<String, Object> uniqueFields) {
        return getRepository().findOne(specificationBuilder().compositeUniqueValues(uniqueFields).build())
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
