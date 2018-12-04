package ivagonz.antroma.elespinar.tournament;

public class Situacion {

    private String posicion;
    private String puntuacion;
    private String premios;

    public Situacion(String posicion, String puntuacion, String premios) {
        this.posicion = posicion;
        this.puntuacion = puntuacion;
        this.premios = premios;
    }


    public String getPosicion() {
        return posicion;
    }

    public String getPuntuacion() {
        return puntuacion;
    }

    public String getPremios() {
        return premios;
    }

}
