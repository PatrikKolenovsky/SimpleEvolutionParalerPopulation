package com.company;

import java.util.Random;

public class Individual {
    public int fitness = 0;
    public int[] genes;

    public Individual(int genesLength) {
        this.genes = new int[genesLength];
        Random rn = new Random();
        //Set genes randomly for each individual
        for (int i = 0; i < genesLength; i++) {
            this.genes[i] = (Math.abs(rn.nextInt() % 2));
        }

        fitness = 0;
    }

    public void printGenes() {
        for (int chrom : genes) {
            System.out.print(chrom);
        }
        System.out.println();
    }

    public int getGene(int index) {
        return genes[index];
    }

    public void setGene(int gene, int index) {
        this.genes[index] = gene;
    }

    //Calculate fitness
    public void setFitness() {
        this.fitness = 0;
        for (int gene : this.genes) {
            if (gene == 1) {
                ++this.fitness;
            }
        }
    }

    public int getFitness() {
        return this.fitness;
    }
}
