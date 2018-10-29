package gui;

import java.util.Queue;

public class Container {

    private Queue<Integer> redQueue;
    private Queue<Integer> blueQueue;


    final int LIMIT = 4;

    public Container(Queue<Integer> redQueue, Queue<Integer> blueQueue) {
        this.redQueue = redQueue;
        this.blueQueue = blueQueue;
    }

}
