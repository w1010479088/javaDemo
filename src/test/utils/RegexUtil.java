package test.utils;

import java.util.regex.Pattern;

public class RegexUtil {

    public static void test() {
        LogUtil.log("RegexUtil:" + isMobile("+86717521006922"));
    }

    public static boolean isMobile(String mobile) {
        String regex = "(\\+\\d+)?1[34578]\\d{9}$";
        return Pattern.matches(regex, mobile);
    }
}
