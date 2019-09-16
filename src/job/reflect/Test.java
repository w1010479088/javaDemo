package job.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import test.util.LogUtil;


class Test extends Sub {
    private String name;

    private Test(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        new Test("Ok").method();
    }

    private void clazz() {
        try {
            Test obj = new Test("Ok");
            Class clazz1 = Test.class;
            Class clazz2 = obj.getClass();
            Class clazz3 = Class.forName("job.reflect.Test");
            LogUtil.log(String.format("clazz1 = %s; clazz2 = %s; clazz3 = %s", clazz1.toString(), clazz2.getSimpleName(), clazz3.getCanonicalName()));
        } catch (Exception ex) {
            error(ex);
        }
    }

    private void superClazz() {
        Test obj = new Test("Ok");
        Class super1 = obj.getClass();
        while (true) {
            super1 = super1.getSuperclass();
            if (super1 == null) {
                break;
            }
            LogUtil.log(String.format("super1 = %s", super1));
        }
    }

    private void consts() {
        try {
            Class clazz = Class.forName("job.reflect.Test");
            Constructor[] conts = clazz.getDeclaredConstructors();
            for (Constructor cons : conts) {
                Class[] types = cons.getParameterTypes();
                for (Class item : types) {
                    LogUtil.log(String.format("Clazz = %s", item.getCanonicalName()));
                }
            }
        } catch (Exception ex) {
            error(ex);
        }
    }

    private void obj() {
        String str = "java.lang.String";
        try {
            Class clazz = Class.forName("job.reflect.Test");
            Constructor[] conts = clazz.getDeclaredConstructors();
            for (Constructor cons : conts) {
                Class[] types = cons.getParameterTypes();
                if (types.length == 1 && str.equals(types[0].getCanonicalName())) {
                    Test obj = (Test) cons.newInstance("Ok?");
                    LogUtil.log(obj.name);
                    break;
                }
            }
        } catch (Exception ex) {
            error(ex);
        }
    }

    private void readField() {
        String fieldName = "name";
        try {
            Field declaredField = this.getClass().getDeclaredField(fieldName);
            Object fieldResult = declaredField.get(this);
            LogUtil.log(fieldResult.toString());
        } catch (Exception ex) {
            error(ex);
        }
    }

    private void editField(String target) {
        try {
            Field field = this.getClass().getDeclaredField("name");
            field.setAccessible(true);
            field.set(this, target);
            readField();
        } catch (Exception ex) {
            error(ex);
        }
    }

    private void method() {
        try {
            Method method = this.getClass().getDeclaredMethod("editField", String.class);
            method.invoke(this, ")0000(");
        } catch (Exception ex) {
            error(ex);
        }
    }

    private void error(Exception error) {
        LogUtil.log("Error = " + error.getMessage());
    }
}

class Base {

}

class Sub extends Base {

}
