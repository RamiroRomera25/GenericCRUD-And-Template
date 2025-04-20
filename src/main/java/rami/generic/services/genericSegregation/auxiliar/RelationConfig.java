package rami.generic.services.genericSegregation.auxiliar;

import lombok.Getter;
import org.springframework.util.ReflectionUtils;
import rami.generic.services.genericSegregation.basicCRUD.ServiceGetById;

import java.lang.reflect.Field;

public class RelationConfig<R, RI> {
    @Getter
    private final String fieldName;
    private final ServiceGetById<R, RI, ?> service;
    private final RI id;

    public RelationConfig(String fieldName, ServiceGetById<R, RI, ?> service, RI id) {
        this.fieldName = fieldName;
        this.service = service;
        this.id = id;
    }

    public void configure(Object entity) throws NoSuchFieldException, IllegalAccessException {
        R relatedEntity = service.getById(id);

        Field field = entity.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(entity, relatedEntity);
    }
}
