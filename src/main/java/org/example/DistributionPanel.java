package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.List;


class DistributionPanel extends JPanel {
    private List<Integer> frequencyData;

    public void setFrequencyData(List<Integer> frequencyData) {
        this.frequencyData = frequencyData;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (frequencyData != null) {
            int barWidth = getWidth() / frequencyData.size();
            int maxCount = 10; // Valor máximo en este ejemplo

            for (int i = 0; i < frequencyData.size(); i++) {
                int count = frequencyData.get(i);
                int barHeight = (int) ((double) count / maxCount * getHeight());

                int x = i * barWidth;
                int y = getHeight() - barHeight;

                g.setColor(Color.blue);
                g.fillRect(x, y, barWidth, barHeight);
            }
        }
    }
}