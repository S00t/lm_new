package soot.letsmeet.utils;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;

import timber.log.Timber;

/**
 * CameraUtil zarz¹dza tworzeniem Intent do zrobienia zdjêcia i
 * zapisania go w pamiêci wewnêtrznej aplikacji.
 * Pozwala równie¿ na usuwanie potencjalnie zdupliowanego zdjêcia utworzonego w Galerii
 **/
public class CameraUtil {
    private FileUtil mFileUtil;
    private File mPhotoFile;

    public CameraUtil(FileUtil fileUtil) {
        this.mFileUtil = fileUtil;
    }

    /**
     * Tworzy intent do otworzenia aparatu wraz z docelow¹ œcie¿k¹ zapisu w InternalStorage
     *
     * @param filename Docelowa nazwa zdjêcia zapisanego w pamiêci wewnêtrznej
     * @return intent pozwalaj¹cy przez startActivityForResult otworzyæ aparat
     */
    public Intent takePhoto(String filename) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        mPhotoFile = mFileUtil.createPhotoFile(filename + ".jpg");
        Timber.d(mPhotoFile != null ? "Photo file created: " + filename : "Error while creating file");
        if (mPhotoFile != null) {
            Uri photoURI = mFileUtil.createURI(mPhotoFile);
            takePictureIntent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            takePictureIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
        }
        return takePictureIntent;
    }

    public void deleteTakenPhoto() {
        if(mFileUtil.deleteFile(mPhotoFile))
            Timber.d("File " + mPhotoFile.getName() + " deleted due to cancelled Camera action");
    }
}