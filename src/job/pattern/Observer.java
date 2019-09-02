package job.pattern;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Observer {
}

interface IUpdate {
    void a();
}

class User implements IUpdate {

    public User(Target target) {
        target.add(this);
    }

    @Override
    public void a() {

    }
}

class Target {
    private ExecutorService mWorker = Executors.newSingleThreadExecutor();
    private List<IUpdate> mObservers = new LinkedList<>();

    private void update() {
        mWorker.execute(() -> {
            for (IUpdate observer : mObservers) {
                observer.a();
            }
        });
    }

    public void add(IUpdate observer) {
        if (!mObservers.contains(observer)) {
            mObservers.add(observer);
        }
    }

    public void remove(IUpdate observer) {
        if (mObservers.contains(observer)) {
            mObservers.remove(observer);
        }
    }

    public void onDestroy() {
        mObservers.clear();
        mWorker.shutdownNow();
    }
}
