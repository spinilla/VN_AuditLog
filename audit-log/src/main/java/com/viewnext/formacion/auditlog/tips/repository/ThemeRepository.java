package com.viewnext.formacion.auditlog.tips.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import com.viewnext.formacion.auditlog.core.model.Theme;

/**
 * com.viewnext.formacion.auditlog.tips.repository
 * 
 * TipRepository
 * 
 * @author Sergio Pinilla (Viewnext/IBM)
 * @version 0.1
 * @since 21 ago. 2018
 *
 */
@RepositoryRestResource(collectionResourceRel = "theme", path = "theme")
public interface ThemeRepository extends MongoRepository<Theme, ObjectId> {
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    Theme save(Theme s);
}

