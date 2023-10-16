package org.example;
import org.example.DistributionPanel;
import org.example.Worker;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class CampanasDeGaussFactory {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Campanas de Gauss Factory");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        BlockingQueue<Component> buffer = new ArrayBlockingQueue<>(100); // Búfer compartido

        DistributionPanel distributionPanel = new DistributionPanel();
        frame.add(distributionPanel);
        frame.setVisible(true);

        List<Integer> frequencyData = new ArrayList<>();

        List<Worker> workers = new ArrayList<>();

        // Crear e iniciar trabajadores
        for (int i = 0; i < 1000; i++) {
            int component = (int) (Math.random() * 10); // Valores entre 0 y 9
            frequencyData.add(component);

            // Colocar el componente en el búfer compartido
            try {
                buffer.put(new Component(component));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Actualizar el panel de distribución
            distributionPanel.setFrequencyData(frequencyData);
            distributionPanel.repaint();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Todos los componentes han sido producidos");
    }
}
