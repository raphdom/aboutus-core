package com.jrdevel.aboutus.core.common.view;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD,FIELD})
@Retention(RUNTIME)
public @interface Projection {
	
	public String entityName() default "";

}
