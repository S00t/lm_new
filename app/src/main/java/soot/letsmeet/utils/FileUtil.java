package soot.letsmeet.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v4.content.FileProvider;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import okhttp3.ResponseBody;
import timber.log.Timber;

/**
 * FileUtil zarz�dza zapisem plik�w do InternalStorage, do prywatnego katalogu aplikacji.
 * Pozwala na usuwanie zgromadzonych tam plik�w(w tym zdj��)
 * Zarz�dza tworzeniem URI do zapisanych plik�w
 * oraz przygotowuje Intent do otworzenia pliku z InternalStorage
 */
public class FileUtil {
    private Context mContext;
    private static final String sAuthority = "soot.letsmeet.fileprovider";
    private static File sDocPath;
    private static File sPhotosPath;

    public FileUtil(Context context) {
        this.mContext = context;
        sDocPath = mContext.getFilesDir();
        sPhotosPath = new File(sDocPath, "photos"); //�cie�ka zdefiniowana w xml/file_paths.xml
    }

    /**
     * Zapisuje plik w InternalStorage
     *
     * @param filename nazwa zapisywanego pliku
     * @param body     zawarto�� pliku do zapisania
     * @return zapisany plik
     */
    public File saveFile(String filename, ResponseBody body) {
        File file = new File(sDocPath, filename);
        try {
            FileOutputStream fos = null;
            BufferedInputStream bis = null;
            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                fos = mContext.openFileOutput(filename, Context.MODE_PRIVATE);
                bis = new BufferedInputStream(body.byteStream());

                while (true) {
                    int read = bis.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    fos.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                    Timber.d("file download: " + fileSizeDownloaded + " of " + fileSize);
                }

                fos.flush();

                return file;
            } catch (IOException e) {
                e.printStackTrace();
                Timber.e(e);
                return null;
            } finally {
                if (bis != null) {
                    bis.close();
                }

                if (fos != null) {
                    fos.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Tworzy plik na zdj�cie. Tak przygotowany plik potrzebny
     * jest do wskazania aparatowi �cie�ki zapisu zdj�cia
     *
     * @param filename nazwa zdj�cia
     * @return plik w kt�rym znajdzie si� zdj�cie
     */
    File createPhotoFile(String filename) {
        if (!sPhotosPath.exists()) {
            if (!sPhotosPath.mkdirs()) {
                Timber.e(new Exception("B��d tworzenia katalogu"));
            }
        }
        return new File(sPhotosPath, filename);
    }

    /**
     * Kasuje zar�wno pobrane pliki jak i wykonane zdj�cia
     */
    public void deleteAllFiles() {
        deleteDownloadedFiles();
        deleteAllPhotos();
    }

    /**
     * Kasuje dokumenty pobrane i zapisane w InternalStorage
     */
    public void deleteDownloadedFiles() {
        if (sDocPath.listFiles() == null || sDocPath.listFiles().length == 0) {
            return;
        }

        for (File file : sDocPath.listFiles()) {
            deleteFile(file);
        }
    }

    /**
     * Kasuje wszystkie zdj�cia wykonane za pomoc� aplikacji zapisane w InternalStorage
     */
    public void deleteAllPhotos() {
        if (sPhotosPath.listFiles() == null || sPhotosPath.listFiles().length == 0) {
            return;
        }

        for (File photo : sPhotosPath.listFiles()) {
            deleteFile(photo);
        }
    }

    /**
     * Zrwaca wszystkie zdj�cia zapisane w aplikacji
     */

    public List<File> getAllPhotos() {
        return (sPhotosPath != null && sPhotosPath.listFiles() != null) ? Arrays.asList(sPhotosPath.listFiles()) : Collections.emptyList();
    }

    public void getAllPhotosNames() {
        Timber.d("Before listing files");
        if (sPhotosPath != null && sPhotosPath.listFiles() != null) {
            for (File photo : sPhotosPath.listFiles()) {
                Timber.d("Saved photo: " + photo.getName());
            }
        }
    }

    /**
     * Usuwa wskazany plik
     *
     * @param file plik do usuni�cia
     * @return powodzenie/pora�ka
     */
    public boolean deleteFile(File file) {
        return file.delete();
    }

    /**
     * Pozwala na odnalezienie pliku w InternalStorage po nazwie i typie(dokument/zdj�cie)
     *
     * @param filename nazwa pliku
     * @param isPhoto  zdj�cie - true, inne - false
     * @return plik
     */
    private File getFile(String filename, boolean isPhoto) {
        return isPhoto ? new File(sPhotosPath, filename) : new File(sDocPath, filename);
    }

    /**
     * Tworzy URI dla wskazanego pliku
     *
     * @param file Plik dla kt�rego utworzy� URI
     * @return URI do pliku
     */
    Uri createURI(File file) {
        return FileProvider.getUriForFile(mContext, sAuthority, file);
    }

    /**
     * Przygotowuje Intent do otworzenia pliku w zewn�trznej aplikacji za pomoc� URI
     *
     * @param file Plik do otworzenia
     * @return Intent do uruchomienia przez startActivity()
     */
    public Intent openFileByIntent(File file) {
        Uri contentUri = createURI(file);
        Timber.d("Uri: " + contentUri);
        Intent openFileIntent = new Intent(Intent.ACTION_VIEW, contentUri);
        openFileIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        openFileIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        //Na urz�dzeniu docelowym r�wnie� konieczne przyznawanie uprawnie� per aplikacja
        List<ResolveInfo> resInfoList = mContext.getPackageManager().queryIntentActivities(openFileIntent, PackageManager.MATCH_DEFAULT_ONLY);
        if (resInfoList != null && !resInfoList.isEmpty()) {
            for (ResolveInfo resolveInfo : resInfoList) {
                String packageName = resolveInfo.activityInfo.packageName;
                mContext.grantUriPermission(packageName, contentUri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
            }
        } else {
            openFileIntent = null;
        }

        return openFileIntent;
    }
}
