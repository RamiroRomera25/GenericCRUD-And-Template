package rami.generic.annotations;

import jdk.jfr.Experimental;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;


@Experimental
public @interface GenericController {
    @AliasFor(annotation = Component.class)
    String value() default "";

    // Configuraciones adicionales
    boolean enablePagination() default true;
    boolean enableReactivation() default true;
}
