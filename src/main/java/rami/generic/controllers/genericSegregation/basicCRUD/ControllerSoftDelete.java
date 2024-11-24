package rami.generic.controllers.genericSegregation.basicCRUD;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import rami.generic.entities.base.BaseEntity;
import rami.generic.services.genericSegregation.basicCRUD.GenericSoftDelete;

public interface ControllerSoftDelete<E extends BaseEntity, I, M> {

    GenericSoftDelete<E, I, M> getService();

    @DeleteMapping("/{id}")
    default ResponseEntity<M> delete(@PathVariable I id) {
        return ResponseEntity.ok(getService().delete(id));
    }

    @PatchMapping("/{id}/reactivate")
    default ResponseEntity<M> reactivate(@PathVariable I id) {
        return ResponseEntity.ok(getService().reactivate(id));
    }
}
