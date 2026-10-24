package ar.edu.davinci;

public class Normal implements DesempenioLaboral {

    @Override
    public void tratarTarea(Responsable responsable, Excusa excusa) {
        if (responsable.puedeProcesar(excusa)) {
            responsable.procesarYContar(excusa);
        } else {
            responsable.derivar(excusa);
        }
    }

    @Override
    public void volverAlDiaSiguiente(Responsable responsable) {
        // Estando normal, volver al otro día no cambia nada
    }
}