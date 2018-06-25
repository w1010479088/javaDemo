package test.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class SemaphoreTest2 {

    public static void main(String[] args) {
        Manager manager = new Manager();
        Thread[] threads = new Thread[100];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new User(manager, i));
        }

        for (Thread thread : threads) {
            thread.start();
        }
    }

    private static class Manager {
        private boolean[] resources;
        private final Semaphore semaphore;
        private ReentrantLock lock;

        Manager() {
            resources = new boolean[10];
            semaphore = new Semaphore(resources.length, true);
            lock = new ReentrantLock(true);
            for (int i = 0; i < resources.length; i++) {
                resources[i] = true;
            }
        }

        void dispatch(int userId) {
            try {
                log(String.format("userId为%d的正在准备...", userId));
                semaphore.acquire();
                int resourceId = getResourceId();
                log(String.format("userId为%d正在使用%d号资源...", userId, resourceId));
                Thread.sleep(500);
                log(String.format("userId为%d的使用%d号资源完毕!", userId, resourceId));
                resources[resourceId] = true;
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private int getResourceId() {
            int id = -1;
            lock.lock();
            for (int i = 0; i < resources.length; i++) {
                if (resources[i]) {
                    id = i;
                    resources[i] = false;
                    break;
                }
            }
            lock.unlock();
            return id;
        }
    }

    private static class User implements Runnable {
        private int userId;
        private Manager manager;

        User(Manager manager, int userId) {
            this.manager = manager;
            this.userId = userId;
        }

        @Override
        public void run() {
            manager.dispatch(userId);
        }
    }

    private static void log(String content) {
        System.out.println(content);
    }
}
