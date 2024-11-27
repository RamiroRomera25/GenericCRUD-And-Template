package rami.generic.controllers.genericSegregation.basicCRUD;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import rami.generic.services.genericSegregation.basicCRUD.GenericCreate;
import rami.generic.services.genericSegregation.basicCRUD.GenericCreateList;

import java.util.List;

public interface ControllerCreateList<E, I, M, DTOPOST, SERVICE extends GenericCreateList<E, I, M, DTOPOST>> {

    SERVICE getService();

    @PostMapping("/list")
    default ResponseEntity<M> create(@RequestBody @Valid List<DTOPOST> dtoPost) {
        return ResponseEntity.ok(getService().createList(dtoPost));
    }
}
