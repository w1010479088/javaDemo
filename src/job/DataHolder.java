package job;

import java.util.ArrayList;
import java.util.List;

/*
* 重构该类.
* */
public class DataHolder {
    public static void main(String[] args){

    }

    private List<Person> mDatas = new ArrayList<>();

    public void add(List<Person> data) {
        mDatas.addAll(data);
    }

    public Person get(int id) {
        for (Person person : mDatas) {
            if (person.id() == id) {
                return person;
            }
        }
        return null;
    }

    public void remove(int id) {
        for (Person person : mDatas) {
            if (person.id() == id) {
                mDatas.remove(person);
            }
        }
    }
}

class Person {
    private int id;

    int id() {
        return id;
    }
}
