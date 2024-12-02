package rami.generic.controllers.genericSegregation.uniqueAtt;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import rami.generic.services.genericSegregation.uniqueAtt.ServiceGetByUniqueAtt;
import rami.generic.services.genericSegregation.uniqueAtt.ServiceUpdateByUniqueAtt;

public interface ControllerUpdateByUniqueAtt<E, I, M, DTOPUT, SERVICE extends ServiceUpdateByUniqueAtt<E, I, M, DTOPUT>> {

    SERVICE getService();

    @PutMapping("/unique/{value}")
    default ResponseEntity<M> updateByCompositeUniqueAtt(@RequestParam String field,
                                                         @PathVariable Object value,
                                                         @RequestBody DTOPUT dtoPut) {
        return ResponseEntity.ok(getService().update(dtoPut, field, value));
    }
}
