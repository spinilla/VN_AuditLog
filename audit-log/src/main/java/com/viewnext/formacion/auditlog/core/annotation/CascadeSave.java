package com.viewnext.formacion.auditlog.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * audit-log
 * com.viewnext.formacion.auditlog.core.annotation
 * 
 * CascadeSave
 * 
 * @author Sergio Pinilla (Viewnext/IBM)
 * @version 0.1
 * @since 22 ago. 2018
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CascadeSave {
}
