package ar.edu.davinci;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecepcionistaTest {

    @Test
    void laRecepcionistaAceptaUnaExcusaDeQuedarseDormido() {
        EmailFake email = new EmailFake();
        Recepcionista ana = new Recepcionista("Ana", "ana@excusas.com", 2001, email);
        Empleado juan = new Empleado("Juan", "juan@excusas.com", 1001);
        Excusa excusa = new Excusa(juan, Motivo.QUEDARSE_DORMIDO);

        ana.manejar(excusa);

        assertTrue(excusa.estaAceptada());
    }

    @Test
    void laRecepcionistaAceptaUnaExcusaDePerderElTransporte() {
        EmailFake email = new EmailFake();
        Recepcionista ana = new Recepcionista("Ana", "ana@excusas.com", 2001, email);
        Empleado juan = new Empleado("Juan", "juan@excusas.com", 1001);
        Excusa excusa = new Excusa(juan, Motivo.PERDIDA_TRANSPORTE);

        ana.manejar(excusa);

        assertTrue(excusa.estaAceptada());
    }

    @Test
    void alAceptarLaRecepcionistaLeEnviaUnEmailAlEmpleado() {
        EmailFake email = new EmailFake();
        Recepcionista ana = new Recepcionista("Ana", "ana@excusas.com", 2001, email);
        Empleado juan = new Empleado("Juan", "juan@excusas.com", 1001);
        Excusa excusa = new Excusa(juan, Motivo.QUEDARSE_DORMIDO);

        ana.manejar(excusa);

        assertEquals(1, email.cantidadEnviados());
        assertEquals("juan@excusas.com", email.ultimoDestino());
        assertEquals("motivo demora", email.ultimoAsunto());
        assertEquals("la licencia fue aceptada", email.ultimoCuerpo());
    }

    @Test
    void laRecepcionistaNoAceptaUnaExcusaQueNoLeCorresponde() {
        EmailFake email = new EmailFake();
        Recepcionista ana = new Recepcionista("Ana", "ana@excusas.com", 2001, email);
        Empleado juan = new Empleado("Juan", "juan@excusas.com", 1001);
        Excusa excusa = new Excusa(juan, Motivo.INVEROSIMIL);

        ana.manejar(excusa);

        assertFalse(excusa.estaAceptada());
    }
}