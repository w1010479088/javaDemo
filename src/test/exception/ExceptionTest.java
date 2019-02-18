package test.exception;

import test.util.LogUtil;

public class ExceptionTest {
    public static void main(String[] args) throws Exception {
        try {
            throw new Exception1();
        } catch (Exception ex) {
            LogUtil.log(ex.getMessage());
        } finally {
            throw new Exception2();
        }
    }

    abstract class Base {
        abstract void f() throws Exception1, Exception2;
    }

    public class Sub extends Base {

        @Override
        void f() throws Exception1 {

        }
    }

    static class Exception1 extends Exception {
        @Override
        public String toString() {
            return "Exception1--->";
        }
    }

    static class Exception2 extends Exception {
        @Override
        public String toString() {
            return "Exception2--->";
        }
    }
}
