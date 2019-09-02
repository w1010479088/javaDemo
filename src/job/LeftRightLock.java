package job;

/*
*该写法有什么问题?
* */
public class LeftRightLock {

    public void transform(Object leftObj, Object rightObj) {
        synchronized (leftObj) {
            synchronized (rightObj) {
                edit(leftObj);
                edit(rightObj);
            }
        }
    }

    private void edit(Object obj) {
        //doOtherThing
    }
}
