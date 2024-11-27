package rami.generic.controllers.genericSegregation.basicCRUD;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import rami.generic.entities.base.BaseEntity;
import rami.generic.services.genericSegregation.basicCRUD.GenericSoftDelete;
import rami.generic.services.genericSegregation.basicCRUD.GenericSoftDeleteList;

import java.util.List;

public interface ControllerSoftDeleteList<E extends BaseEntity, I, M, SERVICE extends GenericSoftDeleteList<E, I, M>> {

    SERVICE getService();

    @DeleteMapping("/list")
    default ResponseEntity<List<M>> delete(@RequestBody List<I> ids) {
        return ResponseEntity.ok(getService().delete(ids));
    }

    @PatchMapping("/{id}/reactivate")
    default ResponseEntity<List<M>> reactivate(@RequestBody List<I> ids) {
        return ResponseEntity.ok(getService().reactivate(ids));
    }
}
