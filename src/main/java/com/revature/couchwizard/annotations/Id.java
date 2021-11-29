package com.revature.couchwizard.annotations;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented


// TODO: MAYBE generate a random UUID here by default?
public @interface Id {
    public String columnName() default "";
}
