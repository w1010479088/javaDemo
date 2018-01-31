package test.design.template;

import test.utils.LogUtil;

public class FootBall extends Game {
    @Override
    protected void prepare() {
        LogUtil.log("FootBall--->prepare");
    }

    @Override
    protected void startPlay() {
        LogUtil.log("FootBall--->startPlay");
    }

    @Override
    protected void end() {
        LogUtil.log("FootBall--->end");
    }
}
