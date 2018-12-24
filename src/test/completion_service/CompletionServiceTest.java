package test.completion_service;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import test.util.LogUtil;

public class CompletionServiceTest {
    private static final int THREAD_POOL = 10;
    private static final int TASK_COUNT = 20;

    public static void main(String[] args) {
        new CompletionServiceTest().test();
    }

    public void test() {
        ExecutorCompletionService completionService = new ExecutorCompletionService(Executors.newFixedThreadPool(THREAD_POOL));
        for (int i = 0; i < TASK_COUNT; i++) {
            completionService.submit(new Task(i));
        }

        for (int i = 0; i < TASK_COUNT; i++) {
            try {
                Future future = completionService.take();
                if (future.isDone()) {
                    log(String.format("Task:%d任务完成:%s", i, future.get()));
                } else {
                    log(String.format("Task:%d还未完成!", i));
                }
            } catch (InterruptedException e) {
                log(e.getMessage());
            } catch (ExecutionException e) {
                log(e.getMessage());
            }
        }
    }

    private void log(String content) {
        LogUtil.log(content);
    }
}

class Task implements Callable<String> {
    private int index;

    Task(int index) {
        this.index = index;
    }

    @Override
    public String call() throws Exception {
        int sleepTime = new Random().nextInt(10 * 1000);
        Thread.sleep(sleepTime);
        return String.format("Task:%d不是最帅的!", index);
    }
}
