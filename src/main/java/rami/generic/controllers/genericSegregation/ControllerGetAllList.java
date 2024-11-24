package rami.generic.controllers.genericSegregation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import rami.generic.services.genericSegregation.basicCRUD.GenericGetAllList;

import java.util.List;

public interface ControllerGetAllList<E, I, M> {

    GenericGetAllList<E, I, M> getService();

    @GetMapping("")
    default ResponseEntity<List<M>> getAll() {
        return ResponseEntity.ok(getService().getAll());
    }
}
