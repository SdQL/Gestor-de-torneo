package Modelos;


public class Torneo {


    private Equipo[] equipos = new Equipo[10];
    private int contadorEquipos = 0;
    private boolean primerGolRegistrado = false;

    public Equipo[] getEquipos() {
        return equipos;
    }

    public void registrarEquipo(Equipo equipo){
        equipos[contadorEquipos] = equipo;
        contadorEquipos++;
    }

    public void jugarTodosContraTodosIdaVuelta(Equipo[] equiposAJugar) {
        System.out.println("\n--- Fase: Todos contra todos (ida y vuelta) ---");

        for (int i = 0; i < equiposAJugar.length ; i++) {
            for (int j = 0; j < equiposAJugar.length; j++) {
                if (i != j) {
                    Equipo local = equiposAJugar[i];
                    Equipo visitante = equiposAJugar[j];

                    System.out.println("Partido: " + local.getNombre() + " (Local) vs " + visitante.getNombre());

                    int golesLocal = (int)(Math.random() * 6); // Valor entre 0 y 5
                    int golesVisitante = (int)(Math.random() * 6); // Valor entre 0 y 5

                    System.out.printf("Goles de %s: %d\n", local.getNombre(), golesLocal);
                    System.out.printf("Goles de %s: %d\n", visitante.getNombre(), golesVisitante);

                    if (!primerGolRegistrado) {
                        if (golesLocal > 0) {
                            local.marcarPrimerGol();
                            primerGolRegistrado = true;
                        } else if (golesVisitante > 0) {
                            visitante.marcarPrimerGol();
                            primerGolRegistrado = true;
                        }
                    }

                    Partido partido = new Partido(local, visitante);
                    partido.jugarPartido(golesLocal, golesVisitante);

                    imprimirTabla(equiposAJugar);
                }
            }
        }
    }



    public void imprimirTabla(Equipo[] tabla){

        System.out.println("\nTabla de posiciones:");
        ordenarTabla(tabla);
        for (Equipo e : tabla) {


            System.out.println(e.getNombre() + " - Pts: " + e.getPuntos() +
                    " | GF: " + e.getGolesFavor() +
                    " | GC: " + e.getGolesContra() +
                    " | DG: " + (e.getDiferenciaGoles()));
        }
        System.out.println();
    }

    private void ordenarTabla(Equipo[] tabla) {
        for (int i = 0; i < tabla.length - 1; i++) {
            for (int j = 0; j < tabla.length - i - 1; j++) {
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

    }

    public Equipo[] equiposClasificados(Equipo[] grupo, int cantidadClasificados) {
        Equipo[] clasificados = new Equipo[cantidadClasificados];

        for (int i = 0; i < cantidadClasificados; i++) {
            clasificados[i] = grupo[i];
        }

        return clasificados;
    }

}
