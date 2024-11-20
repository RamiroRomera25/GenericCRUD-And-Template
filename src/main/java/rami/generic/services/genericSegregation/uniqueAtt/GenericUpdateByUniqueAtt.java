package rami.generic.services.genericSegregation.uniqueAtt;

import org.modelmapper.ModelMapper;
import rami.generic.repositories.GenericRepository;

public interface GenericUpdateByUniqueAtt<E, I, M, DTOPUT> extends GenericFilterByUniqueAtt<E, I, M> {
    ModelMapper getMapper();

    GenericRepository<E, I> getRepository();

    Class<M> modelClass();

    default M update(DTOPUT dtoPut, String field, Object value) {
        E entity = this.getByUniqueField(field, value);
        getMapper().map(dtoPut, entity);
        return getMapper().map(getRepository().save(entity), modelClass());
    }
}
