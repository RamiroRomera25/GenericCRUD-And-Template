package rami.generic.controllers.genericSegregation.compositeUniqueAtt;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import rami.generic.services.genericSegregation.compositeUniqueAtt.ServiceGetAllByCompositeUniqueAtt;

import java.util.List;
import java.util.Map;

public interface ControllerGetAllByCompositeUniqueAtt<E, I, M, SERVICE extends ServiceGetAllByCompositeUniqueAtt<E, I, M>> {

    SERVICE getService();

    @GetMapping("/unique/list")
    default ResponseEntity<List<M>> getAllByCompositeUniqueAtt(@RequestBody Map<String, Object> fields) {
        return ResponseEntity.ok(getService().getAllModelByCompositeUniqueFields(fields));
    }
}
