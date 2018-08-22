package com.viewnext.formacion.auditlog.core.model;

import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * com.viewnext.formacion.auditlog.core.model
 * 
 * Theme
 * 
 * @author Sergio Pinilla (Viewnext/IBM)
 * @version 0.1
 * @since 20 ago. 2018
 *
 */
@Data
@Document
public class Theme extends BaseEntityData {

    @NotNull
    @ApiModelProperty(value = "Title", required = true)
    private String titleTheme;

}

