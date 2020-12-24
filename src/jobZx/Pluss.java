package jobZx;

import test.util.LogUtil;

public class Pluss {
    public static void main(String[] args) {
        int i = 1;
        i = i++;
        LogUtil.log(String.valueOf(i));

    }
}
