package job.pattern;

import java.io.Serializable;

import test.util.LogUtil;

public class ProtoType implements Cloneable, Serializable {
    private Inner inner;
    private int index;

    private void init() {
        inner = new Inner();
    }

    private void log() {
        index++;
        LogUtil.log(String.format("hasCode=%2d  index=%2d inner=%2d", this.hashCode(), index, inner.hashCode()));
    }

    private void copyOther() throws CloneNotSupportedException {
        this.inner = inner.clone();
    }

    @Override
    protected ProtoType clone() throws CloneNotSupportedException {
        ProtoType cloned = (ProtoType) super.clone();
        cloned.copyOther();
        return cloned;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        ProtoType obj = new ProtoType();
        obj.init();
        obj.log();

        ProtoType newObj = obj.clone();
        newObj.log();
    }
}

class Inner implements Cloneable, Serializable {
    @Override
    protected Inner clone() throws CloneNotSupportedException {
        return (Inner) super.clone();
    }
}