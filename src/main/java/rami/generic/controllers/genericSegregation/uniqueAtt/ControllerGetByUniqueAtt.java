package rami.generic.controllers.genericSegregation.uniqueAtt;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rami.generic.services.genericSegregation.uniqueAtt.GenericGetByUniqueAtt;

public interface ControllerGetByUniqueAtt<E, I, M, SERVICE extends GenericGetByUniqueAtt<E, I, M>> {

    SERVICE getService();

    @PostMapping("/unique/{value}")
    default ResponseEntity<M> getByCompositeUniqueAtt(@RequestParam String field,
                                                      @PathVariable Object value) {
        return ResponseEntity.ok(getService().getModelByUniqueField(field, value));
    }
}
