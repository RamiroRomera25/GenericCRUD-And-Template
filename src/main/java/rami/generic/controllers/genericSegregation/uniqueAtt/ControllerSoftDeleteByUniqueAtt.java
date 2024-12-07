package rami.generic.controllers.genericSegregation.uniqueAtt;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import rami.generic.entities.base.BaseEntity;
import rami.generic.services.genericSegregation.uniqueAtt.ServiceSoftDeleteByUniqueAtt;

public interface ControllerSoftDeleteByUniqueAtt<E extends BaseEntity, I, M, SERVICE extends ServiceSoftDeleteByUniqueAtt<E, I, M>> {

    SERVICE getService();

    @DeleteMapping("/unique/{value}")
    default ResponseEntity<M> deleteByCompositeUniqueAtt(@RequestParam(required = false, defaultValue = "id") String field,
                                                         @PathVariable Object value) {
        return ResponseEntity.ok(getService().delete(field, value));
    }

    @PatchMapping("/unique/{value}/reactivate")
    default ResponseEntity<M> reactivateByCompositeUniqueAtt(@RequestParam(required = false, defaultValue = "id") String field,
                                                             @PathVariable Object value) {
        return ResponseEntity.ok(getService().reactivate(field, value));
    }
}
