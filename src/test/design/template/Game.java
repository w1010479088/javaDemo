package test.design.template;

public abstract class Game {

    public final void play() {
        prepare();
        startPlay();
        end();
    }

    protected abstract void prepare();

    protected abstract void startPlay();

    protected abstract void end();
}
