package Modelos;
import java.util.Scanner;

public class Torneo {
    private final Equipo[] equipos = new Equipo[10];
    private int contadorEquipos = 0;

    public void registrarEquipo(Equipo equipo){
        equipos[contadorEquipos] = equipo;
        contadorEquipos++;
    }

    public void jugarTodosContraTodosIdaVuelta() {
        System.out.println("\n--- Fase: Todos contra todos (ida y vuelta) ---");
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < equipos.length; i++) {
            for (int j = 0; j < equipos.length; j++) {
                if (i != j) {
                    System.out.println("Partido: " + equipos[i].getNombre() + " (Local) vs " + equipos[j].getNombre());

                    System.out.print("Goles de " + equipos[i].getNombre() + ": ");
                    int golesLocal = sc.nextInt();

                    System.out.print("Goles de " + equipos[j].getNombre() + ": ");
                    int golesVisitante = sc.nextInt();


                    Partido partido = new Partido(equipos[i], equipos[j]);
                    partido.jugarPartido(golesLocal, golesVisitante);
                    imprimirTabla();

                }
            }
        }
    }



    public void imprimirTabla(){
        System.out.println("\nTabla de posiciones:");
        for (Equipo e : equipos) {

            // ordenarTabla();
            System.out.println(e.getNombre() + " - Pts: " + e.getPuntos() +
                    " | GF: " + e.getGolesFavor() +
                    " | GC: " + e.getGolesContra() +
                    " | DG: " + (e.getGolesFavor() - e.getGolesContra()));
        }
    }

    private void ordenarTabla() {
        return;
    }


}
