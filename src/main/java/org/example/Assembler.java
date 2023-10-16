package org.example;

import java.util.concurrent.BlockingQueue;

class Assembler implements Runnable {
    private BlockingQueue<Component> buffer;
    private boolean productionFinished = false;

    public Assembler(BlockingQueue<Component> buffer) {
        this.buffer = buffer;
    }

    public void setProductionFinished(boolean finished) {
        this.productionFinished = finished;
    }

    @Override
    public void run() {
        while (!productionFinished || !buffer.isEmpty()) {
            try {
                Component component = buffer.take();
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}