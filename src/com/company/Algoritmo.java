package com.company;

import java.util.Random;

public class Algoritmo {

    private static String solucion;
    private static double tasaDeCrossover;
    private static double tasaDeMutacion;
    private static String caracteres;

    public static Poblacion nuevaGeneracion(Poblacion populacao, boolean elitismo) {
        Random r = new Random();
        //nueva poblacion del mismo tamaño de la antigua
        Poblacion nuevaPoblacion = new Poblacion(populacao.getTamPoblacion());

        //si tiene elitismo , mantenga el menor individuo de la generacion actual
        if (elitismo) {
            nuevaPoblacion.setIndividuo(populacao.getIndivduo(0));
        }

        //inserte nuevos individuos en la nueva poblacion, hasta alcanzar el tamaño maximo
        while (nuevaPoblacion.getNumIndividuos() < nuevaPoblacion.getTamPoblacion()) {
            //selecciona los padres para el torneo
            Individuo[] pais = selecionTorneo(populacao);

            Individuo[] filhos = new Individuo[2];

            // verifica la tasa de crossover, si: realiza el crossover; no: mantiene los padres seleccionados para la proxima generación
            if (r.nextDouble() <= tasaDeCrossover) {
                filhos = crossover(pais[1], pais[0]);
            } else {
                filhos[0] = new Individuo(pais[0].getGenes());
                filhos[1] = new Individuo(pais[1].getGenes());
            }

            //adiciona los hijos de la nueva generacion
            nuevaPoblacion.setIndividuo(filhos[0]);
            nuevaPoblacion.setIndividuo(filhos[1]);
        }

        //ordena la nueva poblacion
        nuevaPoblacion.ordenaPoblacion();
        return nuevaPoblacion;
    }

    public static Individuo[] crossover(Individuo individuo1, Individuo individuo2) {
        Random r = new Random();

        //sortea el punto de corte
        int puntoCorte1 = r.nextInt((individuo1.getGenes().length()/2) -2) + 1;
        int puntoCorte2 = r.nextInt((individuo1.getGenes().length()/2) -2) + individuo1.getGenes().length()/2;

        Individuo[] hijos = new Individuo[2];

        //tomar los genes de los padres
        String genPadre1 = individuo1.getGenes();
        String genPadre2 = individuo2.getGenes();

        String genHijo1;
        String genHijo2;

        //realiza el corte,
        genHijo1 = genPadre1.substring(0, puntoCorte1);
        genHijo1 += genPadre2.substring(puntoCorte1, puntoCorte2);
        genHijo1 += genPadre1.substring(puntoCorte2, genPadre1.length());

        genHijo2 = genPadre2.substring(0, puntoCorte1);
        genHijo2 += genPadre1.substring(puntoCorte1, puntoCorte2);
        genHijo2 += genPadre2.substring(puntoCorte2, genPadre2.length());

        //crear el nuevo individuo con los genes de los padres
        hijos[0] = new Individuo(genHijo1);
        hijos[1] = new Individuo(genHijo2);

        return hijos;
    }

    public static Individuo[] selecionTorneo(Poblacion populacao) {
        Random r = new Random();
        Poblacion poblacionIntermedia = new Poblacion(3);

        //seleciona 3 indivíduos aleatoriamente en la poblacion
        poblacionIntermedia.setIndividuo(populacao.getIndivduo(r.nextInt(populacao.getTamPoblacion())));
        poblacionIntermedia.setIndividuo(populacao.getIndivduo(r.nextInt(populacao.getTamPoblacion())));
        poblacionIntermedia.setIndividuo(populacao.getIndivduo(r.nextInt(populacao.getTamPoblacion())));

        //ordena la poblacion
        poblacionIntermedia.ordenaPoblacion();

        Individuo[] padres = new Individuo[2];

        //seleciona los 2 mejores de esta poblacion
        padres[0] = poblacionIntermedia.getIndivduo(0);
        padres[1] = poblacionIntermedia.getIndivduo(1);

        return padres;
    }

    public static String getSolucion() {
        return solucion;
    }

    public static void setSolucion(String solucion) {
        Algoritmo.solucion = solucion;
    }

    public static double getTasaDeCrossover() {
        return tasaDeCrossover;
    }

    public static void setTasaDeCrossover(double tasaDeCrossover) {
        Algoritmo.tasaDeCrossover = tasaDeCrossover;
    }

    public static double getTasaDeMutacion() {
        return tasaDeMutacion;
    }

    public static void setTasaDeMutacion(double tasaDeMutacion) {
        Algoritmo.tasaDeMutacion = tasaDeMutacion;
    }

    public static String getCaracteres() {
        return caracteres;
    }

    public static void setCaracteres(String caracteres) {
        Algoritmo.caracteres = caracteres;
    }


}