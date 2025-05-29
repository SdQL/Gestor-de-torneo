package Modelos;


public class Torneo {
    private Equipo[] equipos = new Equipo[10];
    private int contadorEquipos = 0;
    private boolean primerGolRegistrado = false;

    public void registrarEquipo(Equipo equipo){
        equipos[contadorEquipos] = equipo;
        contadorEquipos++;
    }

    public void jugarTodosContraTodosIdaVuelta() {
        System.out.println("\n--- Fase: Todos contra todos (ida y vuelta) ---");

        for (int i = 0; i < equipos.length ; i++) {
            for (int j = 0; j < equipos.length; j++) {
                if (i != j) {
                    System.out.println("Partido: " + equipos[i].getNombre() + " (Local) vs " + equipos[j].getNombre());

                    int golesLocal = (int)(Math.random() * 6); // Valor entre 0 y 5
                    System.out.printf("Goles de %s: %d\n", equipos[i].getNombre(), golesLocal);

                    int golesVisitante = (int)(Math.random() * 6); // Valor entre 0 y 5
                    System.out.printf("Goles de %s: %d\n", equipos[j].getNombre(), golesVisitante);

                    if (!primerGolRegistrado) {
                        if (golesLocal > 0) {
                            equipos[i].marcarPrimerGol();
                            primerGolRegistrado = true;
                        } else if (golesVisitante > 0) {
                            equipos[j].marcarPrimerGol();
                            primerGolRegistrado = true;
                        }
                    }

                    Partido partido = new Partido(equipos[i], equipos[j]);
                    partido.jugarPartido(golesLocal, golesVisitante);
                    imprimirTabla();

                }
            }
        }
    }



    public void imprimirTabla(){
        Equipo[] copia = new Equipo[10];
        System.arraycopy(equipos, 0, copia, 0, contadorEquipos);

        System.out.println("\nTabla de posiciones:");
        ordenarTabla(copia);
        for (Equipo e : copia) {


            System.out.println(e.getNombre() + " - Pts: " + e.getPuntos() +
                    " | GF: " + e.getGolesFavor() +
                    " | GC: " + e.getGolesContra() +
                    " | DG: " + (e.getDiferenciaGoles()));
        }
        System.out.println();
    }

    private void ordenarTabla(Equipo[] tabla) {
        for (int i = 0; i < equipos.length - 1; i++) {
            for (int j = 0; j < equipos.length - i - 1; j++) {
                Equipo e1 = tabla[j];
                Equipo e2 = tabla[j + 1];

                boolean debeIntercambiar = false;

                // 1. Puntos
                if (e1.getPuntos() < e2.getPuntos()) {
                    debeIntercambiar = true;
                }

                else if (e1.getPuntos() == e2.getPuntos()) {
                    // 2. Diferencia de goles
                    if (e1.getDiferenciaGoles() < e2.getDiferenciaGoles()) {
                        debeIntercambiar = true;

                    } else if (e1.getDiferenciaGoles() == e2.getDiferenciaGoles()) {
                        // 3. Goles a favor
                        if (e1.getGolesFavor() < e2.getGolesFavor()) {
                            debeIntercambiar = true;

                        } else if (e1.getGolesFavor() == e2.getGolesFavor()) {
                            // 4. Anotó primer gol del campeonato
                            if (!e1.anotoPrimerGol() && e2.anotoPrimerGol()) {
                                debeIntercambiar = true;
                            }
                        }
                    }
                }

                if (debeIntercambiar) {
                    // Intercambiar equipos[j] y equipos[j + 1]
                    tabla[j] = e2;
                    tabla[j + 1] = e1;
                }
            }
        }

        equipos = tabla; // Actualizar la referencia de equipos con la tabla ordenada
    }

    public Equipo[] equiposClasificados() {
        Equipo[] clasificados = new Equipo[8];

        for (int i = 0; i < 8; i++) {
            clasificados[i] = equipos[i];
        }

        return clasificados;
    }

}
