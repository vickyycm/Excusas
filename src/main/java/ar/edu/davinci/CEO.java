package ar.edu.davinci;

public class CEO extends Responsable {

    public CEO(String nombre, String emailEmpleado, int numeroLegajo, Email email) {
        super(nombre, emailEmpleado, numeroLegajo, email);
    }

    @Override
    protected boolean puedeProcesar(Excusa excusa) {
        return excusa.getMotivo() == Motivo.INVEROSIMIL;
    }

    @Override
    protected void procesar(Excusa excusa) {
        excusa.aceptar();
        email.enviarEmail(excusa.getEmpleadoTarde().getEmail(), getEmail(),
                "motivo demora",
                "Aprobado por creatividad");

        Prontuario prontuario = new Prontuario(excusa.getEmpleadoTarde(), excusa);
        AdministradorProntuario.getInstancia().persistirProntuario(prontuario);
    }
}