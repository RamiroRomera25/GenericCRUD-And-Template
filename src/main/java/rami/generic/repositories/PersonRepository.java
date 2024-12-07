package rami.generic.repositories;

import org.springframework.stereotype.Repository;
import rami.generic.entities.PersonEntity;

@Repository
public interface PersonRepository extends GenericRepository<PersonEntity, Long> {
}
