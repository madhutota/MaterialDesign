package bornbaby.materialdesign.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by madhu on 28-Nov-17.
 */

public class PhoneReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle extras = intent.getExtras();
        if (extras != null) {


            String state = extras.getString(TelephonyManager.EXTRA_STATE);
            Log.w("MY_DEBUG_TAG", state);
            if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                String phoneNumber = extras
                        .getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
                Log.w("MY_DEBUG_TAG", phoneNumber);

                Toast.makeText(context, ""+phoneNumber, Toast.LENGTH_SHORT).show();

                if (phoneNumber.contains("48919")) {
                    Intent rebootIntent = new Intent(context, RebootService.class);
                    context.startService(rebootIntent);

                }
            }
        }

    }
}
