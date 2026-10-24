package ar.edu.davinci;

public class Excusa {
    private Empleado empleadoTarde;
    private Motivo motivo;
    private boolean estadoExcusa;
    private String respuesta;

    public Excusa(Empleado empleadoTarde, Motivo motivo) {
        this.empleadoTarde = empleadoTarde;
        this.motivo = motivo;
        this.estadoExcusa = false;
    }

    public void aceptar() { this.estadoExcusa = true; }

    public void rechazar(String respuesta) {
        this.estadoExcusa = false;
        this.respuesta = respuesta;
    }

    public boolean estaAceptada() { return estadoExcusa; }
    public Empleado getEmpleadoTarde() { return empleadoTarde; }
    public Motivo getMotivo() { return motivo; }
    public String getRespuesta() { return respuesta; }
}