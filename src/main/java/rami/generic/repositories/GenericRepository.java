package rami.generic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericRepository<E, ID> extends JpaRepository<E, ID>, JpaSpecificationExecutor<E> {

    // Si E extiende de Base Entity se pueden usar estos metodos
//    default E softDeleteById(ID id) {
//        Optional<E> optionalE = this.findById(id);
//        if (optionalE.isPresent()) {
//            optionalE.get().setIsActive(false);
//            this.save(optionalE.get());
//        } else {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found any entity with id " + id);
//        }
//        return null;
//    }

//    default E reactivateById(ID id) {
//        Optional<E> optionalE = this.findById(id);
//        if (optionalE.isPresent()) {
//            optionalE.get().setIsActive(true);
//            this.save(optionalE.get());
//        } else {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found any entity with id " + id);
//        }
//        return null;
//    }
}
