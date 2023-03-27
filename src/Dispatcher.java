import multithearding.Plane;
import multithearding.RunWay;

import java.util.concurrent.*;

public class Dispatcher {
    private static final int PLANE_COUNT = 10;
    private static final int RUNWAY_COUNT = 5;

    public static void main(String[] args) {
        ArrayBlockingQueue<RunWay> runWays = new ArrayBlockingQueue<>(RUNWAY_COUNT);
        for (int i = 1; i <= RUNWAY_COUNT; i++) {
            runWays.add(new RunWay(i));
        }

        ExecutorService planePool = Executors.newFixedThreadPool(PLANE_COUNT);// создаем pool самолетов
        for (int num = 1; num <= PLANE_COUNT; num++) {
            Plane plane = new Plane(String.valueOf(num), runWays);
            planePool.execute(plane);
        }

        planePool.shutdown();//задания закончились

        try {
            planePool.awaitTermination(15, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Все самолеты взлетели!");
    }
}