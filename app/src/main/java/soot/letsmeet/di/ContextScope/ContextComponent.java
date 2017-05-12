package soot.letsmeet.di.ContextScope;


import dagger.Subcomponent;
import soot.letsmeet.activities.login.LoginActivity;
import soot.letsmeet.di.ContextScope.CameraScope.CameraComponent;
import soot.letsmeet.di.ContextScope.CameraScope.CameraModule;
import soot.letsmeet.di.ContextScope.FileServiceScope.FileModule;
import soot.letsmeet.di.ContextScope.FileServiceScope.FileServiceComponent;
import soot.letsmeet.di.ContextScope.FileServiceScope.NotificationModule;

@ContextScope
@Subcomponent(modules = {ConnectivityModule.class})
public interface ContextComponent {


    void inject(LoginActivity loginActivity);

//    void inject(OrganizerListDetailsFragment organizerListDetailsFragment);

//    void inject(SynchronizationService synchronizationService);

    FileServiceComponent newFileServiceComponent(FileModule fileModule, NotificationModule notificationModule);

    CameraComponent newCameraComponent(FileModule fileModule, CameraModule cameraModule);
}
