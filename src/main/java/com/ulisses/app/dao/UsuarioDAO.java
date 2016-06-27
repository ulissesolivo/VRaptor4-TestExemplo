/*
 *  Created on : 20/04/2016, 22:36:48
 *  Author     : Ulisses Olivo
 *  E-mail     : ulissesolivo@gmail.com
 */
package com.ulisses.app.dao;

import com.ulisses.app.entities.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.apache.commons.codec.digest.DigestUtils;

public class UsuarioDAO extends BaseDAO<Usuario> {

  public List<Usuario> findByLogin(String login) {    
    return findByQuery("from Usuario where login = '" + login + "'");
  }
  
  public List<Usuario> findBySenha(String senha) {
    if (senha == null) {
      return new ArrayList<>();
    }
    CriteriaBuilder cb = getCriteriaBuilder();
    CriteriaQuery cq = cb.createQuery(Usuario.class);
    Root<Usuario> root = cq.from(Usuario.class);
    cq.where(cb.equal(root.get("senha"), DigestUtils.sha512Hex(senha)));
    return findAll(cq);
  }

}
