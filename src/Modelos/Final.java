package Modelos;

import java.util.Random;

public class Final {
    private Equipo equipoA;
    private Equipo equipoB;
    private Equipo ganador;

    public Final(Equipo equipoA, Equipo equipoB) {
        this.equipoA = equipoA;
        this.equipoB = equipoB;
    }

    public void jugarPartido() {
        int golesEquipoA = (int)(Math.random() * 4);
        int golesEquipoB = (int)(Math.random() * 4);

        System.out.println("\n=== FINAL DEL TORNEO ===");
        System.out.printf("%s %d - %d %s\n\n",
                equipoA.getNombre(), golesEquipoA, golesEquipoB, equipoB.getNombre());

        if (golesEquipoA == golesEquipoB) {
            System.out.println("¡Empate! Se definirá por penaltis.\n");
            jugarPenaltis();
        } else if (golesEquipoA > golesEquipoB) {
            ganador = equipoA;
            System.out.printf("¡Ganador: %s!\n", equipoA.getNombre());
        } else {
            ganador = equipoB;
            System.out.printf("¡Ganador: %s!\n", equipoB.getNombre());
        }
    }

    private void jugarPenaltis() {
        Random random = new Random();
        int penaltisA = 0;
        int penaltisB = 0;

        System.out.println("--- TANDA DE PENALTIS ---");

        // Serie inicial de 5 penaltis
        for (int i = 1; i <= 5; i++) {
            System.out.println("\nPenalti #" + i);

            boolean golA = random.nextBoolean();
            boolean golB = random.nextBoolean();

            if (golA) penaltisA++;
            if (golB) penaltisB++;

            System.out.printf("%s: %s\n", equipoA.getNombre(), golA ? "GOL" : "Falló");
            System.out.printf("%s: %s\n", equipoB.getNombre(), golB ? "GOL" : "Falló");
            System.out.printf("Parcial: %d - %d\n", penaltisA, penaltisB);
        }

        // Muerte súbita si hay empate
        if (penaltisA == penaltisB) {
            System.out.println("\n¡Empate! Muerte súbita...");
            int ronda = 6;

            while (penaltisA == penaltisB) {
                System.out.println("\nPenalti #" + ronda + " (Muerte súbita)");

                boolean golA = random.nextBoolean();
                boolean golB = random.nextBoolean();

                if (golA && !golB) penaltisA++;
                if (!golA && golB) penaltisB++;

                System.out.printf("%s: %s\n", equipoA.getNombre(), golA ? "GOL" : "Falló");
                System.out.printf("%s: %s\n", equipoB.getNombre(), golB ? "GOL" : "Falló");
                System.out.printf("Parcial: %d - %d\n", penaltisA, penaltisB);
                ronda++;
            }
        }

        this.ganador = (penaltisA > penaltisB) ? equipoA : equipoB;
        System.out.printf("\n¡GANADOR POR PENALTIS: %s!\n", ganador.getNombre());
        System.out.printf("Resultado final: %d - %d\n", penaltisA, penaltisB);
    }

    public Equipo getGanador() {
        return ganador;
    }
}