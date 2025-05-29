package Modelos;

public class EstadisticasEquipo {
    private String nombre;
    private String pais;
    private int golesFavor;
    private int golesContra;
    private int puntos;
    private boolean anotoPrimerGol;

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
    public boolean isAnotoPrimerGol() {
        return anotoPrimerGol;
    }


    public EstadisticasEquipo(Equipo equipo) {
        this.nombre = equipo.getNombre();
        this.pais = equipo.getPais();
        this.golesFavor = equipo.getGolesFavor();
        this.golesContra = equipo.getGolesContra();
        this.puntos = equipo.getPuntos();
        this.anotoPrimerGol = equipo.anotoPrimerGol();
    }


}
