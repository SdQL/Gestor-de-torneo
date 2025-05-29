import Modelos.*;


import  java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Torneo torneo = new Torneo();

        registrarEquipos(torneo, sc);
        Equipo [] equiposRegistrados = torneo.getEquipos();
        torneo.imprimirTabla(equiposRegistrados);

        torneo.jugarTodosContraTodosIdaVuelta(equiposRegistrados);

        Equipo []clasificados = torneo.equiposClasificados(equiposRegistrados, 8);
        Octavos octavos = new Octavos(clasificados);
        octavos.mostrarGrupos();
        Equipo[] grupoA = octavos.getGrupoA();
        Equipo[] grupoB = octavos.getGrupoB();
        System.out.println("\nJugando octavos de final: (Grupo A)");
        torneo.jugarTodosContraTodosIdaVuelta(grupoA);
        System.out.println("\nJugando octavos de final: (Grupo B)");
        torneo.jugarTodosContraTodosIdaVuelta(grupoB);

        Equipo[] finalistaGrupoA = torneo.equiposClasificados(grupoA, 1);
        Equipo[] finalistaGrupoB = torneo.equiposClasificados(grupoB, 1);

        System.out.println("\nFinalistas de cada grupo:");
        System.out.println("Grupo A: " + finalistaGrupoA[0].getNombre());
        System.out.println("Grupo B: " + finalistaGrupoB[0].getNombre());




    }

    private static void registrarEquipos(Torneo torneo, Scanner sc){
        for (int i = 0; i < 10; i++){
            System.out.println("Ingrese el nombre del equipo " + (i + 1) + ": ");
            String nombre = sc.nextLine();
            //System.out.println("Ingrese el pais del equipo " + (i + 1) + ": ");
            String pais = "Colombia";

            Equipo equipo = new Equipo(nombre, pais);
            torneo.registrarEquipo(equipo);

            System.out.println("Equipo registrado: " + nombre + " de " + pais);
        }
    }
}