
package elpoeta.felurian.test.modelo;

import elpoeta.felurian.modelo.Categoria;
import elpoeta.felurian.modelo.SubCategoria;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

/**
 *
 * @author elpoeta
 */
public class CategoriaTest {
    private Categoria categoria;
    public CategoriaTest() {
    }
    
    @Before
    public void setUp() {
        categoria = new Categoria();
    }
    
   

       @Test
       public void setIdCategoria_ConIdValido() {
           categoria.setId(1);
           assertThat(categoria.getId()).isEqualTo(1).isNotNull();
         
       }
       
       @Test(expected = IllegalArgumentException.class)
       public void setIdCategoria_ConIdNoValido() {
          
           categoria.setId(null);
           assertThat(categoria.getId()).isNull();
           fail("Debería haberse lanzado una excepción.");
       }
       @Test
       public void setNombreCategoria_ConNombreValido() {
           categoria.setNombre("Vino");
           assertThat(categoria.getNombre()).isNotBlank().isNotEmpty().isNotNull();
       }
       
       @Test(expected = IllegalArgumentException.class)
       public void setNombreCategoria_ConNombreVacio() {
           categoria.setNombre("");
           assertThat(categoria.getNombre()).isBlank().isEmpty();
           fail("Debería haberse lanzado una excepción.");
      
       }
       
       @Test(expected = IllegalArgumentException.class)
       public void setNombreCategoria_ConNombreSoloEspaciosBlancos() {   
           categoria.setNombre("   ");
           assertThat(categoria.getNombre()).containsOnlyWhitespaces();
           fail("Debería haberse lanzado una excepción.");
       }
        @Test(expected = IllegalArgumentException.class)
       public void setNombreCategoria_ConNombreNull() {   
           categoria.setNombre(null);
           assertThat(categoria.getNombre()).isNull();
           fail("Debería haberse lanzado una excepción.");
       }
       
       @Test
       public void setSubCategorias_ConListaValida() {   
           List<SubCategoria> lista = new ArrayList();
           SubCategoria s = new SubCategoria();
           s.setNombre("tinto");
           lista.add(s);
           categoria.setSubCategorias(lista);
          
           assertThat(categoria.getSubCategorias()).isNotEmpty().isNotNull();
       }
       
       @Test
       public void setSubCategorias_ConListaVacia() {   
           List<SubCategoria> lista = new ArrayList();
           
           categoria.setSubCategorias(lista);
          
           assertThat(categoria.getSubCategorias()).isEmpty();
       }
}
