package org.example;

import java.util.concurrent.BlockingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Worker implements Runnable {
    private String name;
    private BlockingQueue<Component> buffer;
    private int componentsToProduce;
    private static AtomicInteger uniqueIdCounter = new AtomicInteger(0);

    public Worker(String name, BlockingQueue<Component> buffer, int componentsToProduce) {
        this.name = name;
        this.buffer = buffer;
        this.componentsToProduce = componentsToProduce;
    }

    @Override
    public void run() {
        int producedComponents = 0; // Contador de componentes producidos por este trabajador

        while (producedComponents < componentsToProduce) {
            try {
                // Simular la producción de un componente
                Component component = new Component(generateUniqueId(), generateDescription());
                // Colocar el componente en el búfer compartido
                buffer.put(component);
                producedComponents++;
                // Realizar alguna simulación de trabajo
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private int generateUniqueId() {
        // Utiliza un contador atómico para generar IDs únicos
        return uniqueIdCounter.getAndIncrement();
    }

    private String generateDescription() {
        // Genera descripciones aleatorias para los componentes
        String[] descriptions = {"Component A", "Component B", "Component C", "Component D"};
        int randomIndex = (int) (Math.random() * descriptions.length);
        return descriptions[randomIndex];
    }
}

