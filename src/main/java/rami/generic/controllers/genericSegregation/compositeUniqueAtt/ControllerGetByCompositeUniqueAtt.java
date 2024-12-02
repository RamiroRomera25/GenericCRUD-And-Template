package rami.generic.controllers.genericSegregation.compositeUniqueAtt;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import rami.generic.services.genericSegregation.compositeUniqueAtt.GenericGetByCompositeUniqueAtt;

import java.util.Map;

public interface ControllerGetByCompositeUniqueAtt<E, I, M, SERVICE extends GenericGetByCompositeUniqueAtt<E, I, M>> {

    SERVICE getService();

    @PostMapping("/unique")
    default ResponseEntity<M> getByCompositeUniqueAtt(@RequestBody Map<String, Object> fields) {
        return ResponseEntity.ok(getService().getModelByCompositeUniqueFields(fields));
    }
}
