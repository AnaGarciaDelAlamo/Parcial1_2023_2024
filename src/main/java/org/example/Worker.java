package org.example;

import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

class Worker implements Runnable {
    private List<Integer> frequencyData;
    private Random random = new Random();

    public Worker(List<Integer> frequencyData) {
        this.frequencyData = frequencyData;
    }

    @Override
    public void run() {
        while (true) {
            int component = random.nextInt(10); // Valores entre 0 y 9
            synchronized (frequencyData) {
                frequencyData.add(component);
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
