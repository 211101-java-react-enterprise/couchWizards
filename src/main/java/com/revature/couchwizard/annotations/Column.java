package com.revature.couchwizard.annotations;

/*
    The following annotation allows for a column to be set to interact with abstractly
 */

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented

public @interface Column {
    public String columnName() default "";
    public boolean isStatic() default false;
}
