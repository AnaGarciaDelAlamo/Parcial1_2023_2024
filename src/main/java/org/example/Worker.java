package org.example;

import java.util.List;
import java.util.concurrent.BlockingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

class Worker implements Runnable {
    private int componentsToProduce;
    private List<Integer> frequencyData;
    private DistributionPanel distributionPanel;

    public Worker(int componentsToProduce, List<Integer> frequencyData, DistributionPanel distributionPanel) {
        this.componentsToProduce = componentsToProduce;
        this.frequencyData = frequencyData;
        this.distributionPanel = distributionPanel;
    }

    @Override
    public void run() {
        for (int i = 0; i < componentsToProduce; i++) {
            int component = (int) (Math.random() * 10); // Valores entre 0 y 9
            synchronized (frequencyData) {
                frequencyData.add(component);
            }
            distributionPanel.setFrequencyData(frequencyData);
            distributionPanel.repaint();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

