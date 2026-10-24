package ar.edu.davinci;

public class Productivo implements DesempenioLaboral {

    public static final String EMAIL_CTO = "cto@excusas.com";

    @Override
    public void tratarTarea(Responsable responsable, Excusa excusa) {
        if (responsable.puedeProcesar(excusa)) {
            responsable.procesarYContar(excusa);
        } else {
            responsable.enviarEmail(EMAIL_CTO,
                    "excusa derivada",
                    "No pude procesar la excusa de " + excusa.getEmpleadoTarde().getNombre()
                            + ", la paso al siguiente sector");
            responsable.derivar(excusa);
        }
    }

    @Override
    public void volverAlDiaSiguiente(Responsable responsable) {
    }
}