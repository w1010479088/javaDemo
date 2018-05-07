package test.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {

    public static void execute() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {

        });
    }
}
