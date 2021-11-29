package com.revature.couchwizard.annotations;


/*
    This annotation exists to allow a table to be specified for postgres manipulation
 */


import java.lang.annotation.*;

// Annotation is pointed at classes with .TYPE, is available at runtime, and is documented in javadoc.
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented


public @interface Table {
    public String tableName() default "";
}
