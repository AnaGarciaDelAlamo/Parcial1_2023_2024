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

        // Crear e iniciar trabajadores
        List<Worker> workers = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            int component = (int) (Math.random() * 10); // Valores entre 0 y 9
            frequencyData.add(component);

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



