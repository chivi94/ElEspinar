package ivagonz.antroma.elespinar.tickets;

/**
 * Created by Ivan on 09/02/2017.
 */

public class Entrada {
    private String informacion;
    private String precio;

    public Entrada(String informacion, String precio) {
        this.informacion = informacion;
        this.precio = precio;
    }


    public String getInformacion() {
        return informacion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }


}