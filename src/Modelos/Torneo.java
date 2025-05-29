package Modelos;

public class Torneo {
    private Equipo[] equipos = new Equipo[10];
    private int contadorEquipos = 0;
    private boolean primerGolRegistrado = false;

    public Equipo[] getEquipos() {
        return equipos;
    }

    public void registrarEquipo(Equipo equipo) {
        equipos[contadorEquipos] = equipo;
        contadorEquipos++;
    }

    public void jugarTodosContraTodosIdaVuelta(Equipo[] equiposAJugar) {
        System.out.println("\n==============================================");
        System.out.println("      FASE: TODOS CONTRA TODOS (IDA Y VUELTA)");
        System.out.println("==============================================");

        for (int i = 0; i < equiposAJugar.length; i++) {
            for (int j = 0; j < equiposAJugar.length; j++) {
                if (i != j) {
                    Equipo local = equiposAJugar[i];
                    Equipo visitante = equiposAJugar[j];

                    System.out.println("\n--------------------------------");
                    System.out.println("PARTIDO: " + local.getNombre() + " vs " + visitante.getNombre());
                    System.out.println("--------------------------------");

                    int golesLocal = (int)(Math.random() * 6);
                    int golesVisitante = (int)(Math.random() * 6);

                    System.out.println("  " + local.getNombre() + ": " + golesLocal);
                    System.out.println("  " + visitante.getNombre() + ": " + golesVisitante);
                    System.out.println("  Resultado: " + golesLocal + " - " + golesVisitante);

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
                }
            }
        }
        imprimirTabla(equiposAJugar);
    }

    public EstadisticasEquipo[] guardarYReiniciar(Equipo[] equipos) {
        EstadisticasEquipo[] resumen = new EstadisticasEquipo[equipos.length];

        for (int i = 0; i < resumen.length; i++) {
            resumen[i] = new EstadisticasEquipo(equipos[i]);
            equipos[i].reiniciarEstadisticas();
        }

        return resumen;
    }

    public Equipo primerClasificado(Equipo[] equipos) {
        return equipos[0];
    }

    public void imprimirTabla(Equipo[] tabla) {
        System.out.println("\n=================================");
        System.out.println("       TABLA DE POSICIONES");
        System.out.println("=================================");

        ordenarTabla(tabla);
        System.out.println("EQUIPO               PTS  GF  GC  DG");
        System.out.println("---------------------------------");

        for (Equipo e : tabla) {
            System.out.printf("%-20s %3d %3d %3d %3d\n",
                    e.getNombre(),
                    e.getPuntos(),
                    e.getGolesFavor(),
                    e.getGolesContra(),
                    e.getDiferenciaGoles());
        }
        System.out.println("=================================\n");
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

        for(int i = 0; i < cantidadClasificados; i++) {
            clasificados[i] = grupo[i];
        }

        return clasificados;
    }
}