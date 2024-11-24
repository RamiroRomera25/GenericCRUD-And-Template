package rami.generic.controllers.genericSegregation.filters;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import rami.generic.services.genericSegregation.basicCRUD.GenericGetAllList;
import rami.generic.services.genericSegregation.filters.GenericGetAllListFilter;

import java.util.List;

public interface ControllerGetAllListFilter<E, I, M, DTOFILTER> {

    GenericGetAllListFilter<E, I, M, DTOFILTER> getService();

    @GetMapping("/filters")
    default ResponseEntity<List<M>> getAllFilter(@RequestBody DTOFILTER filters) {
        return ResponseEntity.ok(getService().getAll(filters));
    }
}
