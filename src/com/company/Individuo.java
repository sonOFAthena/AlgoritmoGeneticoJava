package com.company;

import java.util.Random;

public class Individuo {

    private String genes = "";
    private int fitness = 0;

    //genera un individuo aleatorio
    public Individuo(int numGenes) {
        genes = "";
        Random r = new Random();

        String caracteres = Algoritmo.getCaracteres();

        for (int i = 0; i < numGenes; i++) {
            genes += caracteres.charAt(r.nextInt(caracteres.length()));
        }

        generaFitness();
    }

    //crea un individuo con los genes definidos
    public Individuo(String genes) {
        this.genes = genes;

        Random r = new Random();
        //se for mutar, cria um gene aleatório
        if (r.nextDouble() <= Algoritmo.getTasaDeMutacion()) {
            String caracteres = Algoritmo.getCaracteres();
            String genNuevo="";
            int posAleatoria = r.nextInt(genes.length());
            for (int i = 0; i < genes.length(); i++) {
                if (i==posAleatoria){
                    genNuevo += caracteres.charAt(r.nextInt(caracteres.length()));
                }else{
                    genNuevo += genes.charAt(i);
                }

            }
            this.genes = genNuevo;
        }
        generaFitness();
    }

    //genera el valor de fitness , sera calculada por el numero de bit del gen igual al de la solucion
    private void generaFitness() {
        String solucion = Algoritmo.getSolucion();
        for (int i = 0; i < solucion.length(); i++) {
            if (solucion.charAt(i) == genes.charAt(i)) {
                fitness++;
            }
        }
    }

    public int getFitness() {
        return fitness;
    }

    public String getGenes() {
        return genes;
    }
}
