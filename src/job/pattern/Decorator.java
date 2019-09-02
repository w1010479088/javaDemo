package job.pattern;

public class Decorator {
}

interface Shape {

    void draw();
}

class Circle implements Shape {

    @Override
    public void draw() {

    }
}

class Square implements Shape {

    @Override
    public void draw() {

    }
}

class ShapeDecorator implements Shape {
    private Shape shape;

    public ShapeDecorator(Shape shape) {
        this.shape = shape;
    }

    @Override
    public void draw() {
        shape.draw();
    }
}

class RedShapeDecorator extends ShapeDecorator {

    public RedShapeDecorator(Shape shape) {
        super(shape);
    }

    @Override
    public void draw() {
        super.draw();
        doOtherThing();
    }

    private void doOtherThing() {

    }
}
