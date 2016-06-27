 /*
 *  Created on : 23/04/2016, 11:06:48
 *  Author     : Ulisses Olivo
 *  E-mail     : ulissesolivo@gmail.com
 */
package com.ulisses.app.dao;

import com.ulisses.app.entities.Usuario;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.ulisses.app.AppQuery;

public abstract class BaseDAO<T> {

  private final Class<T> type;

  @Inject
  private EntityManager em;
  // protected CriteriaBuilder cb;
  // protected CriteriaQuery<T> query;
  // protected Root<T> root;

  @SuppressWarnings("unchecked")
  public BaseDAO() {
    Type t = getClass().getGenericSuperclass();
    ParameterizedType pt = (ParameterizedType) t;
    type = (Class<T>) pt.getActualTypeArguments()[0];
    // if (em == null) {
    // em =
    // Persistence.createEntityManagerFactory("default").createEntityManager();
    // }
    // cb = em.getCriteriaBuilder();
    // query = cb.createQuery(type);
    // root = query.from(type);
  }

  protected EntityManager getEntityManager() {
    return em;
  }
  
  protected CriteriaQuery<T> getCriteriaQuery() {
    return getCriteriaBuilder().createQuery(type);
  }

  public CriteriaBuilder getCriteriaBuilder() {
    return em.getCriteriaBuilder();
  }

  public T findOne(Long id) {
    if (id != null) {
      return em.find(type, id);
    }
    return null;
  }

  public T findOne(Object id) {
    if (id != null) {
      return em.find(type, id);
    }
    return null;
  }

  public T findFirst(CriteriaQuery<T> query) {
    List<T> result = em.createQuery(query).getResultList();
    if (result.size() > 0) {
      return result.get(0);
    }
    return null;
  }

  public T save(T entity) {
    return save(entity, false);
  }

  public T saveAndFlush(T entity) {
    return save(entity, true);
  }

  public T save(T entity, boolean flush) {
    if (entity != null) {
      entity = em.merge(entity);
    }
    if (flush) {
      em.flush();
    }
    return entity;
  }

  public boolean remove(Long id) {
    if (id != null) {
      return remove(findOne(id));
    }
    return false;
  }

  public boolean remove(T entity) {
    if (entity != null) {
      em.remove(entity);
      em.flush();
      return true;
    }
    return false;
  }

  public List<T> findAll() {
    return em.createQuery("from " + type.getSimpleName(), type).getResultList();
  }

  public List<T> findByQuery(String query) {
    return em.createQuery(query, type).getResultList();
  }

  public List<T> findAll(CriteriaQuery<T> query) {
    return em.createQuery(query).getResultList();
  }

  public Long count() {
    TypedQuery<Long> qtd = em.createQuery("select count(*) from " + type.getSimpleName(), Long.class);
    return qtd.getSingleResult();
  }
  
  public List<T> findAll(AppQuery appQuery) {
    CriteriaBuilder builder = getCriteriaBuilder();
    CriteriaQuery<T> query = builder.createQuery(type);
    Root<T> root = query.from(type);
    return em.createQuery(appQuery.builder(root, query, builder)).getResultList();
  }

}
