package com.example.coroutinesdemo

import android.content.Context
import android.util.Log
import android.widget.Toast

/**
 * This Class is just to display log messages
 *
 * --to see the log under this particular Tag in Info
 * --Message you want to print.
 *
 * -- Adding support to null as all the this class is also used in the ${DeBug} class
 * which is not null safe compatible...
 *
 */
object GlobalLoggerUtils {

    private val toShowLog = BuildConfig.DEBUG

    @JvmStatic
    fun showLog(TAG: String?, message: String?) {
        try {
            if (toShowLog && TAG != null && message != null) {
                Log.i(TAG, message + " " + "[Thread: " + Thread.currentThread().name + "]")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @JvmStatic
    fun logError(TAG: String?, message: String?) {
        if (toShowLog && TAG != null && message != null) {
            Log.e(TAG, message + " " + "[Thread: " + Thread.currentThread().name + "]")
        }
    }

    @JvmStatic
    fun largeLog(tag: String, message: String?) {
        if (toShowLog && message != null) {
            try {
                if (message.length > 4000) {
                    showLog(tag, message.subSequence(0, 4000).toString())
                    largeLog(tag, message.subSequence(4000, message.length - 1).toString())
                } else {
                    showLog(tag, message)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    @JvmStatic
    fun showToast(context: Context, MSG: String?) {
        if (toShowLog && MSG != null) {
            Toast.makeText(context, MSG, Toast.LENGTH_SHORT).show()
        }
    }

    //    public static void showNotification(Context context, String MSG) {
    //        if (MSG != null && toShowLog) {
    //            String title = "Debug Notification";
    //
    // Use generic approach via application to get the class , no need to hardcode here...

    //            Intent i = new Intent(context, HomePageActivity.class);
    //            Bundle bundle = new Bundle();
    //            i.putExtras(bundle);
    //            PendingIntent pending = PendingIntent.getActivity(
    //                    context.getApplicationContext(), 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
    //
    //            NotificationManager NM = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    //            NotificationCompat.Builder builder =
    //                    new NotificationCompat.Builder(context, PRIMARY_NOTIF_CHANNEL);
    //            builder.setContentTitle(title);
    //            builder.setContentText(MSG);
    //            builder.setSmallIcon(CommonUtility.getNotificationIcon());
    //            builder.setStyle(new NotificationCompat.BigTextStyle().bigText(MSG));
    //            builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher));
    //            builder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE | Notification.DEFAULT_LIGHTS);
    //            builder.setContentIntent(pending);
    //            builder.build();
    //            builder.setChannelId("default");
    //            Notification notify = builder.getNotification();
    //            notify.sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
    //
    //            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
    //                NM.createNotificationChannel(CommonUtility.oreoNotification(context));
    //            }
    //            if (NM != null) {
    //                NM.notify(1, notify);
    //            }
    //        }
    //    }

}