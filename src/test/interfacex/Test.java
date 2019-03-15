package test.interfacex;

import test.util.LogUtil;

public class Test {

    class Outer {
        Outer() {
        }

        class Inner {
        }
    }


    class OuterSub extends Outer {

        public class Inner extends Outer.Inner {
        }
    }
}

class Bread {
}

class Cheese {
}

class Lettuce {
}

class Meal {
}

class Lunch extends Meal {
}

class PortableLunch extends Lunch {
}

class Sandwich extends PortableLunch {
    Bread bread = new Bread();
    Cheese cheese = new Cheese();
    Lettuce lettuce = new Lettuce();
}

abstract class Shape {
    Shape() {
        LogUtil.log("before Shape()");
        draw();
        LogUtil.log("after Shape()");
    }

    abstract void draw();
}

class Circle extends Shape {
    private int index = 1;

    Circle() {
        LogUtil.log("Circle()");
        draw();
    }

    @Override
    void draw() {
        LogUtil.log("Circle->draw()->index=" + index);
    }

    public static void main(String[] args) {
        new Circle();
    }
}