package rami.generic.controllers.genericSegregation.basicCRUD;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rami.generic.services.genericSegregation.basicCRUD.GenericGetAllList;

import java.util.List;

public interface ControllerGetAllList<E, I, M, SERVICE extends GenericGetAllList<E, I, M>> {

    SERVICE getService();

    @GetMapping("")
    default ResponseEntity<List<M>> getAll(@RequestParam(required = false, defaultValue = "id") String sortBy,
                                           @RequestParam(required = false, defaultValue = "true") boolean isAscending) {
        return ResponseEntity.ok(getService().getAll());
    }
}
