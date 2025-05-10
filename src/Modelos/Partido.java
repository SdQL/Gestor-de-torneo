package Modelos;

public class Partido {
    private Equipo equipoA;
    private Equipo equipoB;
    private int golesEquipoA;
    private int golesEquipoB;

    public Partido(Equipo equipoA, Equipo equipoB) {
        this.equipoA = equipoA;
        this.equipoB = equipoB;
    }

    public void jugarPartido(int golesEquipoA, int golesEquipoB){
        this.golesEquipoA = golesEquipoA;
        this.golesEquipoB = golesEquipoB;

        equipoA.agregarGoles(golesEquipoA, golesEquipoB);
        equipoB.agregarGoles(golesEquipoB, golesEquipoA);

        finalPartido(this.golesEquipoA, this.golesEquipoB);
    }

    public void finalPartido(int golesEquipoA, int golesEquipoB) {
        if(golesEquipoA > golesEquipoB) {
            equipoA.sumarPuntos(3);
            System.out.println("El equipo " + equipoA.getNombre() + " ha ganado el partido " +  golesEquipoA + " - " + golesEquipoB);
        } else if (golesEquipoA < golesEquipoB) {
            equipoB.sumarPuntos(3);
            System.out.println("El equipo " + equipoB.getNombre() + " ha ganado el partido " +  golesEquipoB + " - " + golesEquipoA);
        } else {
            equipoA.sumarPuntos(1);
            equipoB.sumarPuntos(1);
            System.out.println("El partido ha terminado en empate " +  golesEquipoA + " - " + golesEquipoB);
        }
    }


}
