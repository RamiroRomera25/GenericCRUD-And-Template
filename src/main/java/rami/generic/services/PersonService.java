package rami.generic.services;

import org.springframework.stereotype.Service;
import rami.generic.dtos.person.PersonDtoPost;
import rami.generic.entities.PersonEntity;
import rami.generic.models.PersonModel;
import rami.generic.services.genericSegregation.basicCRUD.ServiceCreate;
import rami.generic.services.genericSegregation.basicCRUD.ServiceGetAllList;
import rami.generic.services.genericSegregation.basicCRUD.ServiceGetById;
import rami.generic.services.genericSegregation.compositeUniqueAtt.ServiceGetByCompositeUniqueAtt;
import rami.generic.services.genericSegregation.compositeUniqueAtt.ServiceUpdateByCompositeUniqueAtt;
import rami.generic.services.genericSegregation.uniqueAtt.ServiceGetByUniqueAtt;

import java.util.UUID;

@Service
public interface PersonService
extends ServiceGetAllList<PersonEntity, Long, PersonModel>,
        ServiceGetById<PersonEntity, Long, PersonModel>,
        ServiceCreate<PersonEntity, Long, PersonModel, PersonDtoPost>,
        ServiceGetByUniqueAtt<PersonEntity, Long, PersonModel>,
        ServiceGetByCompositeUniqueAtt<PersonEntity, Long, PersonModel>,
        ServiceUpdateByCompositeUniqueAtt<PersonEntity, Long, PersonModel, PersonDtoPost>
{
}
