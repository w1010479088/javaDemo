package job;

import test.util.LogUtil;

public class IPlusPlusTest {
    public static void main(String[] args) {
        int i = 1;
        i = i++;
        int j = i++;
        LogUtil.log("i = " + i);        //  i = 2
        int k = i + (++i * i++) + i;    //  11 = 2 + (3 * 3 ) + 4
        LogUtil.log("i = " + i);        //  i = 4
        LogUtil.log("j = " + j);        //  j = 1
        LogUtil.log("k = " + k);        //  k = 11

        int y = 1;
        int z = y + y++ + y;
        LogUtil.log("z = " + z);        // z = 1 + 1 + 2 = 4
    }
}
