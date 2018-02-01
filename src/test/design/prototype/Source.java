package test.design.prototype;

import test.utils.DeepCloneUtil;
import test.utils.LogUtil;

import java.io.IOException;
import java.io.Serializable;

public class Source implements Cloneable, Serializable {

    public static void test() {
        try {
            Source originalSource = new Source(7, new Child("Child"));
            LogUtil.log("originalSource ---> " + originalSource.hashCode());
            LogUtil.log("originalChild ---> " + originalSource.child.hashCode());
            LogUtil.log("originalSource ---> " + originalSource.id + "--->" + originalSource.child.name);

            Source shallowCloned = originalSource.clone();
            LogUtil.log("shallowSource ---> " + shallowCloned.hashCode());
            LogUtil.log("shallowChild ---> " + shallowCloned.child.hashCode());
            LogUtil.log("shallowSource ---> " + shallowCloned.id + "--->" + shallowCloned.child.name);

            Source deepCloned = originalSource.deepClone();
            LogUtil.log("deepSource ---> " + deepCloned.hashCode());
            LogUtil.log("deepChild ---> " + deepCloned.child.hashCode());
            LogUtil.log("deepSource ---> " + deepCloned.id + "--->" + deepCloned.child.name);

        } catch (Exception ex) {
            LogUtil.log("异常:" + ex.getMessage());
        }
    }

    private int id;
    private Child child;

    public Source(int id, Child child) {
        this.id = id;
        this.child = child;
    }

    @Override
    protected Source clone() throws CloneNotSupportedException {
        return (Source) super.clone();
    }

    public Source deepClone() throws IOException, ClassNotFoundException {
        DeepCloneUtil<Source> cloneUtil = DeepCloneUtil.newInstance();
        return cloneUtil.deepClone(this);
    }

    public static class Child implements Cloneable, Serializable {
        private String name;

        public Child(String name) {
            this.name = name;
        }

        @Override
        protected Child clone() throws CloneNotSupportedException {
            return (Child) super.clone();
        }
    }
}
