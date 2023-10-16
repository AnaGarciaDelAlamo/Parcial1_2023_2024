package org.example;

import java.util.concurrent.BlockingQueue;

class Worker implements Runnable {
    private String name;
    private BlockingQueue<Component> buffer;

    public Worker(String name, BlockingQueue<Component> buffer) {
        this.name = name;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // Simular la producción de un componente
                Component component = new Component();
                // Colocar el componente en el búfer compartido
                buffer.put(component);
                // Realizar alguna simulación de trabajo
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
