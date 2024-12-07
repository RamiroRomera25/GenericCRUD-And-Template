package rami.generic.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import rami.generic.dtos.person.PersonDtoPost;
import rami.generic.entities.PersonEntity;
import rami.generic.enums.CidiLevel;
import rami.generic.enums.DocumentType;
import rami.generic.models.PersonModel;
import rami.generic.repositories.GenericRepository;
import rami.generic.repositories.PersonRepository;
import rami.generic.repositories.specs.SpecificationBuilder;
import rami.generic.services.PersonService;
import rami.generic.services.stateMachine.StateMachine;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private final ModelMapper modelMapper;

    private final PersonRepository personRepository;

    private final SpecificationBuilder<PersonEntity> specificationBuilder;

    StateMachine<CidiLevel, PersonModel> stateMachine = new StateMachine<>(CidiLevel.LEVEL_0);

    @Autowired
    public PersonServiceImpl(ModelMapper modelMapper,
                             PersonRepository repo,
                             SpecificationBuilder<PersonEntity> specBuilder) {
        this.modelMapper = modelMapper;
        this.personRepository = repo;
        this.specificationBuilder = specBuilder;

        stateMachine.addTransition(CidiLevel.LEVEL_0, CidiLevel.LEVEL_1, (state, params) -> {
            return this.upgradeLevel1((PersonEntity)params[0] ,(BigInteger) params[1], (DocumentType) params[2]);
        });

        stateMachine.addTransition(CidiLevel.LEVEL_1, CidiLevel.LEVEL_2, (state, params) -> {
            return this.upgradeLevel2((PersonEntity) params[0], (List<Long>) params[1]);
        });

        stateMachine.addTransition(CidiLevel.LEVEL_2, CidiLevel.SANCTION, (state, params) -> {
            return this.applySanction((PersonEntity) params[0]);
        });

        stateMachine.addTransition(CidiLevel.LEVEL_1, CidiLevel.SANCTION, (state, params) -> {
            return this.applySanction((PersonEntity) params[0]);
        });

        stateMachine.addTransition(CidiLevel.LEVEL_0, CidiLevel.SANCTION, (state, params) -> {
            return this.applySanction((PersonEntity) params[0]);
        });
    }

    //#region Override Generico
    @Override
    public Class<PersonEntity> entityClass() {
        return PersonEntity.class;
    }

    @Override
    public ModelMapper getMapper() {
        return modelMapper;
    }

    @Override
    public GenericRepository<PersonEntity, Long> getRepository() {
        return personRepository;
    }

    @Override
    public Class<PersonModel> modelClass() {
        return PersonModel.class;
    }

    @Override
    public SpecificationBuilder<PersonEntity> specificationBuilder() {
        return specificationBuilder;
    }
    //#endregion

    //#region Create
    @Override
    public PersonModel create(PersonDtoPost dtoPost) {
        if (this.getByUniqueField("email", dtoPost.getEmail()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Este mail ya esta registado");
        }

        return PersonService.super.create(dtoPost);
    }
    //#endregion

    //    public PersonModel upgradeLevel1(String dni, DocumentType documentType)
    public PersonModel upgradeLevel1(PersonEntity person, BigInteger dni, DocumentType documentType) {
        person.setDocumentNumber(dni);
        person.setDocumentType(documentType);
        return modelMapper.map(personRepository.save(person), PersonModel.class);
    }

    public PersonModel upgradeLevel2(PersonEntity person, List<Long> idFamiliarGroup) {
        person.setFamiliarGroup(this.getByIdList(idFamiliarGroup));
        return modelMapper.map(personRepository.save(person), PersonModel.class);
    }

    public PersonModel applySanction(PersonEntity person) {
        person.setEndSanction(LocalDateTime.now().plusDays(1L));
        return modelMapper.map(personRepository.save(person), PersonModel.class);
    }

    public PersonModel executeTransition(Long id, Object... args) {
        PersonEntity personEntity = this.getById(id);
        stateMachine.setCurrentState(personEntity.getLevel());
        return stateMachine.executeTransition(personEntity.getLevel(), args);
    }
}
