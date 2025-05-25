package Modelos;

public class Equipo {
    private String nombre;
    private String pais;
    private int golesFavor = 0;
    private int golesContra = 0;
    private int puntos = 0;
    private boolean anotoPrimerGol = false;

    public boolean anotoPrimerGol() {
        return anotoPrimerGol;
    }

    public void marcaPrimerGol() {
        anotoPrimerGol = true;
    }


    public Equipo(String nombre, String pais) {
        this.nombre = nombre;
        this.pais = pais;
    }

    public String getNombre() {
        return nombre;
    }
    public String getPais() {
        return pais;
    }
    public int getGolesFavor() {
        return golesFavor;
    }
    public int getGolesContra() {
        return golesContra;
    }
    public int getPuntos() {
        return puntos;
    }
    public int getDiferenciaGoles() {
        return golesFavor - golesContra;
    }

    public void agregarGoles(int golesFavor, int golesContra) {
        this.golesFavor += golesFavor;
        this.golesContra += golesContra;
    }

    public void sumarPuntos(int puntos) {
        this.puntos += puntos;
    }

}
