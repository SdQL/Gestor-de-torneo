import Modelos.*;

import  java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Torneo torneo = new Torneo();

        registrarEquipos(torneo, sc);
        torneo.imprimirTabla();
        torneo.jugarTodosContraTodosIdaVuelta();
    }

    private static void registrarEquipos(Torneo torneo, Scanner sc){
        for (int i = 0; i < 10; i++){
            System.out.println("Ingrese el nombre del equipo " + (i + 1) + ": ");
            String nombre = sc.nextLine();
            System.out.println("Ingrese el pais del equipo " + (i + 1) + ": ");
            String pais = sc.nextLine();

            Equipo equipo = new Equipo(nombre, pais);
            torneo.registrarEquipo(equipo);

            System.out.println("Equipo registrado: " + nombre + " de " + pais);
        }
    }
}