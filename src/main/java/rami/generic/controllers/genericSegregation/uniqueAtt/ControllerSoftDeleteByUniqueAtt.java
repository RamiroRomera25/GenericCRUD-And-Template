package rami.generic.controllers.genericSegregation.uniqueAtt;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import rami.generic.entities.base.BaseEntity;
import rami.generic.services.genericSegregation.uniqueAtt.ServiceSoftDeleteByUniqueAtt;
import rami.generic.services.genericSegregation.uniqueAtt.ServiceUpdateByUniqueAtt;

public interface ControllerSoftDeleteByUniqueAtt<E extends BaseEntity, I, M, SERVICE extends ServiceSoftDeleteByUniqueAtt<E, I, M>> {

    SERVICE getService();

    @PutMapping("/unique/{value}")
    default ResponseEntity<M> deleteByCompositeUniqueAtt(@RequestParam(required = false, defaultValue = "id") String field,
                                                         @PathVariable Object value) {
        return ResponseEntity.ok(getService().delete(field, value));
    }

    @PutMapping("/unique/{value}/reactivate")
    default ResponseEntity<M> reactivateByCompositeUniqueAtt(@RequestParam(required = false, defaultValue = "id") String field,
                                                             @PathVariable Object value) {
        return ResponseEntity.ok(getService().reactivate(field, value));
    }
}
