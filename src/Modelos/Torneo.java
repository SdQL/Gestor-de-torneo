package Modelos;


public class Torneo {
    private final Equipo[] equipos = new Equipo[10];
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
        System.out.println("\nTabla de posiciones:");
        for (Equipo e : equipos) {

            ordenarTabla();
            System.out.println(e.getNombre() + " - Pts: " + e.getPuntos() +
                    " | GF: " + e.getGolesFavor() +
                    " | GC: " + e.getGolesContra() +
                    " | DG: " + (e.getDiferenciaGoles()));
        }
    }

    private void ordenarTabla() {
        for (int i = 0; i < equipos.length - 1; i++) {
            for (int j = 0; j < equipos.length - i - 1; j++) {
                Equipo e1 = equipos[j];
                Equipo e2 = equipos[j + 1];

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
                    equipos[j] = e2;
                    equipos[j + 1] = e1;
                }
            }
        }
    }



}
