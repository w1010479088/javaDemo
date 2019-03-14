package test.interfacex;

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

class Bread {}
class Cheese {}
class Lettuce {}

class Meal {}
class Lunch extends Meal {}
class PortableLunch extends Lunch {}

class Sandwich extends PortableLunch {
    Bread bread = new Bread();
    Cheese cheese = new Cheese();
    Lettuce lettuce = new Lettuce();
}