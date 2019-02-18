package test.lock;

public class DeadLock {
    private final Object leftLock = new Object();
    private final Object rightLock = new Object();

    public void leftRight() {
        synchronized (leftLock) {
            synchronized (rightLock) {
                // do something
            }
        }
    }

    public void rightLeft() {
        synchronized (rightLock) {
            synchronized (leftLock) {
                //do something
            }
        }
    }
}
