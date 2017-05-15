package soot.letsmeet.utils;

import android.util.Log;

import com.crashlytics.android.Crashlytics;

import timber.log.Timber;


/**
 * Created by ggla00 on 2017-05-15.
 */

class CrashlyticsTree extends Timber.Tree {

    @Override
    protected void log(int priority, String tag, String message, Throwable t) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG) {
            return;
        }
        if(t != null){
            Crashlytics.logException(t);
        }
    }
}
