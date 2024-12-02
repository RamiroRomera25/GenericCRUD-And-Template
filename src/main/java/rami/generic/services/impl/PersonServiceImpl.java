package rami.generic.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import rami.generic.dtos.person.PersonDtoPost;
import rami.generic.entities.PersonEntity;
import rami.generic.models.PersonModel;
import rami.generic.repositories.GenericRepository;
import rami.generic.repositories.PersonRepository;
import rami.generic.repositories.specs.SpecificationBuilder;
import rami.generic.services.PersonService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private SpecificationBuilder<PersonEntity> specificationBuilder;

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


    @Override
    public PersonModel create(PersonDtoPost dtoPost) {


        if (this.getByUniqueField("email", dtoPost.getEmail()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Este mail ya esta registado");
        }

        Map<String, Object> fields = new HashMap<>();
        fields.put("documentType", dtoPost.getDocumentType());
        fields.put("documentNumber", dtoPost.getDocumentNumber());
        if (this.getByCompositeUniqueFields(fields) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El documento ya se encuentra registado.");
        }

        return PersonService.super.create(dtoPost);
    }
}
