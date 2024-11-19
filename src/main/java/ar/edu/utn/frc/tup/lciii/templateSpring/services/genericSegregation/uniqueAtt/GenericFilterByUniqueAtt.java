package ar.edu.utn.frc.tup.lciii.templateSpring.services.genericSegregation.uniqueAtt;

import ar.edu.utn.frc.tup.lciii.templateSpring.repositories.GenericRepository;
import ar.edu.utn.frc.tup.lciii.templateSpring.repositories.specs.SpecificationBuilder;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;

import java.util.Optional;

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

    default M getModelByUniqueField(String uniqueField, Object value) {
        return getRepository().findOne(specificationBuilder().uniqueValue(uniqueField, value).build())
                .map((entity) -> getMapper().map(entity, modelClass()))
                .orElseThrow(() -> new EntityNotFoundException(
                        entityClass().getSimpleName() + " not found with " + uniqueField + ": " + value));
    }
}
