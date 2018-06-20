package test.yield;

public class Test {

    public static void main(String[] args) {
        test("test1--->");
        test("test2--->");
    }

    public static void test(String tag) {
        for (int i = 0; i < 20; i++) {
            System.out.println(tag + i);
            Thread.yield();
        }
    }
}
