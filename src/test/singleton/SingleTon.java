package test.singleton;

public class SingleTon {
    private static volatile SingleTon singleTon;

    private SingleTon() {
    }

    public static SingleTon getInstance() {
        if (singleTon == null) {
            synchronized (SingleTon.class) {
                if (singleTon == null) {
                    singleTon = new SingleTon();
                }
            }
        }
        return singleTon;
    }
}

class SingleTon2 {
    private SingleTon2() {

    }

    private static final class HOLER {
        private static final SingleTon2 instance = new SingleTon2();
    }

    public static SingleTon2 getInstance() {
        return HOLER.instance;
    }
}

enum SingleTon3 {

    INSTANCE(1, "大俊子");

    private int id;
    private String name;

    SingleTon3(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
