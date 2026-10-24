package ar.edu.davinci;

public abstract class Responsable extends Empleado implements ExcusaController {

    private static final String RECHAZO = "Excusa rechazada: necesitamos pruebas contundentes";

    private Responsable proximo;
    private DesempenioLaboral comportamiento;
    private int excusasProcesadas;
    protected Email email;

    public Responsable(String nombre, String emailEmpleado, int numeroLegajo, Email email) {
        super(nombre, emailEmpleado, numeroLegajo);
        this.email = email;
        this.excusasProcesadas = 0;
        this.comportamiento = new Productivo();
    }


    public void siguienteResponsable(Responsable proximo) {
        this.proximo = proximo;
    }

    @Override
    public void manejar(Excusa excusa) {
        revisarExcusa(excusa);
    }

    public void revisarExcusa(Excusa unaExcusa) {
        comportamiento.tratarTarea(this, unaExcusa);
    }

    protected void derivar(Excusa unaExcusa) {
        if (proximo != null) {
            proximo.manejar(unaExcusa);
        } else {
            unaExcusa.rechazar(RECHAZO);
        }
    }

    protected abstract boolean puedeProcesar(Excusa excusa);

    protected abstract void procesar(Excusa excusa);

    public void cambiarComportamiento(DesempenioLaboral nuevoComportamiento) {
        this.comportamiento = nuevoComportamiento;
    }

    public DesempenioLaboral getComportamiento() {
        return comportamiento;
    }

    protected void procesarYContar(Excusa excusa) {
        procesar(excusa);
        excusasProcesadas++;
        actualizarComportamientoPorCantidad();
    }

    private void actualizarComportamientoPorCantidad() {
        if (excusasProcesadas < 5) {
            cambiarComportamiento(new Productivo());
        } else if (excusasProcesadas <= 10) {
            cambiarComportamiento(new Normal());
        } else {
            cambiarComportamiento(new Vago());
        }
    }

    public void tomarCafe() {
        cambiarComportamiento(new Productivo());
    }

    public void tomarBreak(int minutos) {
        if (minutos >= 10 && minutos <= 15) {
            cambiarComportamiento(new Normal());
        }
    }

    public void volverAlDiaSiguiente() {
        comportamiento.volverAlDiaSiguiente(this);
    }

    public int getExcusasProcesadas() {
        return excusasProcesadas;
    }

    protected void enviarEmail(String destino, String asunto, String cuerpo) {
        email.enviarEmail(destino, getEmail(), asunto, cuerpo);
    }
}