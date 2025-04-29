package rami.generic.services.genericSegregation.basicCRUD;

import org.modelmapper.ModelMapper;
import rami.generic.repositories.GenericRepository;
import rami.generic.services.genericSegregation.utils.RelationConfig;


public interface ServiceCreate<E, I, M, DTOPOST> {
    ModelMapper getMapper();

    GenericRepository<E, I> getRepository();

    Class<E> entityClass();

    Class<M> modelClass();

    default M create(DTOPOST dtoPost) {
        E entityToSave = getMapper().map(dtoPost, entityClass());
        return getMapper().map(getRepository().save(entityToSave), modelClass());
    }

    default M createWithEntity(E entity) {
        E entityToSave = getMapper().map(entity, entityClass());
        return getMapper().map(getRepository().save(entityToSave), modelClass());
    }

    default M createWithRelations(DTOPOST dtoPost, RelationConfig<?, ?>... relations) {
        E entityToSave = getMapper().map(dtoPost, entityClass());

        // Establecer cada relación configurada
        for (RelationConfig<?, ?> relation : relations) {
            try {
                // Configurar la relación
                relation.configure(entityToSave);
            } catch (Exception e) {
                throw new RuntimeException("Error on relation: " + relation.getFieldName(), e);
            }
        }

        return getMapper().map(getRepository().save(entityToSave), modelClass());
    }
}
