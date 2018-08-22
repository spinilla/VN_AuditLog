package com.viewnext.formacion.auditlog.core.model;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Auditable;
import org.springframework.data.domain.AuditorAware;
//import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * com.viewnext.formacion.auditlog.core.model
 *
 * <p>Tip UserApp domain class that uses auditing functionality of Spring Data that can either be
 * aquired implementing {@link Auditable} or extend {@link AbstractAuditable}.
 *
 * @author Sergio Pinilla (Viewnext/IBM)
 * @version 0.1
 * @since 20 ago. 2018
 */
@Data
@Document
public class Tip extends BaseEntityData  {

  @NotNull
  @ApiModelProperty(value = "Title", required = true)
  private String title;

  @NotNull
  @ApiModelProperty(value = "Text", required = true)
  private String tipText;

  @NotNull
  @ApiModelProperty(value = "Theme", required = true)
  private Theme theme;

}
