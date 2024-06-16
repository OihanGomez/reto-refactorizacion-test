package Libreria.objetos;

public class Usuario {
    private int idUsuario;
    private String direccion;
    private String apellidos;
    private String nombre;
    private String email;
    private boolean admin;
    private String contrasena;

    public Usuario(int idUsuario, String direccion, String apellidos, String nombre, String email, boolean admin, String contrasena) {
        this.idUsuario = idUsuario;
        this.direccion = direccion;
        this.apellidos = apellidos;
        this.nombre = nombre;
        this.email = email;
        this.admin = admin;
        this.contrasena = contrasena;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", direccion='" + direccion + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", admin=" + admin +
                ", contrasena='" + contrasena + '\'' +
                '}';
    }
}
