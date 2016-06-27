 /*
 *  Created on : 19/04/2016, 23:51:48
 *   Author    : Ulisses Olivo
 *   E-mail    : ulissesolivo@gmail.com
 */
package com.ulisses.app.controllers;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;
import javax.inject.Inject;

@Controller
public class AppController {

  @Inject
  private Result r;

  @Get("/")
  public void index() {
    r.include("mensagem", "Bem vindo!");
  }

}
