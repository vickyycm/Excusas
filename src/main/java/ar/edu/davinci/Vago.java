package ar.edu.davinci;

import java.util.Random;

public class Vago implements DesempenioLaboral {

    private final Random random = new Random();

    @Override
    public void tratarTarea(Responsable responsable, Excusa excusa) {
        responsable.derivar(excusa);
    }

    @Override
    public void volverAlDiaSiguiente(Responsable responsable) {
        if (random.nextBoolean()) {
            responsable.cambiarComportamiento(new Productivo());
        } else {
            responsable.cambiarComportamiento(new Normal());
        }
    }
}