package jobZx;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import test.util.LogUtil;

public class PatternTest {
    public static void main(String[] args) {
        new PatternTest().num();
    }

    private void location() {
        String LOCATION = "上海市徐汇区宜山路333号";
        parse(LOCATION, ".*?(?=\\号)");
    }

    private void charactor() {
        String CONTENT = "Youth,Hi,Today is Thursday,Lucy.";
        parse(CONTENT, "(\bHi\b.*\bLucy\b)");
    }

    private void num() {
        String CONTENT = "电话:010-27658900990,详情垂询!";
        parse(CONTENT, "0\\d{2}-\\d{8,10}");

    }

    private void parse(String content, String patternStr) {
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            LogUtil.log(matcher.group());
        }
    }
}
