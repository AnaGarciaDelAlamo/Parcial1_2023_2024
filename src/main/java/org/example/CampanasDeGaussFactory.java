package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class CampanasDeGaussFactory {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Campanas de Gauss Factory");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        DistributionPanel distributionPanel = new DistributionPanel();
        frame.add(distributionPanel);
        frame.setVisible(true);

        List<Integer> frequencyData = new ArrayList<>();
        int numWorkers = 4; // Número de trabajadores (hilos) para la producción
        int maxComponents = 1000; // Número total de componentes a producir
        int componentsPerWorker = maxComponents / numWorkers;

        List<Thread> workerThreads = new ArrayList<>();
        for (int i = 0; i < numWorkers; i++) {
            Worker workerRunnable = new Worker(componentsPerWorker, frequencyData, distributionPanel);
            Thread workerThread = new Thread(workerRunnable);
            workerThreads.add(workerThread);
        }

        // Iniciar los hilos de los trabajadores
        for (Thread workerThread : workerThreads) {
            workerThread.start();
        }

        // Esperar a que todos los hilos de los trabajadores terminen
        try {
            for (Thread workerThread : workerThreads) {
                workerThread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Todos los componentes han sido producidos");
    }
}



