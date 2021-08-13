package com.company;

public class WordAG {

    public static void main(String[] args) {

        //Definir solución
        Algoritmo.setSolucion("Politécnico Colombiano Jaime Isaza Cadavid");
        //Define los caracteres existentes
        Algoritmo.setCaracteres("!,.:;?áÁãÃâÂõÕôÔóÓéêÉÊíQWERTYUIOPASDFGHJKLÇZXCVBNMqwertyuiopasdfghjklçzxcvbnm1234567890 ");
        //tasa de crossover de 60%
        Algoritmo.setTasaDeCrossover(0.6);
        //tasa de mutacion de 3%
        Algoritmo.setTasaDeMutacion(0.3);
        //elitismo
        boolean eltismo = true;
        //tamaño de la poblacion
        int tamPop = 100;
        //numero máximo de generaciones
        int numMaxGeraciones = 10000;

        //define el número de genes del indivíduo basado en la solución
        int numGenes = Algoritmo.getSolucion().length();

        //crear la primera poblacion aleatoria
        Poblacion poblacion = new Poblacion(numGenes, tamPop);

        boolean tieneSolucion = false;
        int generacion = 0;

        System.out.println("Iniciando... Fitness de la solución: "+ Algoritmo.getSolucion().length());

        //loop hasta el criterio de parada
        while (!tieneSolucion && generacion < numMaxGeraciones) {
            generacion++;

            //crear nueva poblacion
            poblacion = Algoritmo.nuevaGeneracion(poblacion, eltismo);

            System.out.println("Generacion " + generacion + " | Fitness: " + poblacion.getIndivduo(0).getFitness() + " | Mejor: " + poblacion.getIndivduo(0).getGenes());

            //verificar si tiene la solucion
            tieneSolucion = poblacion.tieneSolucion(Algoritmo.getSolucion());
        }

        if (generacion == numMaxGeraciones) {
            System.out.println("Número Maximo de Generaciones | " + poblacion.getIndivduo(0).getGenes() + " " + poblacion.getIndivduo(0).getFitness());
        }

        if (tieneSolucion) {
            System.out.println("Encontrado resultado en la Generacion " + generacion + " | " + poblacion.getIndivduo(0).getGenes() + " (Fitness: " + poblacion.getIndivduo(0).getFitness() + ")");
        }
    }
}
