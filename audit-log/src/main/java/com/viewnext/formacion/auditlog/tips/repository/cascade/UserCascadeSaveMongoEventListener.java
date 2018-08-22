package com.viewnext.formacion.auditlog.tips.repository.cascade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

import com.viewnext.formacion.auditlog.core.model.Tip;

/**
 * audit-log
 * com.viewnext.formacion.auditlog.tips.repository.cascade
 * 
 * UserCascadeSaveMongoEventListener
 * 
 * @author Sergio Pinilla (Viewnext/IBM)
 * @version 0.1
 * @since 22 ago. 2018
 *
 */
public class UserCascadeSaveMongoEventListener extends AbstractMongoEventListener<Object> {
    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public void onBeforeConvert(final BeforeConvertEvent<Object> event) {
        final Object source = event.getSource();
        if ((source instanceof Tip) && (((Tip) source).getTheme() != null)) {
            mongoOperations.save(((Tip) source).getTheme());
        }
    }
}
