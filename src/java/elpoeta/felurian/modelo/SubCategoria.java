
package elpoeta.felurian.modelo;

import elpoeta.felurian.util.Validar;

/**
 *
 * @author elpoeta
 */
public class SubCategoria {
    private Integer id;
    private String nombre;
    private Boolean activa;
    private Integer idCategoria;

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

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
           if(idCategoria == null){
            throw new IllegalArgumentException("id Nullo no es entero");
        } 
         
        this.idCategoria = idCategoria;
    }

    @Override
    public String toString() {
        return "SubCategoria{" + "id=" + id + ", nombre=" + nombre + ", activa=" + activa + ", idCategoria=" + idCategoria + '}';
    }

}
