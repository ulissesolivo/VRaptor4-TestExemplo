/*
 *  Created on : 23/04/2016, 19:35:48
 *  Author     : Ulisses Olivo
 *  E-mail     : ulissesolivo@gmail.com
 */
package com.ulisses.app;

import br.com.caelum.vraptor.validator.Severity;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import javax.persistence.PersistenceException;
import org.hibernate.exception.ConstraintViolationException;

public class AppTry {

  public static Object exec(AppTryIntf intf, Validator validator) {
    try {
      return intf.exec();
    } catch (PersistenceException e) {
      validator.add(new SimpleMessage("erro", e.getMessage(), Severity.ERROR));
      if (e.getCause() != null && e.getCause() instanceof ConstraintViolationException) {
        ConstraintViolationException cve = (ConstraintViolationException) e.getCause();
        if (cve.getSQLException() != null) {
          validator.add(new SimpleMessage("erro", cve.getSQLException().getMessage(), Severity.ERROR));
          if (cve.getSQLException().getNextException() != null) {
            validator.add(new SimpleMessage("erro", cve.getSQLException().getNextException().getMessage(), Severity.ERROR));
          }
        }
      }
    } catch (Exception e) {
      validator.add(new SimpleMessage("erro", e.getMessage(), Severity.ERROR));
      if (e.getCause() != null) {
        validator.add(new SimpleMessage("erro", e.getCause().getMessage(), Severity.ERROR));
      }
    }
    return null;
  }

}
