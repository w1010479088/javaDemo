package test.compare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import test.util.LogUtil;

public class ComparatorTest {

    public static void main(String[] args) {
//        List<Person> peoples = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            peoples.add(new Person(i + 1));
//            peoples.add(new Person(i));
//        }
//        LogUtil.log(peoples.toString());
//        Collections.sort(peoples);
//        LogUtil.log(peoples.toString());

        List<Child> children = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            children.add(new Child(i + 1));
            children.add(new Child(i));
        }
        LogUtil.log(children.toString());
        Collections.sort(children, new Comparator<Child>() {
            @Override
            public int compare(Child first, Child second) {
                return first.id() - second.id();
            }
        });
        LogUtil.log(children.toString());
    }
}

class Person implements Comparable<Person> {
    private int id;

    Person(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(Person person) {
        return id - person.id;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}

class Child {
    private int id;

    Child(int id) {
        this.id = id;
    }

    public int id() {
        return id;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
