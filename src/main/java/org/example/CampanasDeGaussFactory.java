package org.example;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class CampanasDeGaussFactory {
    public static void main(String[] args) {
        int numWorkers = 4;
        int bufferSize = 10;
        int maxComponents = 10;
        int componentsPerWorker = maxComponents / numWorkers;

        // Búfer compartido para los componentes producidos
        BlockingQueue<Component> buffer = new ArrayBlockingQueue<>(bufferSize);

        // Crear instancias de Runnable para trabajadores y ensamblador
        Runnable[] runnables = new Runnable[numWorkers + 1];
        for (int i = 0; i < numWorkers; i++) {
            runnables[i] = new Worker("Worker " + i, buffer, componentsPerWorker);
        }
        runnables[numWorkers] = new Assembler(buffer);

        Thread[] threads = new Thread[runnables.length];
        for (int i = 0; i < runnables.length; i++) {
            threads[i] = new Thread(runnables[i]);
            threads[i].start();
        }

        // Imprimir los componentes a medida que se producen
        int componentsProduced = 0;
        while (componentsProduced < maxComponents) {
            try {
                Component component = buffer.take();
                System.out.println("Componente producido: " + component);
                componentsProduced++;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Finalizar los hilos
        for (Thread thread : threads) {
            thread.interrupt();
        }

        System.out.println("Todos los componentes han sido producidos");
    }
}

