package rami.generic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rami.generic.dtos.DummyDtoFilter;
import rami.generic.dtos.DummyDtoPost;
import rami.generic.dtos.DummyDtoPut;
import rami.generic.entities.DummyEntity;
import rami.generic.models.DummyModel;
import rami.generic.services.DummyService;
import rami.generic.services.GenericCRUDService;

import java.util.List;

@RestController
@RequestMapping("/v5/dummy")
public class DummyController extends GenericController<DummyEntity, Long, DummyModel, DummyDtoPost, DummyDtoPut, DummyDtoFilter> {
    @Autowired
    private DummyService dummyService;

    @Override
    public GenericCRUDService<DummyEntity, Long, DummyModel, DummyDtoPost, DummyDtoPut, DummyDtoFilter> getService() {
        return dummyService;
    }

    @GetMapping("/like")
    public ResponseEntity<List<DummyModel>> getDummiesLike(DummyDtoFilter filter) {
        return ResponseEntity.ok(dummyService.dummyLike(filter));
    }
}
