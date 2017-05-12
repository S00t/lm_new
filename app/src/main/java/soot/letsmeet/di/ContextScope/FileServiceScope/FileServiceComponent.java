package soot.letsmeet.di.ContextScope.FileServiceScope;

import dagger.Subcomponent;
import soot.letsmeet.di.ContextScope.ContextScope;

@ContextScope
@Subcomponent(modules = {NotificationModule.class, FileModule.class})
public interface FileServiceComponent {
   // void inject(DownloadService service);
}
