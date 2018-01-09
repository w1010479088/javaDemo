package test.clone;

public class Organ implements Cloneable {
    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Organ() {
    }

    public Organ(String desc) {
        this.desc = desc;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
