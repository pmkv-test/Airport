import multithearding.Plane;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Dispatcher {
    private static final int PLANE_COUNT = 10;
    private static final int RUNWAY_COUNT = 5;

    public static void main(String[] args)  {
        Semaphore semaphore = new Semaphore(RUNWAY_COUNT);// 5 полос;
        ExecutorService executorService = Executors.newFixedThreadPool(RUNWAY_COUNT);// создаем pool по количеству полос

        for (int num = 0; num < PLANE_COUNT; num++) { //передаем в pool задания - самолеты ожидают очереди и если возможно взлетают
            Plane plane = new Plane(String.valueOf(num), semaphore);
            executorService.execute(plane);
        }

        executorService.shutdown();//задания закончились

        try {
            executorService.awaitTermination(15, TimeUnit.SECONDS);//ждем выполнения или когда истечет время
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Все самолеты взлетели!");
    }
}