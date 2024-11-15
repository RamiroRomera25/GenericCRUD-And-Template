package ar.edu.utn.frc.tup.lciii.templateSpring.annotations;


import ar.edu.utn.frc.tup.lciii.templateSpring.services.GenericCRUDService_V3;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

public class GenericControllerProccessor implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        GenericController annotation = AnnotationUtils.findAnnotation(bean.getClass(), GenericController.class);

        if (annotation != null) {
            // Verificar si el bean tiene el método getService() requerido
            try {
                Method getServiceMethod = bean.getClass().getMethod("getService");

                // Añadir métodos CRUD si no existen
                addCRUDMethodsIfNotExists(bean.getClass());
            } catch (NoSuchMethodException e) {
                throw new IllegalStateException("Clase anotada con @GenericCRUDController debe implementar getService()", e);
            }
        }

        return bean;
    }

    private void addCRUDMethodsIfNotExists(Class<?> beanClass) {
        // Método para agregar dinámicamente anotaciones de métodos REST si no existen
        // (Implementación simplificada, en la práctica usarías bibliotecas de bytecode como ByteBuddy)

        // Ejemplo de cómo podrías verificar y añadir métodos
        if (!hasMethodWithAnnotation(beanClass, GetMapping.class, "")) {
            // Lógica para añadir método GET all
            try {
                beanClass.getDeclaredMethod("getAll", Integer.class, Integer.class, Boolean.class);
                // Añadir anotación @GetMapping si el método existe pero no tiene anotación
            } catch (NoSuchMethodException e) {
                // Podrías lanzar una excepción o manejar de otra forma
            }
        }

        // Similar para otros métodos CRUD
    }

    private boolean hasMethodWithAnnotation(Class<?> beanClass, Class<?> annotationClass, String path) {
        return Arrays.stream(beanClass.getDeclaredMethods())
                .anyMatch(method -> method.isAnnotationPresent((Class<? extends Annotation>) annotationClass));
    }
}
