public class Producer implements Runnable {

    Container container;
    private int valueNo;
    String name;

    public Producer(Container container, String name) {
        this.container = container;
        this.name = name;
    }


    @Override
    public void run() {

        while(true){
            try {
                container.setValue(valueNo++,name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
