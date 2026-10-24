package ar.edu.davinci;

public class Recepcionista extends Responsable {

    public Recepcionista(String nombre, String emailEmpleado, int numeroLegajo, Email email) {
        super(nombre, emailEmpleado, numeroLegajo, email);
    }

    @Override
    protected boolean puedeProcesar(Excusa excusa) {
        return excusa.getMotivo() == Motivo.QUEDARSE_DORMIDO
                || excusa.getMotivo() == Motivo.PERDIDA_TRANSPORTE;
    }

    @Override
    protected void procesar(Excusa excusa) {
        excusa.aceptar();
        email.enviarEmail(
                excusa.getEmpleadoTarde().getEmail(),
                getEmail(),
                "motivo demora",
                "la licencia fue aceptada"
        );
    }
}