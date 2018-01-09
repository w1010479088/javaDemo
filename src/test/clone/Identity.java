package test.clone;

public class Identity implements Cloneable {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
