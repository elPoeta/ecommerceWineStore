
package elpoeta.felurian.test.modelo;

import elpoeta.felurian.modelo.Usuario;
import elpoeta.felurian.util.Validar;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author elpoeta
 */
public class UsuarioTest {
    private Usuario usuario;
    public UsuarioTest() {
    }
    
   
    
    @Before
    public void setUp() {
        usuario = new Usuario();
    }
    
    @After
    public void tearDown() {
    }

    @Test
       public void SetIdUsuario_ConIdValido() {
           usuario.setId(1);
           assertThat(usuario.getId()).isNotNull().isEqualTo(1);
         
       }
       
       @Test(expected = IllegalArgumentException.class)
       public void SetIdUsuario_ConIdNoValido() {
          
           usuario.setId(null);
           assertThat(usuario.getId()).isNull();
           fail("Debería haberse lanzado una excepción.");
       }
       
       
       @Test
       public void SetNombreUsuario_ConNombreValido() {
           usuario.setNombre("Leonardo");
           assertThat(usuario.getNombre()).isNotBlank().isNotEmpty().isNotNull();
       }
       
       @Test(expected = IllegalArgumentException.class)
       public void SetNombreUsuario_ConNombreVacio() {
           usuario.setNombre("");
           assertThat(usuario.getNombre()).isBlank().isEmpty();
           fail("Debería haberse lanzado una excepción.");
      
       }
       
       @Test(expected = IllegalArgumentException.class)
       public void SetNombreUsuario_ConNombreSoloEspaciosBlancos() {   
           usuario.setNombre("   ");
           assertThat(usuario.getNombre()).containsOnlyWhitespaces();
           fail("Debería haberse lanzado una excepción.");
       }
        @Test(expected = IllegalArgumentException.class)
       public void SetNombreUsuario_ConNombreNull() {   
           usuario.setNombre(null);
           assertThat(usuario.getNombre()).isNull();
           fail("Debería haberse lanzado una excepción.");
       }
       
       @Test
       public void SetApellidoUsuario_ConApellidoValido() {
           usuario.setApellido("Tosetto");
           assertThat(usuario.getApellido()).isNotBlank().isNotEmpty().isNotNull();
       }
       
       @Test(expected = IllegalArgumentException.class)
       public void SetApellidoUsuario_ConApellidoVacio() {
           usuario.setApellido("");
           assertThat(usuario.getApellido()).isBlank().isEmpty();
           fail("Debería haberse lanzado una excepción.");
      
       }
       
       @Test(expected = IllegalArgumentException.class)
       public void SetApellidoUsuario_ConApellidoSoloEspaciosBlancos() {   
           usuario.setApellido("   ");
           assertThat(usuario.getApellido()).containsOnlyWhitespaces();
           fail("Debería haberse lanzado una excepción.");
       }
        @Test(expected = IllegalArgumentException.class)
       public void SetApellidoUsuario_ConApellidoNull() {   
           usuario.setApellido(null);
           assertThat(usuario.getApellido()).isNull();
           fail("Debería haberse lanzado una excepción.");
       }
       
        @Test
       public void SetEmailUsuario_ConEmailValido() {
           usuario.setEmail("elpoeta@gmail.com");
           assertThat(usuario.getEmail()).isNotBlank().isNotEmpty().isNotNull();
       }
       
       @Test(expected = IllegalArgumentException.class)
       public void SetEmailUsuario_ConEmailInValido() {
           usuario.setEmail("elopetagmail.com");
           assertThat(usuario.getEmail()).doesNotContainPattern(Validar.VALID_EMAIL_ADDRESS_REGEX);
       }
       
        @Test(expected = IllegalArgumentException.class)
       public void SetEmailUsuario_ConEmailNull() {
           usuario.setEmail(null);
           assertThat(usuario.getEmail()).isNull();
       }
       
        @Test
       public void SetPasswordUsuario_ConPasswordValido() {
           usuario.setPassword("P@$$w0rd");
           assertThat(usuario.getPassword()).isNotBlank().isNotEmpty().isNotNull();
       }
       
       @Test(expected = IllegalArgumentException.class)
       public void SetPasswordUsuario_ConPasswordVacio() {
           usuario.setPassword("");
           assertThat(usuario.getPassword()).isBlank().isEmpty();
           fail("Debería haberse lanzado una excepción.");
      
       }
       
       @Test(expected = IllegalArgumentException.class)
       public void SetPasswordUsuario_ConPasswordSoloEspaciosBlancos() {   
           usuario.setPassword("   ");
           assertThat(usuario.getPassword()).containsOnlyWhitespaces();
           fail("Debería haberse lanzado una excepción.");
       }
        @Test(expected = IllegalArgumentException.class)
       public void SetPasswordUsuario_ConPasswordNull() {   
           usuario.setNombre(null);
           assertThat(usuario.getPassword()).isNull();
           fail("Debería haberse lanzado una excepción.");
       }
      
}
