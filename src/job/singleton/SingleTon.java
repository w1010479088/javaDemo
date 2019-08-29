package job.singleton;

class Tester {
    public static void main(String[] args) {
        int a = SingleTon5.INSTANCE1.a();
    }
}

class SingleTon {
    private static final SingleTon INSTANCE = new SingleTon();

    private SingleTon() {
    }

    public static SingleTon getInstance() {
        return INSTANCE;
    }
}

class SingleTon1 {
    private static SingleTon1 instance;

    private SingleTon1() {
    }

    public static SingleTon1 getInstance() {
        if (instance == null) {
            instance = new SingleTon1();
        }
        return instance;
    }
}

class SingleTon2 {
    private static SingleTon2 instance;

    private SingleTon2() {

    }

    public static synchronized SingleTon2 getInstance() {
        if (instance == null) {
            instance = new SingleTon2();
        }
        return instance;
    }
}

class SingleTon3 {
    private static volatile SingleTon3 instance;

    private SingleTon3() {

    }

    public static SingleTon3 getInstance() {
        if (instance == null) {
            synchronized (SingleTon3.class) {
                if (instance == null) {
                    instance = new SingleTon3();
                }
            }
        }
        return instance;
    }
}

class SingleTon4 {
    private SingleTon4() {

    }

    private static class HOLDER {
        private static final SingleTon4 INSTANCE = new SingleTon4();
    }

    public static SingleTon4 getInstance() {
        return HOLDER.INSTANCE;
    }
}

enum SingleTon5 {
    INSTANCE1();

    private int a;
    private int b;

    SingleTon5() {
    }

    public void set(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int a() {
        return a;
    }

    public int b() {
        return b;
    }
}
