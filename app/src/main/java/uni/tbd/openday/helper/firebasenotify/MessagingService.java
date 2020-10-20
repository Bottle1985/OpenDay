package uni.tbd.openday.helper.firebasenotify;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import uni.tbd.openday.R;
import uni.tbd.openday.chat.view.ChatActivity;
import uni.tbd.openday.utils.ImageUtils;


public class MessagingService extends FirebaseMessagingService {

    private static final String TAG = "MessagingService";

    @Override
    public void onMessageReceived(final RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d("mytg", "HERE");
        if (FirebaseAuth.getInstance().getCurrentUser().getUid().equals(remoteMessage.getData().get("owner_id"))) return;

        try {
            final Bitmap bitmap = Picasso.get().load(remoteMessage.getData().get("owner_photo_url")).placeholder(R.drawable.user_photo_holder).resize(ImageUtils.SIZE_M, ImageUtils.SIZE_M).get();
            new Handler(getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    final Notification notification = new NotificationCompat.Builder(getApplicationContext())
                            .setSmallIcon(R.drawable.user_photo_holder)
                            .setContentTitle(remoteMessage.getData().get("owner_nickname"))
                            .setContentText(remoteMessage.getData().get("content"))
                            .setSmallIcon(R.drawable.icon_message_notification)
                            .setLargeIcon(bitmap)
                            .setContentIntent(PendingIntent.getActivity(getApplicationContext(), 0, ChatActivity.getIntent(remoteMessage.getData().get("chat_id"), getApplicationContext()), 0))
                            .build();
                    notification.flags |= Notification.FLAG_AUTO_CANCEL;
                    ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).notify(0, notification);
                }
            });
        } catch (IOException e) {
            Log.d(TAG, "Error " + e.getMessage());
        }
    }
}
