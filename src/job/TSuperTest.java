package job;

import java.util.ArrayList;
import java.util.List;

public class TSuperTest {
    public void a() {
        A<SubA2> aer = new A<>();
        aer.z(new SubA2());

        B<? extends Base> ber = new B<>();
//        ber.a(new SubA());    //不能存东西
        Base b = ber.b();

        B<? super SubB> b2 = new B<>();
        b2.a(new SubB());
        Object b3 = b2.b();

        List<? extends Base> list1 = new ArrayList<>();
//        list1.add(new Base());    //报错
        Base base = list1.get(0);


        List<? super SubB2> list2 = new ArrayList<SubB>();
        Object object = list2.get(0);
//        list2.add(new Base());
    }
}

class Base {

    public void a() {
    }
}

class SubA extends Base {

}

class SubB extends Base {

}

class SubA2 extends SubA {

}

class SubB2 extends SubB {

}

class A<T extends SubA> {
    private T t;

    public void z(T t) {
        this.t = t;
        t.a();
    }
}

class B<T> {
    private T t;

    public void a(T t) {
        this.t = t;
    }

    public T b() {
        return t;
    }
}



