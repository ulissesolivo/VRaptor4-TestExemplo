/*
 *  Created on : 23/04/2016, 17:11:48
 *  Author     : Ulisses Olivo
 *  E-mail     : ulissesolivo@gmail.com
 */
package com.ulisses.app.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @SequenceGenerator(name = "generator_id", sequenceName = "sequence_id", allocationSize = 1)
  @GeneratedValue(generator = "generator_id", strategy = GenerationType.SEQUENCE)
  protected Long id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  @Override
  public boolean equals(Object object) {
    if (!(object instanceof BaseEntity)) {
      return false;
    }
    BaseEntity other = (BaseEntity) object;
    if (this.id == null && other.id == null) {
      return this == other;
    }
    return Objects.equals(this.id, other.id);
  }

}
