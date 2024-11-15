package ar.edu.utn.frc.tup.lciii.templateSpring.annotations;

import jdk.jfr.Experimental;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;


@Experimental
public @interface GenericController {
    @AliasFor(annotation = Component.class)
    String value() default "";

    // Configuraciones adicionales si son necesarias
    boolean enablePagination() default true;
    boolean enableReactivation() default true;
}
