package rami.generic.controllers.genericSegregation.filters;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import rami.generic.services.genericSegregation.filters.GenericGetAllListFilter;

import java.util.List;

public interface ControllerGetAllListFilter<E, I, M, DTOFILTER, SERVICE extends GenericGetAllListFilter<E, I, M, DTOFILTER>> {

    SERVICE getService();

    @GetMapping("/filters")
    default ResponseEntity<List<M>> getAllFilter(@RequestBody DTOFILTER filters,
                                                 @RequestParam(required = false, defaultValue = "id") String sortBy,
                                                 @RequestParam(required = false, defaultValue = "true") boolean isAscending) {
        Sort.Direction direction = isAscending ? Sort.Direction.ASC : Sort.Direction.DESC;
        return ResponseEntity.ok(getService().getAll(filters, Sort.by(direction, sortBy)));
    }
}
