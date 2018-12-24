package test.design.template;

import test.util.LogUtil;

public class BasketBall extends Game {
    @Override
    protected void prepare() {
        LogUtil.log("BasketBall--->prepare");
    }

    @Override
    protected void startPlay() {
        LogUtil.log("BasketBall--->startPlay");
    }

    @Override
    protected void end() {
        LogUtil.log("BasketBall--->end");
    }
}
