package rami.generic.repositories;

import org.springframework.stereotype.Repository;
import rami.generic.entities.DummyEntity;
import rami.generic.entities.PersonEntity;

import java.util.UUID;

@Repository
public interface PersonRepository extends GenericRepository<PersonEntity, Long> {
}
