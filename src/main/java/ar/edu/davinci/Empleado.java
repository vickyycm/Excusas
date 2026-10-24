package ar.edu.davinci;

public class Empleado {
    private String nombre;
    private String email;
    private int numeroLegajo;

    public Empleado(String nombre, String email, int numeroLegajo) {
        this.nombre = nombre;
        this.email = email;
        this.numeroLegajo = numeroLegajo;
    }

    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public int getNumeroLegajo() { return numeroLegajo; }
}