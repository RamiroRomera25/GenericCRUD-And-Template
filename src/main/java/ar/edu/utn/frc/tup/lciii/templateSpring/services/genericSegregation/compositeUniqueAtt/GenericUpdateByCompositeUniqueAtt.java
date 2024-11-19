package ar.edu.utn.frc.tup.lciii.templateSpring.services.genericSegregation.compositeUniqueAtt;

import ar.edu.utn.frc.tup.lciii.templateSpring.repositories.GenericRepository;
import ar.edu.utn.frc.tup.lciii.templateSpring.repositories.specs.SpecificationBuilder;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;

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
