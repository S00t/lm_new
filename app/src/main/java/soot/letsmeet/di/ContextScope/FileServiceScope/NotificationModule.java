package soot.letsmeet.di.ContextScope.FileServiceScope;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import soot.letsmeet.utils.NotificationUtil;

@Module
public class NotificationModule {
    private NotificationUtil mNotificationUtil;

    public NotificationModule(Context context) {
        this.mNotificationUtil = new NotificationUtil(context);
    }

    @Provides
    public NotificationUtil providesNotificationUtil() {
        return mNotificationUtil;
    }
}
