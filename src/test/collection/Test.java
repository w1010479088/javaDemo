package test.collection;

import test.util.LogUtil;

public class Test {
    public static void main(String[] args) {
        String toString = new Sub().toString();
        LogUtil.log(toString);
    }
}

class Base {
    @Override
    public String toString() {
        return "Base toString";
    }
}

class Sub extends Base {
    @Override
    public String toString() {
        return "Sub index:" + super.toString();
    }
}
