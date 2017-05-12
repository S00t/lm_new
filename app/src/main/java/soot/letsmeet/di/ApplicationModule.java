package soot.letsmeet.di;

import android.app.AlarmManager;
import android.app.Application;
import android.content.Context;
import android.telephony.TelephonyManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Singleton
    @Provides
    public Application providesApplication() {
        return this.application;
    }

    @Singleton
    @Provides
    AlarmManager providesAlarmManager(Application application) {
        return (AlarmManager) application.getSystemService(Context.ALARM_SERVICE);
    }

    @Singleton
    @Provides
    TelephonyManager providesTelephonyManger(Application application) {
        return (TelephonyManager) application.getSystemService(Context.TELEPHONY_SERVICE);
    }


}
