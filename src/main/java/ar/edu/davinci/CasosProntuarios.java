package ar.edu.davinci;

import java.util.ArrayList;
import java.util.List;

public class CasosProntuarios {

    private List<Suscriber> equiposAvisados = new ArrayList<>();

    public void agregarSuscriber(Suscriber suscriber) {
        equiposAvisados.add(suscriber);
    }

    public void eliminarSuscriber(Suscriber suscriber) {
        equiposAvisados.remove(suscriber);
    }

    public void notificarNuevoProntuario(Prontuario prontuario) {
        for (Suscriber suscriber : equiposAvisados) {
            suscriber.actualizar(prontuario);
        }
    }
}