package soot.letsmeet.utils;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.service.notification.StatusBarNotification;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.app.NotificationCompat;

import com.annimon.stream.Stream;

/**
 * Util do tworzenia powiadomie�. Wykorzystywany m.in. przy pobieraniu plik�w w FileUtil
 */
public class NotificationUtil {
    private Context mContext;
    private NotificationManager mNotificationManager;

    public NotificationUtil(Context context) {
        mContext = context;
        mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    /**
     * Tworzy powiadomienie o wyst�pieniu b��du w trybie autocancel. U�ywane gdy nie jest mo�liwe wy�wietlenie Toast
     * @param contentTitle tytu� powiadomienia
     * @param notifID id do usuwania powiadomienia
     */

    public void createErrorNotification(String contentTitle, int notifID, boolean highPriority) {
        NotificationCompat.Builder builder = createNotification(contentTitle, "", android.R.drawable.ic_dialog_alert, true);
        if (highPriority) {
            builder.setPriority(NotificationCompat.PRIORITY_HIGH);
            builder.setVibrate(new long[]{});
        }
        sendNotification(notifID, builder);
    }

    /**
     * Tworzy powiadomienie o wyst�pieniu b��du w trybie autocancel. U�ywane gdy nie jest mo�liwe wy�wietlenie Toast
     * @param contentTitle tytu� powiadomienia
     * @param notifID id do usuwania powiadomienia
     */

    public void createErrorNotification(@StringRes int contentTitle, int notifID, boolean highPriority) {
        NotificationCompat.Builder builder = createNotification(mContext.getString(contentTitle), "", android.R.drawable.ic_dialog_alert, true);
        if (highPriority) {
            builder.setPriority(NotificationCompat.PRIORITY_HIGH);
            builder.setVibrate(new long[]{});
        }
        sendNotification(notifID, builder);
    }

    /**
     * Tworzy powiadomienie w trybie autocancel
     * @param contentTitle tytu� powiadomienia
     * @param icon ikona do powiadomienia
     * @param notifID id do usuwania powiadmienia
     */

    public void createNotification(String contentTitle, @DrawableRes int icon, int notifID) {
        sendNotification(notifID, createNotification(contentTitle, "", icon, true));
    }

    public void createNotification(@StringRes int contentTitle, @DrawableRes int icon, int notifID) {
        sendNotification(notifID, createNotification(mContext.getString(contentTitle), "", icon, true));
    }

    /**
     * Tworzy powiadmienie z mo�liwo�ci� ustawienia trybu autocancel
     * @param contentTitle tytu� powiadomienia
     * @param icon ikona do powiadomienia
     * @param notifID id do usuwania powiadmienia
     * @param autoCancel czy po odczytaniu ma znikn��
     */

    public void createNotification(String contentTitle, @DrawableRes int icon, int notifID, boolean autoCancel) {
        sendNotification(notifID, createNotification(contentTitle, "", icon, autoCancel));
    }

    public void createNotification(@StringRes int contentTitle, @DrawableRes int icon, int notifID, boolean autoCancel) {
        sendNotification(notifID, createNotification(mContext.getString(contentTitle), "", icon, autoCancel));
    }

    /**
     * Tworzy powiadmienie z mo�liwo�ci� ustawienia trybu autocancel i ustawianiem tre�ci powiadomienia
     * @param contentTitle tytu� powiadomienia
     * @param contentText tre�� powiadomienia
     * @param icon ikona powiadomienia
     * @param notifID id do usuwania powiadmienia
     */

    public void createNotification(String contentTitle, String contentText, @DrawableRes int icon, int notifID) {
        sendNotification(notifID, createNotification(contentTitle, contentText, icon, true));
    }

    public void createNotification(@StringRes int contentTitle, @StringRes int contentText, @DrawableRes int icon, int notifID) {
        sendNotification(notifID, createNotification(mContext.getString(contentTitle), mContext.getString(contentText), icon, true));
    }

    /**
     * Tworzy powiadomienie z akcj� do wykonania po jego wybraniu
     * @param intent Intent kt�ry zostanie wywo�any po wybraniu powiadomienia
     * @param contentTitle tytu� powiadomienia
     * @param contentText tre�� powiadomienia
     * @param icon ikona powiadomienia
     * @param notifID id do usuwania powiadmienia
     * @param autoCancel czy po odczytaniu ma znikn��
     */

    public void createNotificiationWithIntent(PendingIntent intent, String contentTitle, String contentText, @DrawableRes int icon, int notifID, boolean autoCancel) {
        NotificationCompat.Builder builder = createNotification(contentTitle, contentText, icon, autoCancel);
        builder.setPriority(NotificationCompat.PRIORITY_HIGH);
        builder.setVibrate(new long[]{});
        builder.setContentIntent(intent);
        sendNotification(notifID, builder);
    }

    public void createNotificiationWithIntent(PendingIntent intent, @StringRes int contentTitle, @StringRes int contentText, @DrawableRes int icon, int notifID, boolean autoCancel) {
        NotificationCompat.Builder builder = createNotification(mContext.getString(contentTitle), mContext.getString(contentText), icon, autoCancel);
        builder.setContentIntent(intent);
        sendNotification(notifID, builder);
    }

    /**
     * Builder powiadomienia
     * @param contentTitle tytu� powiadomienia
     * @param contentText tre�� powiadomienia
     * @param icon ikona powiadomienia
     * @param autoCancel czy po odczytaniu ma znikn��
     * @return builder powiadomienia
     */

    private NotificationCompat.Builder createNotification(String contentTitle, String contentText, @DrawableRes int icon, boolean autoCancel) {
        return new NotificationCompat.Builder(mContext)
                .setSmallIcon(icon)
                .setContentTitle(contentTitle)
                .setContentText(contentText)
                .setAutoCancel(autoCancel);
    }

    /**
     * Anuluje poprzednie powiadomienie, buduje nowe i wysy�a
     * @param notifID id powiadomienia do usuni�cia
     * @param builder builder powiadomienia
     */

    private void sendNotification(int notifID, NotificationCompat.Builder builder) {
        cancelNotification(notifID);
        mNotificationManager.notify(notifID, builder.build());
    }

    /**
     * Anuluje powiadomienia
     * @param notifID id powiadomienia do anulowania
     */
    @TargetApi(23)
    private void cancelNotification(int notifID) {
        if(mNotificationManager.getActiveNotifications() != null && Stream.of(mNotificationManager.getActiveNotifications()).map(StatusBarNotification::getId).anyMatch(id -> id == notifID)) {
            mNotificationManager.cancel(notifID);
        }
    }
}
