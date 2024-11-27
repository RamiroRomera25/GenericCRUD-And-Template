package rami.generic.controllers.genericSegregation.basicCRUD;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import rami.generic.services.genericSegregation.basicCRUD.GenericGetAllList;

import java.util.List;

public interface ControllerGetAllList<E, I, M, SERVICE extends GenericGetAllList<E, I, M>> {

    SERVICE getService();

    @GetMapping("")
    default ResponseEntity<List<M>> getAll() {
        return ResponseEntity.ok(getService().getAll());
    }
}
