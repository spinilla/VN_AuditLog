package com.viewnext.formacion.auditlog.core.model;

import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;

import com.viewnext.formacion.auditlog.core.utils.Role;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * com.viewnext.formacion.auditlog.core.model
 * 
 * UserApp
 * 
 * @author Sergio Pinilla (Viewnext/IBM)
 * @version 0.1
 * @since 21 ago. 2018
 *
 */
@Data
@Document(collection="user")
public class UserApp extends BaseEntityData {
    
    @NotNull
    @ApiModelProperty(value = "UserApp's nick", required = true)
    private String nick;

    @NotNull
    @ApiModelProperty(value = "UserApp's password", required = true)
    private String password;

    @NotNull
    @ApiModelProperty(value = "UserApp's password", required = true)
    private Role role;
    
}

