package com.kielrafal.producerconsumer2;

import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {

        Queue<Integer> redQueue = new LinkedList<>();
        Queue<Integer> blueQueue = new LinkedList<>();

        Container container = new Container(redQueue, blueQueue);

        Thread t1 = new Thread(new Producer(container, "red"));
        Thread t2 = new Thread(new Producer(container, "blue"));

        Thread t3 = new Thread(new Consumer(container));
        Thread t4 = new Thread(new Consumer(container));
        Thread t5 = new Thread(new Consumer(container));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
