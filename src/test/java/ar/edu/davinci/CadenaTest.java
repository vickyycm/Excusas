package ar.edu.davinci;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CadenaTest {

    private EmailFake email;
    private Recepcionista recepcionista;
    private SupervisorArea supervisora;
    private GerenteRRHH gerente;
    private CEO ceo;
    private Empleado juan;

    @BeforeEach
    void armarCadena() {
        email = new EmailFake();
        recepcionista = new Recepcionista("Ana", "ana@excusas.com", 2001, email);
        supervisora = new SupervisorArea("Lucia", "lucia@excusas.com", 2002, email);
        gerente = new GerenteRRHH("Pedro", "pedro@excusas.com", 2003, email);
        ceo = new CEO("Marta", "marta@excusas.com", 2004, email);

        recepcionista.siguienteResponsable(supervisora);
        supervisora.siguienteResponsable(gerente);
        gerente.siguienteResponsable(ceo);

        juan = new Empleado("Juan", "juan@excusas.com", 1001);
    }


    @Test
    void laSupervisoraAceptaUnCorteDeLuzYConsultaAEdesur() {
        Excusa excusa = new Excusa(juan, Motivo.PERDIDA_SUMINISTRO);

        recepcionista.manejar(excusa);

        assertTrue(excusa.estaAceptada());
        assertEquals("EDESUR@mailfake.com.ar", email.ultimoDestino());
    }

    @Test
    void laSupervisoraAceptaCuidarUnFamiliarYPreguntaSiEstaTodoBien() {
        Excusa excusa = new Excusa(juan, Motivo.CUIDADO_FAMILIAR);

        recepcionista.manejar(excusa);

        assertTrue(excusa.estaAceptada());
        assertEquals("juan@excusas.com", email.ultimoDestino());
        assertEquals("¿Está todo bien?", email.ultimoCuerpo());
    }


    @Test
    void elGerenteAceptaUnaExcusaCompleja() {
        Excusa excusa = new Excusa(juan, Motivo.IRRELEVANTE);

        recepcionista.manejar(excusa);

        assertTrue(excusa.estaAceptada());
    }


    @Test
    void elCeoAceptaUnaExcusaInverosimilYRespondePorCreatividad() {
        Excusa excusa = new Excusa(juan, Motivo.INVEROSIMIL);

        recepcionista.manejar(excusa);

        assertTrue(excusa.estaAceptada());
        assertEquals("juan@excusas.com", email.ultimoDestino());
        assertEquals("Aprobado por creatividad", email.ultimoCuerpo());
    }


    @Test
    void unaExcusaTrivialLaResuelveLaRecepcionistaYNoLlegaAlCeo() {
        Excusa excusa = new Excusa(juan, Motivo.QUEDARSE_DORMIDO);

        recepcionista.manejar(excusa);

        assertTrue(excusa.estaAceptada());
        assertEquals("la licencia fue aceptada", email.ultimoCuerpo());
        assertEquals(1, email.cantidadEnviados());
    }

    @Test
    void elOrdenDeLaCadenaEsConfigurable() {
        Recepcionista otraRecepcionista = new Recepcionista("Sol", "sol@excusas.com", 3001, email);
        otraRecepcionista.siguienteResponsable(ceo);
        Excusa excusa = new Excusa(juan, Motivo.INVEROSIMIL);

        otraRecepcionista.manejar(excusa);

        assertTrue(excusa.estaAceptada());
        assertEquals("Aprobado por creatividad", email.ultimoCuerpo());
    }

    @Test
    void siNadiePuedeProcesarlaLaExcusaSeRechaza() {
        gerente.siguienteResponsable(null);
        Excusa excusa = new Excusa(juan, Motivo.INVEROSIMIL);

        recepcionista.manejar(excusa);

        assertFalse(excusa.estaAceptada());
        assertEquals("Excusa rechazada: necesitamos pruebas contundentes", excusa.getRespuesta());
    }
}