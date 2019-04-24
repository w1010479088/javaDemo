package test.component;

import java.util.ArrayList;
import java.util.List;

import test.util.LogUtil;

public abstract class Component {
    public static void main(String[] args) {
        Component root = new Composite("root");

        Component leaf1 = new Leaf("Leaf-1");
        Component leaf2 = new Leaf("Leaf-2");

        Component dept = new Composite("dept-1");

        Component leaf3 = new Leaf("dept-1-leaf3");
        Component leaf4 = new Leaf("dept-1-leaf4");
        Component leaf5 = new Leaf("dept-1-leaf5");

        root.add(leaf1);
        root.add(leaf2);
        root.add(dept);

        dept.add(leaf3);
        dept.add(leaf4);
        dept.add(leaf5);

        root.display(1);
    }

    protected String name;

    public Component(String name) {
        this.name = name;
    }

    public abstract void add(Component component);

    public abstract void remove(Component component);

    public abstract void display(int deep);

    protected void log(int depth, String content) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append("--");
        }
        LogUtil.log(sb.toString() + content);
    }
}

class Leaf extends Component {

    public Leaf(String name) {
        super(name);
    }

    @Override
    public void add(Component component) {
        //ignore
    }

    @Override
    public void remove(Component component) {
        //ignore
    }

    @Override
    public void display(int deep) {
        log(deep, name);
    }
}

class Composite extends Component {
    private List<Component> composite = new ArrayList<>();

    public Composite(String name) {
        super(name);
    }

    @Override
    public void add(Component component) {
        if (!composite.contains(component)) {
            composite.add(component);
        }
    }

    @Override
    public void remove(Component component) {
        if (composite.contains(component)) {
            composite.remove(component);
        }
    }

    @Override
    public void display(int deep) {
        log(deep, name);
        for (Component com : composite) {
            com.display(deep + 2);
        }
    }
}