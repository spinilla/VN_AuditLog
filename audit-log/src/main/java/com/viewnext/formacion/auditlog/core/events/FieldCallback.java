package com.viewnext.formacion.auditlog.core.events;

import java.lang.reflect.Field;

import org.springframework.data.annotation.Id;
import org.springframework.util.ReflectionUtils;

/**
 * 
 * audit-log
 * com.viewnext.formacion.auditlog.core.events
 * 
 * FieldCallback
 * 
 * @author Sergio Pinilla (Viewnext/IBM)
 * @version 0.1
 * @since 22 ago. 2018
 *
 */
public class FieldCallback implements ReflectionUtils.FieldCallback {
    private boolean idFound;

    @Override
    public void doWith(final Field field) throws IllegalArgumentException, IllegalAccessException {
        ReflectionUtils.makeAccessible(field);

        if (field.isAnnotationPresent(Id.class)) {
            idFound = true;
        }
    }

    public boolean isIdFound() {
        return idFound;
    }
}
