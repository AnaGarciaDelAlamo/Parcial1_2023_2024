package org.example;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class CampanasDeGaussFactory {
    public static void main(String[] args) {
        int numWorkers = 4;
        int bufferSize = 100;

        // Búfer compartido para los componentes producidos
        BlockingQueue<Component> buffer = new ArrayBlockingQueue<>(bufferSize);

        // Crear instancias de Runnable para trabajadores y ensamblador
        Runnable[] runnables = new Runnable[numWorkers + 1];
        for (int i = 0; i < numWorkers; i++) {
            runnables[i] = new Worker("Worker " + i, buffer);
        }
        runnables[numWorkers] = new Assembler(buffer);


        Thread[] threads = new Thread[runnables.length];
        for (int i = 0; i < runnables.length; i++) {
            threads[i] = new Thread(runnables[i]);
            threads[i].start();
        }


        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Todos los hilos han terminado");
    }
}
