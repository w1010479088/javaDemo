package test;

import test.utils.LogUtil;

public class JavaMainTest {

    public static void main(String[] args) {
        LogUtil.log("大俊子在此!");
        new ThreadManager().start();
    }
}
