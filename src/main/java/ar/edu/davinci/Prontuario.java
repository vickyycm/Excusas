package ar.edu.davinci;

public class Prontuario {
    private Empleado datosEmpleado;
    private Excusa excusa;
    private int nroLegajo;

    public Prontuario(Empleado datosEmpleado, Excusa excusa) {
        this.datosEmpleado = datosEmpleado;
        this.excusa = excusa;
        this.nroLegajo = datosEmpleado.getNumeroLegajo();
    }

    public Empleado getDatosEmpleado() { return datosEmpleado; }
    public Excusa getExcusa() { return excusa; }
    public int getNroLegajo() { return nroLegajo; }
}