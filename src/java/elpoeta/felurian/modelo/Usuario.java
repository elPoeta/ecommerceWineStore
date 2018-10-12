
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
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
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
        if(Validar.isValidEmail(email)){
            this.email = email;
        }else{
            throw new IllegalArgumentException("Verificar email");
        }
        
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
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
