package com.company;
public class PopulationThread extends Thread {

    @Override
    public void run() {
        EvolutionProcess ep = new EvolutionProcess(1000, 10000, 40, 10);
        ep.startProcess();
    }

}

