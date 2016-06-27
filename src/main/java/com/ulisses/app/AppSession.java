 /*
 *  Created on : 21/04/2016, 22:36:48
 *  Author     : Ulisses Olivo
 *  E-mail     : ulissesolivo@gmail.com
 */
package com.ulisses.app;

import com.ulisses.app.entities.Usuario;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author uliss
 */
@SessionScoped
@Named(value = "session")
public class AppSession implements Serializable {
  
  private Usuario usuario;

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }
  
  
  
}
