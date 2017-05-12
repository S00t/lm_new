package soot.letsmeet.di.ContextScope.CameraScope;


import dagger.Subcomponent;
import soot.letsmeet.di.ContextScope.ContextScope;
import soot.letsmeet.di.ContextScope.FileServiceScope.FileModule;

@ContextScope
@Subcomponent(modules = {FileModule.class, CameraModule.class})
public interface CameraComponent {

}
