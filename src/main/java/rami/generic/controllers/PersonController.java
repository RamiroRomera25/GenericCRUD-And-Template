package rami.generic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rami.generic.controllers.genericSegregation.basicCRUD.ControllerCreate;
import rami.generic.controllers.genericSegregation.basicCRUD.ControllerGetAllList;
import rami.generic.controllers.genericSegregation.basicCRUD.ControllerGetById;
import rami.generic.controllers.genericSegregation.compositeUniqueAtt.ControllerGetByCompositeUniqueAtt;
import rami.generic.controllers.genericSegregation.compositeUniqueAtt.ControllerUpdateByCompositeUniqueAtt;
import rami.generic.controllers.genericSegregation.uniqueAtt.ControllerGetByUniqueAtt;
import rami.generic.dtos.person.PersonDtoPost;
import rami.generic.entities.PersonEntity;
import rami.generic.models.PersonModel;
import rami.generic.services.PersonService;

@RestController
@RequestMapping("/v5/person")
public class PersonController
implements ControllerGetById<PersonEntity, Long, PersonModel, PersonService>,
           ControllerGetAllList<PersonEntity, Long, PersonModel, PersonService>,
           ControllerCreate<PersonEntity, Long, PersonModel, PersonDtoPost, PersonService>,
           ControllerGetByCompositeUniqueAtt<PersonEntity, Long, PersonModel, PersonService>,
           ControllerGetByUniqueAtt<PersonEntity, Long, PersonModel, PersonService>,
           ControllerUpdateByCompositeUniqueAtt<PersonEntity, Long, PersonModel, PersonDtoPost, PersonService>
{
    @Autowired
    private PersonService personService;

    @Override
    public PersonService getService() {
        return personService;
    }
}
