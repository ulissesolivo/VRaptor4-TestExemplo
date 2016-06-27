/*
 *  Created on : 20/04/2016, 22:36:48
 *  Author     : Ulisses Olivo
 *  E-mail     : ulissesolivo@gmail.com
 */
package com.ulisses.app.components;

import com.ulisses.app.dao.UsuarioDAO;
import com.ulisses.app.entities.Usuario;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.apache.commons.codec.digest.DigestUtils;
import com.ulisses.app.AppQuery;
import javax.transaction.Transactional;

@Transactional
public class UsuarioComponent {

  @Inject
  private UsuarioDAO usuarioDAO;
  
  public Usuario buscar(Long id) {
    return usuarioDAO.findOne(id);
  }

  public Usuario salvar(Usuario usuario) {
    if (usuario != null) {
      if (usuario.getSenhaConfirmacao() != null) {
        if (!usuario.getSenhaConfirmacao().equals(usuario.getSenha())) {
          throw new RuntimeException("As senhas não conferem!");
        } else if (usuario.getSenhaConfirmacao().length() < 10) {
          throw new RuntimeException("A senha deve conter no mínimo 10 caracteres!");
        } else if (usuario.getSenhaConfirmacao().length() > 125) {
          throw new RuntimeException("A senha deve conter no máximo 125 caracteres!");
        } else {
          usuario.setSenha(DigestUtils.sha512Hex(usuario.getSenha()));
          usuario.setSenhaConfirmacao(null);
        }
      } else if (usuario.getId() != null) {
        usuario.setSenha(null);
        Usuario u = usuarioDAO.findOne(usuario.getId());
        if (u != null) {
          usuario.setSenha(u.getSenha());
        }
      } else {
        usuario.setSenha(null);
      }
      usuario = usuarioDAO.saveAndFlush(usuario);
    }
    return usuario;
  }

  public boolean excluir(Long id) {
    Usuario usuario = usuarioDAO.findOne(id);
    if (usuario != null) {
      return usuarioDAO.remove(usuario);
    }
    return false;
  }
  
  public List<Usuario> buscar(String texto) {
    AppQuery<Usuario> spec = new AppQuery<Usuario>() {
      @Override
      public CriteriaQuery builder(Root<Usuario> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        query.where(builder.like(builder.lower(root.get("login")), ("%" + texto + "%").toLowerCase()));
        return query;
      }
    };
    return usuarioDAO.findAll(spec);
  }

  public List<Usuario> buscarTodos() {
    return usuarioDAO.findAll();
  }

  public Usuario buscar(String login, String senha) {
    List<Usuario> usuarios = usuarioDAO.findBySenha(senha);
    for (Usuario usuario : usuarios) {
      if (usuario.getLogin().equalsIgnoreCase(login)) {
        return usuario;
      }
    }
    return null;
  }

  public Long contar() {
    return usuarioDAO.count();
  }

}
