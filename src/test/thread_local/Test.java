package test.thread_local;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import test.util.LogUtil;

public class Test {

    public static void main(String[] args) {
        ExecutorService executors = Executors.newFixedThreadPool(3);
        Object obj = new Object();
        ThreadLocal<Object> objLocal = new ThreadLocal<>();
        objLocal.set(obj);
        for (int i = 0; i < 3; i++) {
            executors.execute(new Runer(objLocal));
        }
//        executors.shutdown();
    }

    private static class Runer implements Runnable {
        private ThreadLocal<Object> objLocal;

        Runer(ThreadLocal<Object> objLocal) {
            this.objLocal = objLocal;
        }

        @Override
        public void run() {
            log(String.valueOf(objLocal.get().hashCode()));
        }
    }

    private static void log(String content) {
        LogUtil.log(content);
    }
}


class TestConcurrent {

    void arrayList() {
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        CopyOnWriteArraySet copyOnWriteArraySet = new CopyOnWriteArraySet();

//        SortedMap sortedMap = new SortedMap();
//        SortedSet sortedSet = new SortedSet();

//        CompletionService.class
//        ThreadPoolExecutor
        Runtime.getRuntime().availableProcessors();
    }
}