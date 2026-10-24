package ar.edu.davinci;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DesempenioTest {

    private EmailFake email;
    private Recepcionista ana;
    private CEO ceo;
    private Empleado juan;

    @BeforeEach
    void preparar() {
        email = new EmailFake();
        ana = new Recepcionista("Ana", "ana@excusas.com", 2001, email);
        ceo = new CEO("Marta", "marta@excusas.com", 2004, email);
        juan = new Empleado("Juan", "juan@excusas.com", 1001);
    }


    @Test
    void unResponsableNuevoEstaProductivo() {
        assertInstanceOf(Productivo.class, ana.getComportamiento());
    }


    @Test
    void productivoSiNoPuedeLeAvisaAlCtoAntesDeDerivar() {
        ana.siguienteResponsable(ceo);
        Excusa excusa = new Excusa(juan, Motivo.INVEROSIMIL);

        ana.manejar(excusa);

        assertTrue(email.seEnvioA(Productivo.EMAIL_CTO));
        assertTrue(excusa.estaAceptada());
    }

    @Test
    void vagoNoEvaluaLaExcusaAunquePudieraResolverla() {
        ana.cambiarComportamiento(new Vago());
        Excusa excusa = new Excusa(juan, Motivo.QUEDARSE_DORMIDO);

        ana.manejar(excusa);

        assertFalse(excusa.estaAceptada());
    }

    @Test
    void vagoDerivaSinAvisarAlCto() {
        ana.cambiarComportamiento(new Vago());
        ana.siguienteResponsable(ceo);
        Excusa excusa = new Excusa(juan, Motivo.INVEROSIMIL);

        ana.manejar(excusa);

        assertFalse(email.seEnvioA(Productivo.EMAIL_CTO));
        assertTrue(excusa.estaAceptada());
    }

    @Test
    void normalDerivaSinAvisarAlCto() {
        ana.cambiarComportamiento(new Normal());
        ana.siguienteResponsable(ceo);
        Excusa excusa = new Excusa(juan, Motivo.INVEROSIMIL);

        ana.manejar(excusa);

        assertFalse(email.seEnvioA(Productivo.EMAIL_CTO));
        assertTrue(excusa.estaAceptada());
    }


    @Test
    void alTomarUnCafeVuelveAEstarProductivo() {
        ana.cambiarComportamiento(new Vago());

        ana.tomarCafe();

        assertInstanceOf(Productivo.class, ana.getComportamiento());
    }

    @Test
    void unBreakDe12MinutosLoPoneNormal() {
        ana.cambiarComportamiento(new Vago());

        ana.tomarBreak(12);

        assertInstanceOf(Normal.class, ana.getComportamiento());
    }

    @Test
    void unBreakDeMenosDe10MinutosNoSurteEfecto() {
        ana.cambiarComportamiento(new Vago());

        ana.tomarBreak(5);

        assertInstanceOf(Vago.class, ana.getComportamiento());
    }

    @Test
    void unBreakDeMasDe15MinutosNoSurteEfecto() {
        ana.cambiarComportamiento(new Vago());

        ana.tomarBreak(20);

        assertInstanceOf(Vago.class, ana.getComportamiento());
    }

    @Test
    void conMenosDe5ExcusasProcesadasSigueProductivo() {
        procesarExcusas(4);

        assertInstanceOf(Productivo.class, ana.getComportamiento());
    }

    @Test
    void con5ExcusasProcesadasPasaANormal() {
        procesarExcusas(5);

        assertInstanceOf(Normal.class, ana.getComportamiento());
    }

    @Test
    void con11ExcusasProcesadasPasaAVago() {
        procesarExcusas(11);

        assertInstanceOf(Vago.class, ana.getComportamiento());
    }

    @Test
    void siEstaVagoYVuelveAlOtroDiaQuedaProductivoONormal() {
        ana.cambiarComportamiento(new Vago());

        ana.volverAlDiaSiguiente();

        DesempenioLaboral modo = ana.getComportamiento();
        assertTrue(modo instanceof Productivo || modo instanceof Normal);
    }

    @Test
    void siEstaNormalYVuelveAlOtroDiaSigueNormal() {
        ana.cambiarComportamiento(new Normal());

        ana.volverAlDiaSiguiente();

        assertInstanceOf(Normal.class, ana.getComportamiento());
    }

    private void procesarExcusas(int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            ana.manejar(new Excusa(juan, Motivo.QUEDARSE_DORMIDO));
        }
    }
}