package com.revature.couchwizard.annotations;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented


public @interface Id {
    public String columnName() default "";
}
