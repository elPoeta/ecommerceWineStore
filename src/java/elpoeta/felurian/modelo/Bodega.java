
package elpoeta.felurian.modelo;

/**
 *
 * @author elpoeta
 */
public class Bodega {
    private Integer id;
    private String nombre;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Bodega{" + "id=" + id + ", nombre=" + nombre + '}';
    }
}
