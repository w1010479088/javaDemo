package test.util;

public class LogUtil {

    public static void log(String content) {
        System.out.println("------>" + content);
    }

    public static void log(int[] content) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < content.length; i++) {
            sb.append(String.valueOf(content[i]));
            if (i < content.length - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        LogUtil.log(sb.toString());
    }
}
