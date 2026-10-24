package ar.edu.davinci;

public class EquipoDireccion implements Suscriber {

    private int avisosRecibidos = 0;
    private Prontuario ultimoProntuario;

    @Override
    public void actualizar(Prontuario prontuario) {
        avisosRecibidos++;
        ultimoProntuario = prontuario;
    }

    public int cantidadAvisosRecibidos() { return avisosRecibidos; }
    public Prontuario getUltimoProntuario() { return ultimoProntuario; }
}