package multithearding;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Plane implements Runnable{
    private String numPlane;
    private long numRunWay;
    private ArrayBlockingQueue runWays;
    private RunWay runway;

    public Plane(String numPlane, ArrayBlockingQueue runWays)  {
        this.numPlane = "A"+numPlane;
        this.runWays = runWays;
    }

    @Override
    public void run() {
        numRunWay = Thread.currentThread().getId();
        try {
            runway = (RunWay) runWays.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(" самолет " + numPlane + " выруливает на полосу " + runway.getName() );
        System.out.println("Полоса "+runway.getName() +" приняла самолет "+numPlane);

        try {
            TimeUnit.SECONDS.sleep(1);//время взлета
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        runway.counter();
        System.out.println(" самолет " + numPlane + " взлетел с полосы "+runway.getName());
        System.out.println("Полоса "+runway.getName()+" освободилась");
        runWays.add(runway);
    }
}
