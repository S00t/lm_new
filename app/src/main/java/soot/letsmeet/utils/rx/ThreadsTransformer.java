package soot.letsmeet.utils.rx;

import android.os.Handler;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Klasa definiuj¹ca najczêœciej wykorzystywany Observer i Scheduler dla wywo³ywanych w programie akcji
 * @param <T> typ Observable
 */
public class ThreadsTransformer<T>{

    public static <T> Observable<T> newThreadToMainThread(Observable<T> tObservable) {
        return tObservable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread());
    }

    public static <T> Observable<T> newHandlerThread(Observable<T> tObservable, Handler handler) {
        return tObservable
                .observeOn(AndroidSchedulers.from(handler.getLooper()))
                .subscribeOn(AndroidSchedulers.from(handler.getLooper()));
    }

    public static <T> Observable<T> newHandlerToMainThread(Observable<T> tObservable, Handler handler) {
        return tObservable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.from(handler.getLooper()));
    }
}
