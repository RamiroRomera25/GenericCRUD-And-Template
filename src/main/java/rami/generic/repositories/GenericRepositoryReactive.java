package rami.generic.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import rami.generic.entities.DummyEntity;

public interface GenericRepositoryReactive extends ReactiveCrudRepository<DummyEntity, Long> {
}
