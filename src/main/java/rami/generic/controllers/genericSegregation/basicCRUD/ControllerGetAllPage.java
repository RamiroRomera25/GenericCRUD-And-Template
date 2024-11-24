package rami.generic.controllers.genericSegregation.basicCRUD;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rami.generic.services.genericSegregation.basicCRUD.GenericGetAllPage;

public interface ControllerGetAllPage<E, I, M> {

    GenericGetAllPage<E, I, M> getService();

    @GetMapping("")
    default ResponseEntity<Page<M>> getAll(@RequestParam(required = false) int page,
                                           @RequestParam(required = false) int size) {
        return ResponseEntity.ok(getService().getAll(PageRequest.of(page, size)));
    }
}
