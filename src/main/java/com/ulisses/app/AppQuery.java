/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ulisses.app;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author uliss
 * @param <T>
 */
public interface AppQuery<T> {

  CriteriaQuery<T> builder(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder);

}
