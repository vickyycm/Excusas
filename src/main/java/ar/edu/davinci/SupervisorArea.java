package ar.edu.davinci;

public class SupervisorArea extends Responsable {

    private static final String EMAIL_EDESUR = "EDESUR@mailfake.com.ar";

    public SupervisorArea(String nombre, String emailEmpleado, int numeroLegajo, Email email) {
        super(nombre, emailEmpleado, numeroLegajo, email);
    }

    @Override
    protected boolean puedeProcesar(Excusa excusa) {
        return excusa.getMotivo() == Motivo.PERDIDA_SUMINISTRO
                || excusa.getMotivo() == Motivo.CUIDADO_FAMILIAR;
    }

    @Override
    protected void procesar(Excusa excusa) {
        excusa.aceptar();
        if (excusa.getMotivo() == Motivo.PERDIDA_SUMINISTRO) {
            email.enviarEmail(EMAIL_EDESUR, getEmail(),
                    "consulta corte de suministro",
                    "¿Hubo un corte de luz en el barrio?");
        } else {
            email.enviarEmail(excusa.getEmpleadoTarde().getEmail(), getEmail(),
                    "motivo demora",
                    "¿Está todo bien?");
        }
    }
}