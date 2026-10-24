package ar.edu.davinci;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExcusaTest {

    @Test
    void unaExcusaNuevaNoEstaAceptada() {
        Empleado juan = new Empleado("Juan", "juan@excusas.com", 1001);
        Excusa excusa = new Excusa(juan, Motivo.QUEDARSE_DORMIDO);

        assertFalse(excusa.estaAceptada());
    }

    @Test
    void alAceptarUnaExcusaQuedaAceptada() {
        Empleado juan = new Empleado("Juan", "juan@excusas.com", 1001);
        Excusa excusa = new Excusa(juan, Motivo.QUEDARSE_DORMIDO);

        excusa.aceptar();

        assertTrue(excusa.estaAceptada());
    }

    @Test
    void laExcusaConoceAlEmpleadoQueLlegoTarde() {
        Empleado juan = new Empleado("Juan", "juan@excusas.com", 1001);
        Excusa excusa = new Excusa(juan, Motivo.PERDIDA_TRANSPORTE);

        assertEquals(juan, excusa.getEmpleadoTarde());
        assertEquals(Motivo.PERDIDA_TRANSPORTE, excusa.getMotivo());
    }
}