package ar.edu.utn.frc.tup.lciii.templateSpring.services.genericSegregation.uniqueAtt;

import ar.edu.utn.frc.tup.lciii.templateSpring.repositories.GenericRepository;
import org.modelmapper.ModelMapper;

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
