package soot.letsmeet;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.crashlytics.android.Crashlytics;
import com.facebook.stetho.Stetho;

import io.fabric.sdk.android.Fabric;
import soot.letsmeet.di.ApplicationComponent;
import soot.letsmeet.di.ApplicationModule;
import soot.letsmeet.di.DaggerApplicationComponent;
import soot.letsmeet.utils.AppLogger;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Soot on 03/05/2017.
 */

public class LetsMeetApplication extends MultiDexApplication {
    public ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initializeWithDefaults(this);
        AppLogger.init();
        Fabric.with(this, new Crashlytics());

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setFontAttrId(R.attr.fontPath)
                .setDefaultFontPath("fonts/Roboto-RobotoRegular.ttf")
                .build()
        );

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}