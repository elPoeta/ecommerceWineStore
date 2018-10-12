
package elpoeta.felurian.test.modelo;


import elpoeta.felurian.modelo.SubCategoria;
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
public class SubcategoriaTest {
    private SubCategoria subCategoria; 
    public SubcategoriaTest() {
    }
    
    
    @Before
    public void setUp() {
        subCategoria = new SubCategoria();
    }
   
      

       @Test
       public void SetIdSubcategoria_ConIdValido() {
           subCategoria.setId(1);
           assertThat(subCategoria.getId()).isNotNull().isEqualTo(1);
         
       }
       
       @Test(expected = IllegalArgumentException.class)
       public void SetIdSubcategoria_ConIdNoValido() {
          
           subCategoria.setId(null);
           assertThat(subCategoria.getId()).isNull();
           fail("Debería haberse lanzado una excepción.");
       }
       
       
       @Test
       public void SetIdCategoria_ConIdValido() {
          
           subCategoria.setIdCategoria(1);
           assertThat(subCategoria.getIdCategoria()).isNotNull().isEqualTo(1);
         
       }
       @Test(expected = IllegalArgumentException.class)
       public void SetIdCategoria_ConIdNoValido() {
           subCategoria.setIdCategoria(null);
           assertThat(subCategoria.getId()).isNull();
         
       }
       @Test
       public void SetNombreSucategoria_ConNombreValido() {
           subCategoria.setNombre("Tinto");
           assertThat(subCategoria.getNombre()).isNotBlank().isNotEmpty().isNotNull();
       }
       
       @Test(expected = IllegalArgumentException.class)
       public void SetNombreSubcategoria_ConNombreVacio() {
           subCategoria.setNombre("");
           assertThat(subCategoria.getNombre()).isBlank().isEmpty();
           fail("Debería haberse lanzado una excepción.");
      
       }
       
       @Test(expected = IllegalArgumentException.class)
       public void SetNombreSubcategoria_ConNombreSoloEspaciosBlancos() {   
           subCategoria.setNombre("   ");
           assertThat(subCategoria.getNombre()).containsOnlyWhitespaces();
           fail("Debería haberse lanzado una excepción.");
       }
        @Test(expected = IllegalArgumentException.class)
       public void SetNombreSubcategoria_ConNombreNull() {   
           subCategoria.setNombre(null);
           assertThat(subCategoria.getNombre()).isNull();
           fail("Debería haberse lanzado una excepción.");
       }
       
}
