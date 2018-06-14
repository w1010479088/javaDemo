package test;

import test.clone.Identity;
import test.clone.Organ;
import test.clone.User;
import test.factory.ISender;
import test.factory.ISenderProvider;
import test.factory.SmsFactory;
import test.hourcoincide.CoincideForHourMinute;
import test.timer.TimerTestManager;
import test.utils.LogUtil;

import java.util.ArrayList;

public class JavaMainTest {

    public static void main(String[] args) {
        testMethod();
    }

    private static void testMethod() {
        LogUtil.log("------------> Java开始运行!");
//        new ThreadManager().start();
//        testInteger();
//        testTimerCounter();
//        testFactory();
//        testClone();
//        NetWorkTest.request();
//        OkHttpRequestor.testUploadFile();
//        EquipmentTest.test();
//        GameTest.test();
//        ClazzAdapter.test();
//        ObjectAdapter.test();
//        InterfaceAdapter.test();
//        Source.test();
//        RegexUtil.test();
//        BitSetTest.test();
//        CoincideForHourMinute.test();
        BaseTest.test();
        LogUtil.log("------------> Java运行结束!");
    }

    private static void testTimerCounter() {
        TimerTestManager.test();
    }

    private static void testInteger() {
        LogUtil.log("Integer最大值:" + Integer.MAX_VALUE);
        LogUtil.log("Integer最小值:" + Integer.MIN_VALUE);
    }

    private static void testFactory() {
        ISenderProvider provider = new SmsFactory();
        ISender sender = provider.provide();
        String sentMessage = sender.send();
        LogUtil.log("sentMessage:" + sentMessage);
    }

    private static void testClone() {
        User originUser = new User();
        Identity identity = new Identity();
        ArrayList<String> arguments = new ArrayList<>();
        ArrayList<Organ> organs = new ArrayList<>();
        originUser.setIdentity(identity);
        originUser.setArguments(arguments);
        originUser.setOrgans(organs);
        originUser.setName("大俊子");
        originUser.setAge(10);
        identity.setMessage("安徽");
        arguments.add("小黄人");
        arguments.add("大黄人");
        organs.add(new Organ("手臂"));
        organs.add(new Organ("脚"));

        try {
            User clonedUser = (User) originUser.clone();
            LogUtil.log("originUserHashCode:" + originUser.hashCode() + "---> IdentityHashCode:" + originUser.getIdentity().hashCode() + "---> List<Organ>:" + originUser.getOrgans().hashCode() + "---> List<String>:" + originUser.getArguments().hashCode());
            LogUtil.log("clonedUserHashCode:" + clonedUser.hashCode() + "---> IdentityHashCode:" + clonedUser.getIdentity().hashCode() + "---> List<Organ>:" + clonedUser.getOrgans().hashCode() + "---> List<String>:" + clonedUser.getArguments().hashCode());
        } catch (CloneNotSupportedException e) {
            LogUtil.log("CloneNotSupportedException:" + e.getMessage());
        }
    }
}
