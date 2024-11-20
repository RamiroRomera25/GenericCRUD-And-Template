package rami.generic.services.genericSegregation.basicCRUD;

import org.modelmapper.ModelMapper;
import rami.generic.repositories.GenericRepository;

public interface GenericUpdate<E, I, M, DTOPUT> extends GenericGetById<E, I, M> {

    ModelMapper getMapper();

    GenericRepository<E, I> getRepository();

    Class<M> modelClass();

//    @Deprecated
//    default M update(DTOPUT dtoPut, I id) {
//        E entity = this.getModelById(id);
//        if (optionalEntity.isPresent()) {
//            E entity = optionalEntity.get();
//            getMapper().map(dtoPut, entity);
//            return getMapper().map(getRepository().save(entity), modelClass());
//        } else {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found any object with id: " + id);
//        }
//    }

    default M update(DTOPUT dtoPut, I id) {
        E entity = this.getById(id);
        getMapper().map(dtoPut, entity);
        return getMapper().map(getRepository().save(entity), modelClass());
    }
}
