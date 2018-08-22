package com.viewnext.formacion.auditlog.tips.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.security.core.context.SecurityContextHolder;

import com.viewnext.formacion.auditlog.core.model.BaseEntityData;
import com.viewnext.formacion.auditlog.core.model.Tip;

/**
 * com.viewnext.formacion.auditlog.tips.repository
 *
 * <p>TipCascadeSaveMongoEventListener
 *
 * @author Sergio Pinilla (Viewnext/IBM)
 * @version 0.1
 * @since 21 ago. 2018
 */
public class TipCascadeSaveMongoEventListener extends AbstractMongoEventListener<Object> {
  @Autowired private MongoOperations mongoOperations;

  @Override
  public void onBeforeConvert(BeforeConvertEvent<Object> event) {
    Object source = event.getSource();
    if (source instanceof BaseEntityData) {
      BaseEntityData baseEntity = (BaseEntityData) source;
      if (SecurityContextHolder.getContext() != null
          && SecurityContextHolder.getContext().getAuthentication() != null
          && SecurityContextHolder.getContext().getAuthentication().getName() != null) {
        String uname = SecurityContextHolder.getContext().getAuthentication().getName();
        if (uname != null) {
          if (baseEntity.getCreatedBy() == null) {
            baseEntity.setCreatedBy(uname);
          }
          baseEntity.setModifiedBy(uname);
        }
      }
    }

    if ((source instanceof Tip) && (((Tip) source).getTheme() != null)) {
      mongoOperations.save(((Tip) source).getTheme());
    }
  }
}
