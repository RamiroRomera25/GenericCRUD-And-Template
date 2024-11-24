package rami.generic.controllers.genericSegregation.filters;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import rami.generic.services.genericSegregation.basicCRUD.GenericGetAllPage;
import rami.generic.services.genericSegregation.filters.GenericGetAllListFilter;
import rami.generic.services.genericSegregation.filters.GenericGetAllPageFilter;

import java.util.List;

public interface ControllerGetAllPageFilter<E, I, M, DTOFILTER> {

    GenericGetAllPageFilter<E, I, M, DTOFILTER> getService();

    @GetMapping("/filters")
    default ResponseEntity<Page<M>> getAllFilter(@RequestBody DTOFILTER filters,
                                                 @RequestParam(required = false) int page,
                                                 @RequestParam(required = false) int size) {
        return ResponseEntity.ok(getService().getAll(PageRequest.of(page, size),filters));
    }
}
