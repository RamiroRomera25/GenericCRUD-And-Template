package ar.edu.utn.frc.tup.lciii.templateSpring.controllers;

import ar.edu.utn.frc.tup.lciii.templateSpring.dtos.DummyDtoFilter;
import ar.edu.utn.frc.tup.lciii.templateSpring.dtos.DummyDtoPost;
import ar.edu.utn.frc.tup.lciii.templateSpring.dtos.DummyDtoPut;
import ar.edu.utn.frc.tup.lciii.templateSpring.entities.DummyEntity;
import ar.edu.utn.frc.tup.lciii.templateSpring.models.DummyModel;
import ar.edu.utn.frc.tup.lciii.templateSpring.services.DummyService;
import ar.edu.utn.frc.tup.lciii.templateSpring.services.GenericCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v3/dummy/")
public class DummyController extends GenericController<DummyEntity, Long, DummyModel, DummyDtoPost, DummyDtoPut, DummyDtoFilter> {
    @Autowired
    private DummyService dummyService;

    @Override
    public GenericCRUDService<DummyEntity, Long, DummyModel, DummyDtoPost, DummyDtoPut, DummyDtoFilter> getService() {
        return dummyService;
    }
}
