package com.viewnext.formacion.auditlog.tips.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.viewnext.formacion.auditlog.core.model.UserApp;

/**
 * 
 * com.viewnext.formacion.auditlog.tips.repository
 * 
 * TipRepository
 * 
 * @author Sergio Pinilla (Viewnext/IBM)
 * @version 0.1
 * @since 21 ago. 2018
 *
 */
@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserAppRepository extends MongoRepository<UserApp, ObjectId> {
    
}

