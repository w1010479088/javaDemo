package job;

/*
* 多线程访问update()
* 1.该写法什么问题?
* 2.有问题,请重构为自己认为正确的代码.
* */
public class HotRegion {
    private final int FILE_SIZE = 999;
    private int mCurLength;

    public void update(int length) {
        mCurLength += length;
        updateView();
    }

    private void updateView() {
        int progress = mCurLength * 100 / FILE_SIZE;
        //更新进度条...
    }
}
