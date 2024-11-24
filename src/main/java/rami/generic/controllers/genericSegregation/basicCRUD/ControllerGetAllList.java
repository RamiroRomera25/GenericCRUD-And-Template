package rami.generic.controllers.genericSegregation.basicCRUD;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import rami.generic.entities.DummyEntity;
import rami.generic.models.DummyModel;
import rami.generic.services.genericSegregation.basicCRUD.GenericGetAllList;
import rami.generic.services.genericSegregation.basicCRUD.GenericGetById;

import java.util.List;

public interface ControllerGetAllList<E, I, M> {

    GenericGetAllList<E, I, M> getService();

    @GetMapping("")
    default ResponseEntity<List<M>> getAll() {
        return ResponseEntity.ok(getService().getAll());
    }
}
