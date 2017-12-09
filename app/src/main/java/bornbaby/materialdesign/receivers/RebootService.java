package bornbaby.materialdesign.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by madhu on 28-Nov-17.
 */

public class RebootService extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent != null) {
            if (intent.getAction().equalsIgnoreCase(
                    Intent.ACTION_BOOT_COMPLETED)) {

                //Boot Receiver Called
            }
        }

    }
}
