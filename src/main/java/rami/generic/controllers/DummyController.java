package rami.generic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rami.generic.controllers.genericSegregation.basicCRUD.ControllerCreate;
import rami.generic.controllers.genericSegregation.basicCRUD.ControllerGetAllList;
import rami.generic.controllers.genericSegregation.basicCRUD.ControllerGetAllPage;
import rami.generic.controllers.genericSegregation.basicCRUD.ControllerGetById;
import rami.generic.controllers.genericSegregation.basicCRUD.ControllerSoftDelete;
import rami.generic.controllers.genericSegregation.basicCRUD.ControllerUpdate;
import rami.generic.controllers.genericSegregation.filters.ControllerGetAllListFilter;
import rami.generic.controllers.genericSegregation.filters.ControllerGetAllPageFilter;
import rami.generic.dtos.DummyDtoFilter;
import rami.generic.dtos.DummyDtoPost;
import rami.generic.dtos.DummyDtoPut;
import rami.generic.entities.DummyEntity;
import rami.generic.models.DummyModel;
import rami.generic.services.DummyService;
import rami.generic.services.genericSegregation.basicCRUD.GenericSoftDelete;

import java.util.List;

@RestController
@RequestMapping("/v5/dummy")
public class DummyController
implements ControllerGetById<DummyEntity, Long, DummyModel>,
           ControllerGetAllList<DummyEntity, Long, DummyModel>,
           ControllerGetAllPage<DummyEntity, Long, DummyModel>,
           ControllerGetAllPageFilter<DummyEntity, Long, DummyModel, DummyDtoFilter>,
           ControllerGetAllListFilter<DummyEntity, Long, DummyModel, DummyDtoFilter>,
           ControllerSoftDelete<DummyEntity, Long, DummyModel>,
           ControllerUpdate<DummyEntity, Long, DummyModel, DummyDtoPut>,
           ControllerCreate<DummyEntity, Long, DummyModel, DummyDtoPost> {

    @Autowired
    private DummyService dummyService;


    @GetMapping("/like")
    public ResponseEntity<List<DummyModel>> getDummiesLike(DummyDtoFilter filter) {
        return ResponseEntity.ok(dummyService.dummyLike(filter));
    }

    @Override
    public GenericSoftDelete getService() {
        return dummyService;
    }
}
