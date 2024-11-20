package rami.generic.services.genericSegregation;

import org.springframework.util.ReflectionUtils;

import java.util.HashMap;
import java.util.Map;

public interface GenericMapper {
    default Map<String, Object> getFilterMap(Object filter) {
        Map<String, Object> filterMap = new HashMap<>();

        ReflectionUtils.doWithFields(filter.getClass(), field -> {
            ReflectionUtils.makeAccessible(field);
            Object value = field.get(filter);
            if (value != null) {
                filterMap.put(field.getName(), value);
            }
        });

        return filterMap;
    }
}
