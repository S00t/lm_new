package soot.letsmeet.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

import retrofit2.Response;
import timber.log.Timber;

/**
 * Util do sprawdzania dostêpnoœci internetu
 */
public class ConnectivityUtil {
    private ConnectivityManager mConnectivityManager;

    public ConnectivityUtil(Context context) {
        mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    /**
     * Testuje dostêpnoœæ internetu
     * @return true/false
     */
    public boolean isNetworkAvailable() {
        NetworkInfo nInfo = mConnectivityManager.getActiveNetworkInfo();
        return nInfo != null && nInfo.isConnectedOrConnecting();
    }

    public static String getDeviceIPAddress() {
        try {
            List<NetworkInterface> networkInterfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface networkInterface : networkInterfaces) {
                List<InetAddress> inetAddresses = Collections.list(networkInterface.getInetAddresses());
                for (InetAddress inetAddress : inetAddresses) {
                    if (!inetAddress.isLoopbackAddress()) {
                        String sAddr = inetAddress.getHostAddress().toUpperCase();
                        boolean isIPv4 = inetAddress instanceof Inet4Address;
                        if (isIPv4)
                            return sAddr;
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static int getPort(Response response){
        int port = response.raw().request().url().port();
        Timber.d("Port: " + port);
        return port;
    }
}
