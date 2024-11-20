package rami.generic.services.genericSegregation.compositeUniqueAtt;

import org.modelmapper.ModelMapper;
import rami.generic.repositories.GenericRepository;

import java.util.Map;

public interface GenericUpdateByCompositeUniqueAtt<E, I, M, DTOPUT> extends GenericFilterByCompositeUniqueAtt<E, I, M> {

    ModelMapper getMapper();

    GenericRepository<E, I> getRepository();

    Class<M> modelClass();

    // TODO: Pensar como pasar los atributos para no hacer logica en el controller y hacer el mapeo aca.
    default M update(DTOPUT dtoPut, Map<String, Object> uniqueFiels) {
        E entity = this.getByCompositeUniqueFields(uniqueFiels);
        getMapper().map(dtoPut, entity);
        return getMapper().map(getRepository().save(entity), modelClass());
    }
}
