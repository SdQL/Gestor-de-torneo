package Modelos;

public class Octavos {

    private Equipo[] clasificados;
    private Equipo[] grupoA;
    private Equipo[] grupoB;

    public Octavos(Equipo[] clasificados) {
        this.clasificados = clasificados;
        this.grupoA = new Equipo[4];
        this.grupoB = new Equipo[4];
        armarGrupos();
    }

    public Equipo[] getGrupoA() {
        return grupoA;
    }

    public Equipo[] getGrupoB() {
        return grupoB;
    }

    public void mostrarGrupos() {
        System.out.println("Grupo A:");
        for (Equipo e : grupoA) {
            System.out.println("- " + e.getNombre());
        }

        System.out.println("Grupo B:");
        for (Equipo e : grupoB) {
            System.out.println("- " + e.getNombre());
        }
    }

    private void armarGrupos(){
        int indexA=0, indexB=0;
        for (int i = 0; i < clasificados.length; i++){
            if((i+1) % 2 == 0){
                grupoA[indexA] = clasificados[i];
                indexA++;
            } else {
                grupoB[indexB] = clasificados[i];
                indexB++;
            }
        }
    }
}
