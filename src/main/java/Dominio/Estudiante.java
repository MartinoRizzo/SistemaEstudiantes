
package Dominio;


public class Estudiante {
    
    private int idEstudiante;
    private String nombre;
    private String telefono;
    private String mail;

    public Estudiante() {
    }

    public Estudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public Estudiante(String nombre, String telefono, String mail) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.mail = mail;
    }

    public Estudiante(int idEstudiante, String nombre, String telefono, String mail) {
        this.idEstudiante = idEstudiante;
        this.nombre = nombre;
        this.telefono = telefono;
        this.mail = mail;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "Estudiante{" + "idEstudiante=" + idEstudiante + ", nombre=" + nombre + ", telefono=" + telefono + ", mail=" + mail + '}';
    }
    
    
    
}
