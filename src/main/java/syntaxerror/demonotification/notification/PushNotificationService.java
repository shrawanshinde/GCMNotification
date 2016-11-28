package syntaxerror.demonotification.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.google.android.gms.gcm.GcmListenerService;

import syntaxerror.demonotification.MainActivity;
import syntaxerror.demonotification.R;


/**
 * Created by kundan on 10/22/2015.
 */
public class PushNotificationService extends GcmListenerService {

    @Override
    public void onMessageReceived(String from, Bundle data) {
        String message = data.getString("message");
        String title=data.getString("title");
        showNotification(title,message);
    }
/*
    private void createNotification(String title,String text){
        try {
            Notification notification  = new Notification.Builder(this)
                    .setContentTitle(title)
                    .setContentText(text)
                    .setSmallIcon(R.drawable.ic_arrow)
                    .setAutoCancel(true)
                   .build();
            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.notify(1, notification );
        }catch (Exception e){
        }
    }*/

    public void showNotification(@NonNull String title, @NonNull String message){

        // define sound URI, the sound to be played when there's a notification
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        // intent triggered, you can add other intent for other actions
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

        // this is it, we'll build the notification!
        // in the addAction method, if you don't want any icon, just set the first param to 0
        Notification mNotification = new Notification.Builder(this)

                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_notifications_none_black_24dp)
                .setContentIntent(pIntent)
                .setSound(soundUri)
                .build();
        mNotification.flags |= Notification.FLAG_AUTO_CANCEL;
        mNotification.defaults |= Notification.DEFAULT_LIGHTS;
        mNotification.defaults |= Notification.DEFAULT_VIBRATE;
        mNotification.defaults |= Notification.DEFAULT_SOUND;

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // If you want to hide the notification after it was selected, do the code below
        // myNotification.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, mNotification);
    }
}
