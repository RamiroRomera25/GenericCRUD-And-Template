package rami.generic.controllers;

import jdk.jfr.Experimental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rami.generic.controllers.genericSegregation.basicCRUD.ControllerCreate;
import rami.generic.controllers.genericSegregation.basicCRUD.ControllerCreateList;
import rami.generic.controllers.genericSegregation.basicCRUD.ControllerGetAllList;
import rami.generic.controllers.genericSegregation.basicCRUD.ControllerGetAllPage;
import rami.generic.controllers.genericSegregation.basicCRUD.ControllerGetById;
import rami.generic.controllers.genericSegregation.basicCRUD.ControllerSoftDelete;
import rami.generic.controllers.genericSegregation.basicCRUD.ControllerSoftDeleteList;
import rami.generic.controllers.genericSegregation.basicCRUD.ControllerUpdate;
import rami.generic.controllers.genericSegregation.filters.ControllerGetAllListFilter;
import rami.generic.controllers.genericSegregation.filters.ControllerGetAllPageFilter;
import rami.generic.dtos.DummyDtoFilter;
import rami.generic.dtos.DummyDtoPost;
import rami.generic.dtos.DummyDtoPut;
import rami.generic.entities.DummyEntity;
import rami.generic.models.DummyModel;
import rami.generic.services.DummyService;

import java.util.List;

@RestController
@RequestMapping("/v5/dummy")

@Experimental

public class DummyController
implements ControllerGetById<DummyEntity, Long, DummyModel, DummyService>,
           ControllerGetAllList<DummyEntity, Long, DummyModel, DummyService>,
           ControllerGetAllPage<DummyEntity, Long, DummyModel, DummyService>,
           ControllerGetAllPageFilter<DummyEntity, Long, DummyModel, DummyDtoFilter, DummyService>,
           ControllerGetAllListFilter<DummyEntity, Long, DummyModel, DummyDtoFilter, DummyService>,
           ControllerSoftDelete<DummyEntity, Long, DummyModel, DummyService>,
           ControllerUpdate<DummyEntity, Long, DummyModel, DummyDtoPut, DummyService>,
           ControllerCreate<DummyEntity, Long, DummyModel, DummyDtoPost, DummyService>,
           ControllerCreateList<DummyEntity, Long, DummyModel, DummyDtoPost, DummyService>,
           ControllerSoftDeleteList<DummyEntity, Long, DummyModel, DummyService>
{

    @Autowired
    private DummyService dummyService;

    @GetMapping("/like")
    public ResponseEntity<List<DummyModel>> getDummiesLike(DummyDtoFilter filter) {
        return ResponseEntity.ok(dummyService.dummyLike(filter));
    }

    @Override
    public DummyService getService() {
        return dummyService;
    }
}
