package com.viewnext.formacion.auditlog.core.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * audit-log
 * com.viewnext.formacion.auditlog.core.model
 * 
 * BaseEntityData
 * 
 * @author Sergio Pinilla (Viewnext/IBM)
 * @version 0.1
 * @since 22 ago. 2018
 *
 */
@Data
public abstract class BaseEntityData implements Serializable, AuditorAware<String> {

  @Id
  @ApiModelProperty(value = "Id Object", required = true)
  private ObjectId id;

  @CreatedDate
  @ApiModelProperty(value = "Created Date", required = true)
  private Date createdDate;

  @ApiModelProperty(value = "Created By", required = true)
  @CreatedBy
  private String createdBy;

  @LastModifiedDate
  @ApiModelProperty(value = "Modifiede Date", required = true)
  private Date modifiedDate;

  @ApiModelProperty(value = "Modifed By", required = true)
  @LastModifiedBy
  private String modifiedBy;

  @Override
  public Optional<String> getCurrentAuditor() {

    String uname = SecurityContextHolder.getContext().getAuthentication().getName();
    return Optional.of(uname);
  }

}
