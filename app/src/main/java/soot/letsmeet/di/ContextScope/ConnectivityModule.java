package soot.letsmeet.di.ContextScope;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import soot.letsmeet.utils.ConnectivityUtil;

@Module
public class ConnectivityModule {
    private ConnectivityUtil connectivityUtil;

    public ConnectivityModule(Context context) {
        this.connectivityUtil = new ConnectivityUtil(context);
    }

    @Provides
    @ContextScope
    public ConnectivityUtil providesConnectivityUtil() {
        return connectivityUtil;
    }
}
