package rami.generic.controllers.genericSegregation.basicCRUD;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rami.generic.services.genericSegregation.basicCRUD.GenericGetAllPage;

public interface ControllerGetAllPage<E, I, M, SERVICE extends GenericGetAllPage<E, I, M>> {

    SERVICE getService();

    @GetMapping("/page")
    default ResponseEntity<Page<M>> getAll(@RequestParam(required = false, defaultValue = "0") int page,
                                           @RequestParam(required = false, defaultValue = "10") int size,
                                           @RequestParam(required = false, defaultValue = "id") String sortBy,
                                           @RequestParam(required = false, defaultValue = "true") boolean isAscending) {
        Sort.Direction direction = isAscending ? Sort.Direction.ASC : Sort.Direction.DESC;
        return ResponseEntity.ok(getService().getAll(PageRequest.of(page, size, Sort.by(direction, sortBy))));
    }
}
