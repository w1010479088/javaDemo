package test.interfacex;

import test.util.LogUtil;

public class InnerClass {
    public static void main(String[] args) {
//        new C();
//        LogUtil.divider();
//        new C();

//        new F();
//        LogUtil.divider();
//        new F();
        new InnerClass().a();
    }

    void a() {
        int x = 4, y = 5;
        int a = x + y > 8 ? 1 : 0;
        LogUtil.log("a = " + a);
    }

    void b() {
        int x = 1;
        int y = 2;

        outer:
        while (true) {
            x++;
            inner:
            while (true) {
                if (x > 10) {
                    break outer;
                } else {
                    y++;
                }
                if (y > 20) {
                    break inner;
                }
            }
        }
    }
}

class Outer {

    Inner inner() {
        return this.new Inner();
    }

    class Inner {
        Outer outer() {
            return Outer.this;
        }
    }
}

class InnerSub extends Outer.Inner {

    public InnerSub(Outer outer) {
        outer.super();
    }
}

class OuterSub extends Outer {

    public class InnerSub extends Outer.Inner {

    }
}

class A {
    private static Bean staticBean = new Bean("A static field");
    private Bean bean = new Bean("A field");

    A(String name) {
        Bean bean = new Bean("A constructor");
    }
}

class B extends A {
    private static Bean staticBean = new Bean("B static field");
    private Bean bean = new Bean("B field");

    B(String name, int age) {
        super("B");
        Bean bean = new Bean("B constructor");
    }

}

class C extends B {
    private static Bean staticBean = new Bean("C static field");
    private Bean bean = new Bean("C field");

    C() {
        super("C", 18);
        Bean bean = new Bean("C constructor");
    }
}

class Bean {
    Bean(String name) {
        LogUtil.log(name);
    }
}

abstract class E {
    protected int index;

    static {
        LogUtil.log("E static block");
    }


    E() {
        LogUtil.log("E constructor");
        e();
    }

    {
        LogUtil.log("Normal block");
    }

    private Bean bean = new Bean("bean init");

    abstract void e();
}

class F extends E {

    static {
        LogUtil.log("F static block");
    }

    F() {
        LogUtil.log("F constructor");
        index = 1;
        e();
    }

    @Override
    void e() {
        LogUtil.log("f.e() = " + index);
    }
}