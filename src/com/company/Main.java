package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        runNoMultithread();
        runMultithread();
    }

    static void runNoMultithread() {
        long startTime = System.currentTimeMillis();
        EvolutionProcess ep = new EvolutionProcess(4000, 10000, 40, 10);
        ep.startProcess();
        long endTime = System.currentTimeMillis();
        long deltaTime = endTime - startTime;
        System.out.println("No multiThreading with same number of generation took about " + deltaTime + " ms.");
    }

    static void runMultithread() {


        int THREAD_COUNT = Runtime.getRuntime().availableProcessors();
        System.out.println("Threads: " + THREAD_COUNT);

        long startTime = System.currentTimeMillis();
        List<PopulationThread> threads = new ArrayList<>();

        for (int i = 0; i < THREAD_COUNT; i++) {
            PopulationThread thread = new PopulationThread();
            threads.add(thread);
        }

        for (PopulationThread thread : threads) {
            thread.start();
        }

        long endTime = System.currentTimeMillis();
        long deltaTime = endTime - startTime;
        System.out.println("Threads: " + THREAD_COUNT + " took about " + deltaTime + " ms.");
    }

}