package ar.edu.davinci;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProntuarioTest {

    private EmailFake email;
    private CEO ceo;
    private Empleado juan;
    private EquipoDireccion direccion;
    private CasosProntuarios casos;

    @BeforeEach
    void preparar() {
        email = new EmailFake();
        ceo = new CEO("Marta", "marta@excusas.com", 2004, email);
        juan = new Empleado("Juan", "juan@excusas.com", 1001);

        direccion = new EquipoDireccion();
        casos = AdministradorProntuario.getInstancia().getCasosProntuarios();
        casos.agregarSuscriber(direccion);
    }

    @AfterEach
    void limpiar() {
        casos.eliminarSuscriber(direccion);
    }

    @Test
    void elAdministradorDeProntuariosEsUnico() {
        AdministradorProntuario primero = AdministradorProntuario.getInstancia();
        AdministradorProntuario segundo = AdministradorProntuario.getInstancia();

        assertSame(primero, segundo);
    }

    @Test
    void cuandoElCeoApruebaSeGuardaUnProntuario() {
        int antes = AdministradorProntuario.getInstancia().cantidadProntuarios();
        Excusa excusa = new Excusa(juan, Motivo.INVEROSIMIL);

        ceo.manejar(excusa);

        assertEquals(antes + 1, AdministradorProntuario.getInstancia().cantidadProntuarios());
    }

    @Test
    void elProntuarioTieneLosDatosDelEmpleadoYLaExcusa() {
        Excusa excusa = new Excusa(juan, Motivo.INVEROSIMIL);

        ceo.manejar(excusa);

        Prontuario prontuario = direccion.getUltimoProntuario();
        assertEquals(juan, prontuario.getDatosEmpleado());
        assertEquals(excusa, prontuario.getExcusa());
        assertEquals(1001, prontuario.getNroLegajo());
    }

    @Test
    void laDireccionRecibeElAvisoDeUnNuevoProntuario() {
        Excusa excusa = new Excusa(juan, Motivo.INVEROSIMIL);

        ceo.manejar(excusa);

        assertEquals(1, direccion.cantidadAvisosRecibidos());
    }

    @Test
    void siLaDireccionSeDesuscribeNoRecibeAvisos() {
        casos.eliminarSuscriber(direccion);
        Excusa excusa = new Excusa(juan, Motivo.INVEROSIMIL);

        ceo.manejar(excusa);

        assertEquals(0, direccion.cantidadAvisosRecibidos());
    }

    @Test
    void unaExcusaQueNoLlegaAlCeoNoGeneraProntuario() {
        Recepcionista recepcionista = new Recepcionista("Ana", "ana@excusas.com", 2001, email);
        Excusa excusa = new Excusa(juan, Motivo.QUEDARSE_DORMIDO);

        recepcionista.manejar(excusa);

        assertEquals(0, direccion.cantidadAvisosRecibidos());
    }
}