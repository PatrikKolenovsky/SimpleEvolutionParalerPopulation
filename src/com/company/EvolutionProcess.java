package com.company;

import java.util.Random;

public class EvolutionProcess {

    boolean isInitalize = false;
    int maxGeneration = 0;
    int numberOfGenerations = 0;
    int populationSize = 0;
    int requiredNumberOfOnes = 0;
    int mutationRate = 0;
    public Population population;
    Individual fittest;
    Individual secondFittest;

    public EvolutionProcess(int maxGeneration, int populationSize, int requiredNumberOfOnes, int mutationRate) {
        this.maxGeneration = maxGeneration;
        this.populationSize = populationSize;
        this.requiredNumberOfOnes = requiredNumberOfOnes;
        this.mutationRate = mutationRate;
    }

    public void startProcess() {
        // START
        // Generate the initial population
        if (!isInitalize) {
            Population population = new Population();
            population.initializePopulation(this.populationSize, this.requiredNumberOfOnes);
            this.population = population;
            isInitalize = true;
        }
        // Compute fitness
        this.population.calculateFitness();
//        population.getPopulationInfo();
//        System.out.println("Evolution is running");
        // REPEAT
        while (this.population.getFittestIndividual().getFitness() < this.requiredNumberOfOnes) {
            this.numberOfGenerations++;
            this.selection(population);
            this.crossover();
            this.addFittestOffspring(population);
            this.mutation(population);
            this.population.calculateFitness();
//            population.getPopulationInfo();
            if (this.numberOfGenerations > maxGeneration){
//                System.out.println("Max number of G run out");
                break;
            }
        }

//        System.out.println("\nSolution found in generation " + numberOfGenerations);
        this.population.getPopulationInfo();
//        System.out.println();
    }

    void selection(Population population) {
        //Select the most fittest individual
        fittest = population.getFittestIndividual();

        //Select the second most fittest individual
        secondFittest = population.getSecFittestIndividual();
    }

    //Crossover
    void crossover() {
        Random rn = new Random();
        //Select a random crossover point
        int crossOverPoint = rn.nextInt(requiredNumberOfOnes);

        //Swap values among parents
        for (int i = 0; i < crossOverPoint; i++) {
            int temp = this.fittest.getGene(i);
            fittest.setGene(this.secondFittest.getGene(i), i);
            secondFittest.setGene(temp, i);
        }

    }

    //Mutation
    void mutation(Population population) {
        Random r = new Random();
        int randomInt = r.nextInt(100) + 1;
        if (randomInt < this.mutationRate) {
//            System.out.println("Mutated");
            Random rn = new Random();

            //Select a random mutation point
            int mutationPoint = rn.nextInt(requiredNumberOfOnes);

            //Flip values at the mutation point
            if (fittest.genes[mutationPoint] == 0) {
                fittest.genes[mutationPoint] = 1;
            } else {
                fittest.genes[mutationPoint] = 0;
            }

        }
    }

    //Get fittest offspring
    Individual getFittestOffspring() {
        if (fittest.getFitness() > secondFittest.getFitness()) {
            return fittest;
        }
        return secondFittest;
    }


    //Replace least fittest individual from most fittest offspring
    void addFittestOffspring(Population population) {

        population.setFittest();
        population.setSecondFittest();
        //Replace least fittest individual from most fittest offspring
        population.setLeastFittest(getFittestOffspring());
    }

}
