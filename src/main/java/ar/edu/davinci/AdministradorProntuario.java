package ar.edu.davinci;

import java.util.ArrayList;
import java.util.List;

public class AdministradorProntuario {

    private static AdministradorProntuario instancia;

    private List<Prontuario> prontuarios;
    private String nombre;
    private CasosProntuarios casosProntuarios;

    private AdministradorProntuario() {
        this.prontuarios = new ArrayList<>();
        this.nombre = "Administrador de Prontuarios";
        this.casosProntuarios = new CasosProntuarios();
    }

    public static AdministradorProntuario getInstancia() {
        if (instancia == null) {
            instancia = new AdministradorProntuario();
        }
        return instancia;
    }

    public void persistirProntuario(Prontuario unProntuario) {
        prontuarios.add(unProntuario);
        casosProntuarios.notificarNuevoProntuario(unProntuario);
    }

    public int cantidadProntuarios() {
        return prontuarios.size();
    }

    public CasosProntuarios getCasosProntuarios() {
        return casosProntuarios;
    }
}