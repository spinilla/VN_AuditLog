package com.viewnext.formacion.auditlog.core.utils;

/**
 * com.viewnext.formacion.auditlog.core.utils
 *
 * <p>Role
 *
 * @author Sergio Pinilla (Viewnext/IBM)
 * @version 0.1
 * @since 21 ago. 2018
 */
public enum Role {
  ROLE_ADMIN("ADMIN"),
  ROLE_USER("USER");
  private String valor;

  private Role(String valor) {
    this.valor = valor;
  }

  public String getValor() {
    return valor;
  }

  public void setValor(String valor) {
    this.valor = valor;
  }
}
