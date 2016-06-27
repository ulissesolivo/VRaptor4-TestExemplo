/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario;

import br.com.caelum.vraptor.test.VRaptorIntegration;
import br.com.caelum.vraptor.test.VRaptorTestResult;
import br.com.caelum.vraptor.test.http.Parameters;
import com.ulisses.app.entities.Usuario;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author uliss
 */

public class UsuarioTest extends VRaptorIntegration {

  @Test
  public void lista() {
    VRaptorTestResult result = navigate().get("/usuario/listar").execute();
    List<Usuario> usuarios = result.getObject("usuarios");
    if (usuarios != null) {
      for (Usuario usuario : usuarios) {
        System.out.println("Usuário: " + usuario.getNome());
      }
      System.out.println(usuarios.size() + " usuários");
    }
    Assert.assertTrue(usuarios != null && usuarios.size() > 0);
  }

  @Test
  public void add() {
    Parameters parameters = new Parameters();
    parameters.add("usuario.nome", "Ulisses test");
    parameters.add("usuario.login", "ulissestest@gmail.com");
    parameters.add("usuario.senha", "1234567890");
    parameters.add("usuario.senhaConfirmacao", "1234567890");
    VRaptorTestResult result = navigate().post("/usuario/salvar", parameters).execute();
    List<Usuario> usuarios = result.getObject("usuarios");
    if (usuarios != null) {
      for (Usuario usuario : usuarios) {
        System.out.println("Usuário: " + usuario.getNome());
      }
      System.out.println(usuarios.size() + " usuários");
    }
    Assert.assertTrue(usuarios != null && usuarios.size() > 0);
  }

  @Test
  public void get() {
    VRaptorTestResult result = navigate().get("/").execute();
    String mensagem = result.getObject("mensagem");
    Assert.assertEquals("Bem vindo!", mensagem);
    System.out.println("Result: " + mensagem);

  }

  @Test
  public void find() {
    VRaptorTestResult result = navigate().get("/usuario/editar/4").execute();
    Usuario usuario = result.getObject("usuario");
    Assert.assertTrue(usuario != null);
  }

}
