package rami.generic.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
class DummyRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DummyRepository dummyRepository;
}
