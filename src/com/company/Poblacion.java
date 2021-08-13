package com.company;

public class Poblacion {

    private Individuo[] individuos;
    private int tamPoblacion;

    //crea una poblacion con los individuos aleatorios
    public Poblacion(int numGenes, int tamPop) {
        tamPoblacion = tamPop;
        individuos = new Individuo[tamPop];
        for (int i = 0; i < individuos.length; i++) {
            individuos[i] = new Individuo(numGenes);
        }
    }

    //crea una poblacion con individuos sin valor, sera asignado posteriormente
    public Poblacion(int tamPop) {
        tamPoblacion = tamPop;
        individuos = new Individuo[tamPop];
        for (int i = 0; i < individuos.length; i++) {
            individuos[i] = null;
        }
    }

    //coloca  un individuo en cierta posicion de la poblacion
    public void setIndividuo(Individuo individuo, int posicion) {
        individuos[posicion] = individuo;
    }

    //coloca un indivíduo en la proxima posicion disponible de la poblacion
    public void setIndividuo(Individuo individuo) {
        for (int i = 0; i < individuos.length; i++) {
            if (individuos[i] == null) {
                individuos[i] = individuo;
                return;
            }
        }
    }

    //verifica si algun individuo de la poblacion posee una solucion
    public boolean tieneSolucion(String solucion) {
        Individuo i = null;
        for (int j = 0; j < individuos.length; j++) {
            if (individuos[j].getGenes().equals(solucion)) {
                i = individuos[j];
                break;
            }
        }
        if (i == null) {
            return false;
        }
        return true;
    }

    // ordena la poblacion por el valor de fitness de cada individuo ,del mayor valor al menor, de este modo si se quiere obtener el mejor individuo de esta poblacion , se puede acceder por la posicion 0 del array de indivíduos
    public void ordenaPoblacion() {
        boolean cambio = true;
        while (cambio) {
            cambio = false;
            for (int i = 0; i < individuos.length - 1; i++) {
                if (individuos[i].getFitness() < individuos[i + 1].getFitness()) {
                    Individuo temp = individuos[i];
                    individuos[i] = individuos[i + 1];
                    individuos[i + 1] = temp;
                    cambio = true;
                }
            }
        }
    }

    //número de indivíduos existentes na população
    public int getNumIndividuos() {
        int num = 0;
        for (int i = 0; i < individuos.length; i++) {
            if (individuos[i] != null) {
                num++;
            }
        }
        return num;
    }

    public int getTamPoblacion() {
        return tamPoblacion;
    }

    public Individuo getIndivduo(int pos) {
        return individuos[pos];
    }
}