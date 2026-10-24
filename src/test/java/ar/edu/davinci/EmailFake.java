package ar.edu.davinci;

import java.util.ArrayList;
import java.util.List;

public class EmailFake implements Email {

    private final List<String[]> enviados = new ArrayList<>();

    @Override
    public void enviarEmail(String unEmailDestino, String unEmailOrigen, String unAsunto, String unCuerpo) {
        enviados.add(new String[]{unEmailDestino, unEmailOrigen, unAsunto, unCuerpo});
    }

    public int cantidadEnviados() {
        return enviados.size();
    }

    public String ultimoDestino() {
        return enviados.get(enviados.size() - 1)[0];
    }

    public String ultimoAsunto() {
        return enviados.get(enviados.size() - 1)[2];
    }

    public String ultimoCuerpo() {
        return enviados.get(enviados.size() - 1)[3];
    }

    public boolean seEnvioA(String destino) {
        for (String[] mail : enviados) {
            if (mail[0].equals(destino)) {
                return true;
            }
        }
        return false;
    }
}