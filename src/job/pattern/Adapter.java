package job.pattern;

class Adapter {
}

class OldClass {

    void a() {
    }

    void b() {

    }
}

interface NewClass {
    void c();

    void d();
}

class ClassAdapter extends OldClass implements NewClass {

    @Override
    public void c() {
        super.a();
    }

    @Override
    public void d() {
        super.b();
    }
}

abstract class InterfaceAdapter implements NewClass {
    @Override
    public void c() {

    }
}

class InterfaceAdapter2 extends InterfaceAdapter {

    @Override
    public void d() {

    }
}

class ObjAdapter implements NewClass {
    private OldClass old;

    public ObjAdapter(OldClass obj) {
        this.old = obj;
    }

    @Override
    public void c() {
        old.a();
    }

    @Override
    public void d() {
        old.b();
    }
}
