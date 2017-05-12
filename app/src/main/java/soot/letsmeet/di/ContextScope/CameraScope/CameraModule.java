package soot.letsmeet.di.ContextScope.CameraScope;


import dagger.Module;
import dagger.Provides;
import soot.letsmeet.di.ContextScope.ContextScope;
import soot.letsmeet.utils.CameraUtil;
import soot.letsmeet.utils.FileUtil;

@Module
public class CameraModule {
    private CameraUtil mCameraUtil;

    public CameraModule(FileUtil fileUtil){
        mCameraUtil = new CameraUtil(fileUtil);
    }

    @Provides
    @ContextScope
    public CameraUtil providesCameraUtil(){
        return mCameraUtil;
    }
}
