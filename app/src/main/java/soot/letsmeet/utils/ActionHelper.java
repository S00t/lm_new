package soot.letsmeet.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;

import java.util.List;

import timber.log.Timber;


public class ActionHelper {
    private static final int MAIL_REQUEST_CODE = 9876;
    private static final int PHONE_REQUEST_CODE = 47852;


    public static void call(Context context, String nrTel){
        if (nrTel != null && !nrTel.trim().equals("")) {
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:" + nrTel));
            callIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            List<ResolveInfo> resInfoList = context.getPackageManager().queryIntentActivities(callIntent, PackageManager.MATCH_DEFAULT_ONLY);
            if(resInfoList == null || resInfoList.isEmpty()){
                Timber.e("Brak aplikacji osblugujacej akcje ACTION_DIAL");
            }
            ((Activity) context).startActivityForResult(Intent.createChooser(callIntent, "Wybierz numer..."), PHONE_REQUEST_CODE);
        }
    }


    public static void sendMail(Context context, String email){
        if (email != null && !email.trim().equals("")) {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", email.trim(), null));
            emailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            List<ResolveInfo> resInfoList = context.getPackageManager().queryIntentActivities(emailIntent, PackageManager.MATCH_DEFAULT_ONLY);
            if(resInfoList == null || resInfoList.isEmpty()){
                Timber.e("Brak aplikacji osblugujacej akcje ACTION_SENDTO");
            }
            ((Activity) context).startActivityForResult(Intent.createChooser(emailIntent, "Wyœlij email..."), MAIL_REQUEST_CODE);
        }
    }

//    /**
//     * Metoda do rozpoczêcia nawigacji do wskazanego adresu
//     * @param context kontekst do utworzenia Intentu
//     * @param adres adres docelowy
//     * @param isNetworkAvaible informacja czy internet jest dostêpny
//     */
//    public static void nawigujZGoogle(final Context context, AdresKredytobiorcy adres, boolean isNetworkAvaible) {
//        if (isNetworkAvaible) {
//            if (adres != null) {
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q=" + (emptyIfNull(adres.getKodPocztowy()) + "+" + emptyIfNull(adres.getMiejscowosc()) + "," + emptyIfNull(adres.getUlica()) + "+" + emptyIfNull(adres.getNumer())).replace(" ","+")));
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                List<ResolveInfo> resInfoList = context.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
//                if(resInfoList == null || resInfoList.isEmpty()){
//                    Timber.e("Brak aplikacji osblugujacej akcje nawigacji Map Google");
//                }
//                context.startActivity(intent);
//            } else {
//                Toast.makeText(context, R.string.error_adres_null, Toast.LENGTH_SHORT).show();
//            }
//        } else {
//            Toast.makeText(context, R.string.errorNoNetwork, Toast.LENGTH_SHORT).show();
//        }
//    }


    private static String emptyIfNull(String tekst){
        return tekst != null ? tekst : "";
    }
}
