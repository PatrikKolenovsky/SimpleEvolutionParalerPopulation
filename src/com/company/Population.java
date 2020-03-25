package com.company;

public class Population {

    private Individual[] individuals;
    private Individual fittestIndividual;
    private Individual secFittestIndividual;
    private Individual leastFittestIndividual;

    public void getPopulationInfo() {
        System.out.println("Fittest: fit " + fittestIndividual.getFitness() + " code:");
        fittestIndividual.printGenes();
    }

    public Individual[] getIndividuals() {
        return individuals;
    }

    public Individual getFittestIndividual() {
        return fittestIndividual;
    }

    public Individual getSecFittestIndividual() {
        return secFittestIndividual;
    }

    public void initializePopulation(int size, int genesLenght) {
        this.individuals = new Individual[size];
        for (int i = 0; i < size; i++) {
            this.individuals[i] = new Individual(genesLenght);
        }
        setFittest();
        setSecondFittest();
        setLeastFittest();
    }

    //Get the fittest individual
    public void setFittest() {
        int maxFit = Integer.MIN_VALUE;
        int maxFitIndex = 0;
        for (int i = 0; i < individuals.length; i++) {
            if (maxFit <= individuals[i].getFitness()) {
                maxFit = individuals[i].getFitness();
                maxFitIndex = i;
            }
        }
        this.fittestIndividual = individuals[maxFitIndex];
    }

    //Get the second most fittest individual
    public void setSecondFittest() {
        int maxFit1 = 0;
        int maxFit2 = 0;
        for (int i = 0; i < individuals.length; i++) {
            if (individuals[i].getFitness() > individuals[maxFit1].getFitness()) {
                maxFit2 = maxFit1;
                maxFit1 = i;
            } else if (individuals[i].getFitness() > individuals[maxFit2].getFitness()) {
                maxFit2 = i;
            }
        }
        this.secFittestIndividual = individuals[maxFit2];
    }

    public void setLeastFittest() {
        int minFitVal = Integer.MAX_VALUE;
        int minFitIndex = 0;
        for (int i = 0; i < individuals.length; i++) {
            if (minFitVal >= individuals[i].getFitness()) {
                minFitVal = individuals[i].getFitness();
                minFitIndex = i;
            }
        }
        this.leastFittestIndividual = individuals[minFitIndex];
    }

    public void setLeastFittest(Individual leastFittestIndividual) {
        this.leastFittestIndividual = leastFittestIndividual;
    }

    //Calculate fitness of each individual
    public void calculateFitness() {
        for (Individual individual : individuals) {
            individual.setFitness();
        }
    }
}
