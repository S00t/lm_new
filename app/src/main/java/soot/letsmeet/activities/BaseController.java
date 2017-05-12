package soot.letsmeet.activities;

/**
 * Created by Soot on 03/05/2017.
 */

public abstract class BaseController<T> {
    private T mView;

    public BaseController() {
    }

    public void onCreate(T mView) {
        this.mView = mView;
    }

    public void onDestroy() {
        this.mView = null;
    }

    public T getView() {
        return mView;
    }

    public boolean isViewPresent() {
        return mView != null;
    }
}