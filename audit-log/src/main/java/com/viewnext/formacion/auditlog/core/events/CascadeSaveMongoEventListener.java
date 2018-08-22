package com.viewnext.formacion.auditlog.core.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.util.ReflectionUtils;

/**
 * 
 * audit-log
 * com.viewnext.formacion.auditlog.core.events
 * 
 * CascadeSaveMongoEventListener
 * 
 * @author Sergio Pinilla (Viewnext/IBM)
 * @version 0.1
 * @since 22 ago. 2018
 *
 */
public class CascadeSaveMongoEventListener extends AbstractMongoEventListener<Object> {

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public void onBeforeConvert(final BeforeConvertEvent<Object> event) {
        final Object source = event.getSource();
        ReflectionUtils.doWithFields(source.getClass(), new CascadeCallback(source, mongoOperations));
    }
}