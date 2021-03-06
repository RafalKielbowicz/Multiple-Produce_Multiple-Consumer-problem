public class Producer implements Runnable {

    private Container container;
    private String name;

    public Producer(Container container, String name) {
        this.container = container;
        this.name = name;
    }


    @Override
    public void run() {

        while(true){
            try {
                container.setValue(name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
