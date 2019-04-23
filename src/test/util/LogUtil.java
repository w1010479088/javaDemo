package test.util;

public class LogUtil {

    public static void log(String content) {
        System.out.println("------>" + content);
    }

    public static void log(int[] content) {
        StringBuilder sb = new StringBuilder();
        for (int item : content) {
            sb.append(String.valueOf(item));
            sb.append("\n");
        }
        LogUtil.log(sb.toString());
    }
}
