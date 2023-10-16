package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class CampanasDeGaussFactory {
    private static final int numWorkers = 4;
    private static final int bufferSize = 100;
    private static final int numBalls = 1000;

    public static void main(String[] args) {
        BlockingQueue<Component> buffer = new ArrayBlockingQueue<>(bufferSize);
        List<Component> componentList = new ArrayList<>(numBalls);

        JFrame frame = new JFrame("Campanas de Gauss Factory");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);

        for (int i = 0; i < numWorkers; i++) {
            Thread workerThread = new Thread(new Worker("Worker " + i, buffer, numBalls / numWorkers));
            workerThread.start();
        }

        Thread assemblerThread = new Thread(new Assembler(buffer));
        assemblerThread.start();

        frame.setSize(800, 600);
        frame.setVisible(true);

        while (componentList.size() < numBalls) {
            try {
                Component component = buffer.take();
                componentList.add(component);
                textArea.append("Componente producido: " + component + "\n");
                textArea.setCaretPosition(textArea.getDocument().getLength());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Todos los componentes han sido producidos");
    }
}



