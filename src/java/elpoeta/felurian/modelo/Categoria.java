
package elpoeta.felurian.modelo;

import elpoeta.felurian.util.Validar;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elpoeta
 */
public class Categoria {
     private Integer id;
    private String nombre;
    private Boolean activa;
    private List<SubCategoria> subCategorias;

    public Categoria(){
        this.subCategorias = new ArrayList();
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        if(id == null){
            throw new IllegalArgumentException("id Nullo no es entero");
        } 
         
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        
        if(Validar.isNullOEspaciosEnBlanco(nombre)){
            
            throw new IllegalArgumentException("Nombre vacio o nulo");
        }
        this.nombre = nombre;
    }

    public Boolean getActiva() {
        return activa;
    }

    public void setActiva(Boolean activa) {
        this.activa = activa;
    }

    public List<SubCategoria> getSubCategorias() {
        return subCategorias;
    }

    public void setSubCategorias(List<SubCategoria> subCategorias) {
        this.subCategorias = subCategorias;
    }

    @Override
    public String toString() {
        return "Categoria{" + "id=" + id + ", nombre=" + nombre + ", activa=" + activa + ", subCategorias=" + subCategorias + '}';
    }    
}
