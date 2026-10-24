package ar.edu.davinci;

public class GerenteRRHH extends Responsable {

    public GerenteRRHH(String nombre, String emailEmpleado, int numeroLegajo, Email email) {
        super(nombre, emailEmpleado, numeroLegajo, email);
    }

    @Override
    protected boolean puedeProcesar(Excusa excusa) {
        return excusa.getMotivo() == Motivo.IRRELEVANTE;
    }

    @Override
    protected void procesar(Excusa excusa) {
        excusa.aceptar();
    }
}