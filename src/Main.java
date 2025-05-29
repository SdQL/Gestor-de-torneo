import Modelos.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Torneo torneo = new Torneo();

        registrarEquipos(torneo, sc);
        Equipo[] equiposRegistrados = torneo.getEquipos();
        torneo.imprimirTabla(equiposRegistrados);

        // Fase 1: Todos contra todos
        torneo.jugarTodosContraTodosIdaVuelta(equiposRegistrados);
        EstadisticasEquipo[] estadisticasFase1 = torneo.guardarYReiniciar(equiposRegistrados);

        // Octavos de final
        Equipo[] clasificados = torneo.equiposClasificados(equiposRegistrados, 8);
        Octavos octavos = new Octavos(clasificados);
        octavos.mostrarGrupos();

        Equipo[] grupoA = octavos.getGrupoA();
        Equipo[] grupoB = octavos.getGrupoB();

        System.out.println("\nJugando octavos de final: (Grupo A)");
        torneo.jugarTodosContraTodosIdaVuelta(grupoA);
        System.out.println("\nJugando octavos de final: (Grupo B)");
        torneo.jugarTodosContraTodosIdaVuelta(grupoB);

        // Guardar estadísticas de fase 2 (octavos)
        EstadisticasEquipo[] estadisticasFase2 = torneo.guardarYReiniciar(clasificados);

        Equipo finalistaGrupoA = torneo.primerClasificado(grupoA);
        Equipo finalistaGrupoB = torneo.primerClasificado(grupoB);
        System.out.println("Finalistas: " + finalistaGrupoA.getNombre() + " vs " + finalistaGrupoB.getNombre());

        // Final
        Final partidoFinal = new Final(finalistaGrupoA, finalistaGrupoB);
        partidoFinal.jugarPartido();
        Equipo ganador = partidoFinal.getGanador();

        mostrarEstadisticasEquipo(ganador, estadisticasFase1, estadisticasFase2);
    }

    private static void registrarEquipos(Torneo torneo, Scanner sc) {
        for (int i = 0; i < 10; i++) {
            System.out.println("Ingrese el nombre del equipo " + (i + 1) + ": ");
            String nombre = sc.nextLine();
            System.out.println("Ingrese el pais del equipo " + (i + 1) + ": ");
            String pais = sc.nextLine();

            Equipo equipo = new Equipo(nombre, pais);
            torneo.registrarEquipo(equipo);

            System.out.println("Equipo registrado: " + nombre + " de " + pais);
        }
    }

    private static void mostrarEstadisticasEquipo(Equipo ganador, EstadisticasEquipo[] estadisticasFase1, EstadisticasEquipo[] estadisticasFase2) {
        System.out.println("\n=== ESTADÍSTICAS DEL CAMPEÓN ===");
        System.out.println("Equipo campeón: " + ganador.getNombre());

        // Estadísticas fase 1
        for (EstadisticasEquipo estadistica : estadisticasFase1) {
            if (estadistica.getNombre().equals(ganador.getNombre())) {
                System.out.println("Puntos en fase de grupos: " + estadistica.getPuntos());
            }
        }

        // Estadísticas fase 2 (octavos)
        for (EstadisticasEquipo estadistica : estadisticasFase2) {
            if (estadistica.getNombre().equals(ganador.getNombre())) {
                System.out.println("Goles en octavos: " + estadistica.getGolesFavor());
            }
        }
    }
}