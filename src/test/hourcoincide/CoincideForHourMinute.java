package test.hourcoincide;

import test.util.LogUtil;

public class CoincideForHourMinute {
    private static final int DAY_SEC = 24 * 60 * 60;
    private static final int HOUR_SEC = 60 * 60;
    private static final int MIN_SEC = 60;

    public static void test() {
        int coincideCount = 0;
        for (int n = 0; n < 100; n++) {
            float curSeconds = curSeconds(n);
            if (curSeconds >= 0 && curSeconds <= (DAY_SEC + 0.01f)) {
                coincideCount++;
                LogUtil.log("当前时间:" + curTimeFormat(curSeconds));
            } else {
                break;
            }
        }
        LogUtil.log("总共次数:" + coincideCount);
    }

    private static float curSeconds(int n) {
        return 1.000f * 3600 * 12 * n / 11;
    }

    private static String curTimeFormat(float time) {
        int hour = (int) (time / HOUR_SEC);
        float hourDel = (time % HOUR_SEC);
        int min = (int) (hourDel / MIN_SEC);
        float minDel = hourDel % MIN_SEC;
        return String.format("%02d:%02d:%f", hour, min, minDel);
    }
}
