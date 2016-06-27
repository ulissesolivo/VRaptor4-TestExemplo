/*
 *  Created on : 20/04/2016, 22:22:48
 *  Author     : Ulisses Olivo
 *  E-mail     : ulissesolivo@gmail.com
 */
package com.ulisses.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "usuario", uniqueConstraints = @UniqueConstraint(columnNames = {"login"}, name = "login_key"))
public class Usuario extends BaseEntity {

  @Column(length = 255, nullable = false)
  @NotNull(message = "O nome deve ser informado!")
  private String nome;
  
  @Column(length = 255, nullable = false)
  @Length(min = 1, max = 255, message = "O login deve conter no máximo 255 caracteres!")
  private String login;

  @Column(length = 130)
  @Length(min = 0, max = 130, message = "A senha deve conter no máximo 130 caracteres!")
  private String senha;

  @Transient
  private String senhaConfirmacao;

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome == null || nome.trim().equals("") ? null : nome.trim();
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login == null || login.trim().equals("") ? null : login.trim();
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public String getSenhaConfirmacao() {
    return senhaConfirmacao;
  }

  public void setSenhaConfirmacao(String senhaConfirmacao) {
    this.senhaConfirmacao = senhaConfirmacao;
  }

  public Usuario() {
  }

  @Override
  public String toString() {
    return "com.ulisses.app.entities.Usuario[ id=" + id + " ]";
  }

}
