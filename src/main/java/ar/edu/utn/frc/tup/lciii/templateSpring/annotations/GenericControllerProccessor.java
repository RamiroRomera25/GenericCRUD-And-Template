package ar.edu.utn.frc.tup.lciii.templateSpring.annotations;


import jdk.jfr.Experimental;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.GetMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

@Experimental
public class GenericControllerProccessor implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        GenericController annotation = AnnotationUtils.findAnnotation(bean.getClass(), GenericController.class);

        if (annotation != null) {
            try {
                Method getServiceMethod = bean.getClass().getMethod("getService");

                addCRUDMethodsIfNotExists(bean.getClass());
            } catch (NoSuchMethodException e) {
                throw new IllegalStateException("Clase anotada con @GenericCRUDController debe implementar getService()", e);
            }
        }

        return bean;
    }

    private void addCRUDMethodsIfNotExists(Class<?> beanClass) {

        if (!hasMethodWithAnnotation(beanClass, GetMapping.class, "")) {
            try {
                beanClass.getDeclaredMethod("getAll", Integer.class, Integer.class, Boolean.class);
            } catch (NoSuchMethodException e) {
            }
        }

        // Similar para otros métodos CRUD
    }

    private boolean hasMethodWithAnnotation(Class<?> beanClass, Class<?> annotationClass, String path) {
        return Arrays.stream(beanClass.getDeclaredMethods())
                .anyMatch(method -> method.isAnnotationPresent((Class<? extends Annotation>) annotationClass));
    }
}
