package rami.generic.services;

import org.springframework.stereotype.Service;
import rami.generic.dtos.person.PersonDtoPost;
import rami.generic.entities.PersonEntity;
import rami.generic.models.PersonModel;
import rami.generic.services.genericSegregation.basicCRUD.GenericCreate;
import rami.generic.services.genericSegregation.basicCRUD.GenericGetAllList;
import rami.generic.services.genericSegregation.basicCRUD.GenericGetById;
import rami.generic.services.genericSegregation.compositeUniqueAtt.GenericGetByCompositeUniqueAtt;
import rami.generic.services.genericSegregation.uniqueAtt.GenericGetByUniqueAtt;

import java.util.UUID;

@Service
public interface PersonService
extends GenericGetAllList<PersonEntity, UUID, PersonModel>,
        GenericGetById<PersonEntity, UUID, PersonModel>,
        GenericCreate<PersonEntity, UUID, PersonModel, PersonDtoPost>,
        GenericGetByUniqueAtt<PersonEntity, UUID, PersonModel>,
        GenericGetByCompositeUniqueAtt<PersonEntity, UUID, PersonModel>
{
}
