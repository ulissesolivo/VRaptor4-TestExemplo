/*
 *  Created on : 20/04/2016, 21:51:48
 *  Author     : Ulisses Olivo
 *  E-mail     : ulissesolivo@gmail.com
 */
package com.ulisses.app.controllers;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Severity;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import com.ulisses.app.AppSession;
import com.ulisses.app.AppTry;
import com.ulisses.app.components.UsuarioComponent;
import com.ulisses.app.entities.Usuario;
import javax.inject.Inject;

@Controller
@Path("usuario")
public class UsuarioController {

  @Inject
  private Result result;
  @Inject
  private Validator validator;
  @Inject
  private AppSession session;
  @Inject
  private UsuarioComponent usuarioComponent;

  @Get("listar")
  public void listar() {
    result.include("usuarios", usuarioComponent.buscarTodos());
  }

  @Post("listar")
  public void listar(String texto) {
    if (texto != null && !texto.isEmpty()) {
      result.include("texto", texto);
      result.include("usuarios", usuarioComponent.buscar(texto));
    } else {
      result.forwardTo(this).listar();
    }
  }

  @Get("editar")
  public void editar(Usuario usuario) {
    result.include("usuario", usuario == null ? new Usuario() : usuario);
  }

  @Get("editar/{id}")
  public void editar(Long id) {
    result.include("usuario", usuarioComponent.buscar(id));
  }

  @Post("salvar")
  public void salvar(Usuario usuario) {
    validator.validate(usuario);
    validator.onErrorForwardTo(this).editar(usuario);
    AppTry.exec(() -> usuarioComponent.salvar(usuario), validator);
    validator.onErrorForwardTo(this).editar(usuario);
    result.forwardTo(this).listar();
  }

  @Get("excluir/{id}")
  public void excluir(Long id) {
    AppTry.exec(() -> usuarioComponent.excluir(id), validator);
    validator.onErrorForwardTo(this).listar();
    result.forwardTo(this).listar();
  }

  @Get("login")
  public void login() {
    if (usuarioComponent.contar() == 0) {
      validator.add(new SimpleMessage("login.novo",
              "O sistema não possui nenhum usuário cadastrado, o primeiro login será cadastrado!",
              Severity.ERROR));
    }
    validator.onErrorForwardTo(this);
  }

  @Post("logar")
  public void logar(Usuario usuario) {
    AppTry.exec(() -> {
      session.setUsuario(usuarioComponent.buscar(usuario.getLogin(), usuario.getSenha()));
      if (session.getUsuario() == null) {
        if (usuarioComponent.contar() > 0) {
          validator.add(new SimpleMessage("login.invalido", "Login e/ou senha inválidos!", Severity.ERROR));
        } else {
          usuario.setSenhaConfirmacao(usuario.getSenha());
          session.setUsuario(usuarioComponent.salvar(usuario));
        }
      }
      return null;
    }, validator);
    validator.onErrorForwardTo(this).login();
    result.forwardTo(AppController.class).index();
  }

  @Get("sair")
  public void sair() {
    session.setUsuario(null);
    result.forwardTo(AppController.class).index();
  }

}
