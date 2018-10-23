package com.kielrafal.producerconsumer2;

public class Consumer implements Runnable {

    Container container;

    public Consumer(Container container) {
        this.container = container;
    }

    @Override
    public void run() {
        while(true){
            try {

                container.getValue();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
