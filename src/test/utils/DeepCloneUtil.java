package test.utils;

import java.io.*;

public class DeepCloneUtil<T> {

    private DeepCloneUtil() {
    }

    public static <T> DeepCloneUtil<T> newInstance() {
        return new DeepCloneUtil<>();
    }

    public T deepClone(T t) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(t);

        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return (T) ois.readObject();
    }
}
