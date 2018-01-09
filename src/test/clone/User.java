package test.clone;

import java.util.ArrayList;

public class User implements Cloneable {
    private String name;
    private int age;
    private Identity identity;
    private ArrayList<String> arguments;
    private ArrayList<Organ> organs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

    public ArrayList<String> getArguments() {
        return arguments;
    }

    public void setArguments(ArrayList<String> arguments) {
        this.arguments = arguments;
    }

    public ArrayList<Organ> getOrgans() {
        return organs;
    }

    public void setOrgans(ArrayList<Organ> organs) {
        this.organs = organs;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        User clonedUser = (User) super.clone();
        Identity clonedIdentity = (Identity) identity.clone();
        ArrayList<String> clonedArguments = (ArrayList<String>) arguments.clone();
        if (organs != null && !organs.isEmpty()) {
            ArrayList<Organ> clonedOrgans = new ArrayList<>();
            clonedUser.setOrgans(clonedOrgans);
            for (Organ organ : organs) {
                Organ clonedOrgan = (Organ) organ.clone();
                clonedOrgans.add(clonedOrgan);
            }
        }

//        ArrayList<Organ> clonedOrgans = (ArrayList<Organ>) organs.clone();
//        clonedUser.setOrgans(clonedOrgans);
        clonedUser.setIdentity(clonedIdentity);
        clonedUser.setArguments(clonedArguments);
        return clonedUser;
    }
}
