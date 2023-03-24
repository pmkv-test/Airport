package multithearding;

import java.util.concurrent.Semaphore;

public class RunWay {
    String numPlane;
    long numRunWay;
    Semaphore semaphore;

    public RunWay(Semaphore semaphore, String numPlane, long numRunWay) {
        this.numPlane = numPlane;
        this.semaphore = semaphore;
        this.numRunWay = numRunWay;
    }

    public void lockRunWay(){
        try {
            semaphore.acquire(); // блокирует поток пока полоса не доступна
            System.out.println("Полоса "+numRunWay+" приняла самолет "+numPlane);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void releaseRunWay(){
        semaphore.release(); //освобождаем доступ
        System.out.println("Полоса "+numRunWay+" освободилась ");
    }
}
