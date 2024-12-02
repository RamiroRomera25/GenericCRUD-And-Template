package rami.generic.controllers.genericSegregation.compositeUniqueAtt;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import rami.generic.entities.base.BaseEntity;
import rami.generic.services.genericSegregation.compositeUniqueAtt.ServiceSoftDeleteByCompositeUniqueAtt;
import rami.generic.services.genericSegregation.uniqueAtt.ServiceSoftDeleteByUniqueAtt;

import java.util.Map;

public interface ControllerSoftDeleteByCompositeUniqueAtt<E extends BaseEntity, I, M, SERVICE extends ServiceSoftDeleteByCompositeUniqueAtt<E, I, M>> {

    SERVICE getService();

    @DeleteMapping("/unique")
    default ResponseEntity<M> deleteByCompositeUniqueAtt(@RequestBody Map<String, Object> fields) {
        return ResponseEntity.ok(getService().delete(fields));
    }

    @PatchMapping("/unique/reactivate")
    default ResponseEntity<M> reactivateByCompositeUniqueAtt(@RequestBody Map<String, Object> fields) {
        return ResponseEntity.ok(getService().reactivate(fields));
    }
}
