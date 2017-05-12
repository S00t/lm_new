package soot.letsmeet.di;


import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class EventBusModule {

    @Provides
    @Singleton
    public EventBus providesEventBus() {
        return EventBus.getDefault();
    }
}
