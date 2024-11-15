package ar.edu.utn.frc.tup.lciii.templateSpring.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
class DummyRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DummyRepository dummyRepository;
}
