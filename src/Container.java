import java.util.Queue;


public class Container {

    Queue<String> redQueue;
    Queue<String> blueQueue;

   // List<Queue> queues = Arrays.asList(redQueue,blueQueue);

    final int LIMIT = 4;

    public Container(Queue<String> redQueue, Queue<String> blueQueue){
        this.redQueue = redQueue;
        this.blueQueue = blueQueue;
    }


    public void setValue(String name) throws InterruptedException {

        if(name == "red")
        {
            synchronized (redQueue) {
                while (redQueue.size() == LIMIT) {
                    System.out.println(Thread.currentThread().getName()
                            + ": Zbyt dużo czerwonych wartości... oczekiwanie na pobranie");
                    redQueue.wait();
                }
            }
            synchronized (redQueue) {
                System.out.println(Thread.currentThread().getName()
                        + ": Nowa czerwona wartosc " + name);
                redQueue.add(name);
                Thread.sleep((long)(Math.random()*1000));
                redQueue.notify();
            }
        } else {
            synchronized (blueQueue) {
                while (blueQueue.size() == LIMIT) {
                    System.out.println(Thread.currentThread().getName()
                            + ": Zbyt dużo niebieskich wartości... oczekiwanie na pobranie");
                    blueQueue.wait();
                }
            }
            synchronized (blueQueue) {
                System.out.println(Thread.currentThread().getName()
                        + ": Nowa niebieska wartosc: " + name);
                blueQueue.add(name);
                Thread.sleep((long)(Math.random()*1000));
                blueQueue.notify();
            }

        }



    }

     public void getValue() throws InterruptedException {

         synchronized (redQueue) {

             while(redQueue.isEmpty()) {
                 System.out.println(Thread.currentThread().getName()
                         + ": Brak wartosci czerwonej... Oczekiwanie na dodanie");
                 redQueue.wait();
             }

             synchronized (blueQueue) {

                 while(blueQueue.isEmpty()) {
                     System.out.println(Thread.currentThread().getName()
                             + ": Brak wartosci niebieskiej... Oczekiwanie na dodanie");
                     blueQueue.wait();
                 }

                 Thread.sleep((long)Math.random()*2000);
                 System.out.println(Thread.currentThread().getName()
                         + ": Pobranie wartosci: niebieski[" + blueQueue.poll() + "], czerwony:[" +  redQueue.poll() + "]");
                 blueQueue.notify();
             }
             redQueue.notify();
         }



    }
}
