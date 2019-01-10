package test.util;

import java.util.ArrayList;
import java.util.List;

public class PublicTest {
//    public static void main(String[] args) {
//        Base base = new Base();
//        PublicTest test = new PublicTest();
//
//        //创建内部类对象
//        Base.Sub1 sub1 = base.new Sub1();
//        Base.Sub2 sub2 = base.new Sub2();
//
//        InheritSub1 inheritSub1 = test.new InheritSub1(base);
//    }

    //继承内部类的特殊语法, outer.super()
    public class InheritSub1 extends Base.Sub1 {

        InheritSub1(Base base) {
            base.super();
        }
    }
}

//一个java文件中的public最多只能一个,也可以没有,有一个的话名字必须与文件名相同,没有的话java文件名随意
class Base {

    Base() {
        LogUtil.log("Base");
    }

    //内部类持有外部类对象的引用
    class Sub1 extends Base {
        private Base base = new Base();

        Sub1() {
            LogUtil.log("Sub1");
        }

        //方法内部的class
        void methodClass() {

            class MethodOutter {

            }

            {
                class MethodInner {

                }
            }
        }
    }

    class Sub2 extends Base.Sub1 {
        Sub2() {
            LogUtil.log("Sub2");
        }
    }
}

class Egg2 {
    private Yolk y = new Yolk();

    Egg2() {
        LogUtil.log("new Egg2()");
    }

    void insertYolk(Yolk y) {
        this.y = y;
    }

    void g() {
        y.f();
    }

    protected class Yolk {

        Yolk() {
            LogUtil.log("Egg2.Yolk()");
        }

        public void f() {
            LogUtil.log("Egg2.Yolk.f()");
        }
    }
}

class BigEgg2 extends Egg2 {

    BigEgg2() {
        insertYolk(new Yolk());
    }

    public class Yolk extends Egg2.Yolk {
        Yolk() {
            LogUtil.log("BigEgg2.Yolk()");
        }

        public void f() {
            LogUtil.log("BigEgg2.Yolk.f()");
        }
    }
}

class Tester {
    private List<String> collection = new ArrayList<>();

    public Tester() {
        LogUtil.log(String.valueOf(collection.size()));
        LogUtil.log("Tester()");
    }

    public static void main(String[] args) {
        new Tester();
//        new BigEgg2().g();
    }
}