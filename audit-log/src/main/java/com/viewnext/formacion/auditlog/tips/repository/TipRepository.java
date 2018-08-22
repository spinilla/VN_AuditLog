package com.viewnext.formacion.auditlog.tips.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.viewnext.formacion.auditlog.core.model.Tip;

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
@RepositoryRestResource(collectionResourceRel = "tip", path = "tip")
public interface TipRepository extends MongoRepository<Tip, ObjectId> {

    List<Tip> findByTitle(@Param("title") String title);
    
}

