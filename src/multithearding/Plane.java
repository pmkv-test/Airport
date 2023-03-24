package multithearding;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Plane implements Runnable{
    String numPlane;
    Semaphore semaphore;
    long numRunWay;

    public Plane(String number, Semaphore semaphore)  {
        this.numPlane = "A"+number;
        this.semaphore = semaphore;
    }

    public void run() {
        numRunWay = Thread.currentThread().getId();
        RunWay runWay = new RunWay(semaphore, numPlane, numRunWay);
        System.out.println("самолет " + numPlane + " выруливает на полосу " + numRunWay);
        runWay.lockRunWay();
        try {
            TimeUnit.SECONDS.sleep(1);//время взлета
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("самолет " + numPlane + " взлетел");
        runWay.releaseRunWay();
    }
}
