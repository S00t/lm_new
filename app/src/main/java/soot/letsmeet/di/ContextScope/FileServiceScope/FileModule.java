package soot.letsmeet.di.ContextScope.FileServiceScope;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import soot.letsmeet.di.ContextScope.ContextScope;
import soot.letsmeet.utils.FileUtil;

@Module
public class FileModule {
    private FileUtil mFileUtil;

    public FileModule(Context context) {
        mFileUtil = new FileUtil(context);
    }

    @Provides
    @ContextScope
    public FileUtil providesFileUtil() {
        return mFileUtil;
    }
}
