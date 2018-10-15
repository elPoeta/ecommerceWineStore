
package elpoeta.felurian.modelo;

import elpoeta.felurian.util.Validar;

/**
 *
 * @author elpoeta
 */
public class Usuario {
    private Integer id;
	private String nombre;
	private String apellido;
        private String telefono;
	private String email;
	private String password;
        private String confirmPassword;
	private boolean activo;

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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
          if(Validar.isNullOEspaciosEnBlanco(apellido)){
            
            throw new IllegalArgumentException("Apellido vacio o nulo");
        }
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(!Validar.isValidEmail(email) || email == null){
            throw new IllegalArgumentException("Verificar email");       
        }
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
          if(Validar.isNullOEspaciosEnBlanco(password)){
            
            throw new IllegalArgumentException("Password vacio o nulo");
        }
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", telefono=" + telefono + ", email=" + email + ", password=" + password + ", confirmPassword=" + confirmPassword + ", activo=" + activo + '}';
    }
       
}
