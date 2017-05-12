package soot.letsmeet.di;


import javax.inject.Singleton;

import dagger.Component;
import soot.letsmeet.LetsMeetApplication;
import soot.letsmeet.di.ContextScope.ConnectivityModule;
import soot.letsmeet.di.ContextScope.ContextComponent;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        EventBusModule.class,
        NetworkModule.class,
        RepositoryModule.class,
})
public interface ApplicationComponent {
    void inject(LetsMeetApplication letsMeetApplication);

    ContextComponent newContextComponent(ConnectivityModule connectivityModule);
}
