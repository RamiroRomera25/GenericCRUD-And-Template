package rami.generic.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface GenericRepositoryReactive<E, I> extends ReactiveCrudRepository<E, I> {
}
