package bornbaby.materialdesign.aynctask;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import bornbaby.materialdesign.R;
import bornbaby.materialdesign.activity.BaseActivity;

/**
 * Created by madhu on 09-Dec-17.
 */

public class CustomDialog {

    /*SHOW PROGRESS BAR*/
    public static Dialog showProgressDialog(BaseActivity baseActivity, boolean cancelable) {

        Dialog mDialog = new Dialog(baseActivity, R.style.pb_dialog_style);

        /* <style name="pb_dialog_style">
        <item name="android:windowFrame">@null</item>
        <item name="android:windowBackground">@android:color/transparent</item>
        <item name="android:windowIsFloating">true</item>
        <item name="android:windowContentOverlay">@null</item>
        <item name="android:windowTitleStyle">@null</item>
        <item name="android:windowAnimationStyle">@android:style/Animation.Dialog</item>
        <item name="android:windowSoftInputMode">stateUnspecified|adjustPan</item>
        <item name="android:backgroundDimEnabled">false</item>
        <item name="android:background">@android:color/transparent</item>
    </style>*/


        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        View layout = View.inflate(baseActivity, R.layout.progressbar_custom, null);
        mDialog.setContentView(layout);

        // progressbar_custom
      /*  <?xml version="1.0" encoding="utf-8"?>
        <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:background="@drawable/bg_loader"
        android:gravity="center_horizontal|center_vertical"
        android:orientation="vertical">

         <ProgressBar
        android:id="@android:id/progress"
        style="?android:attr/progressBarStyle"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center_horizontal" />

      <TextView
        android:id="@+id/tvProgressMessage"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:gravity="center"
        android:padding="3dp"
        android:textColor="@android:color/white" />

        </LinearLayout>*/




      /*<?xml version="1.0" encoding="utf-8"?>
    <shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">

     <solid android:color="#80111111" />

     <corners
        android:bottomLeftRadius="10dp"
        android:bottomRightRadius="10dp"
        android:topLeftRadius="10dp"
        android:topRightRadius="10dp" />

     <size
        android:width="100dp"
        android:height="100dp" />

     </shape>*/

        TextView tvProgressMessage = (TextView) layout.findViewById(R.id.tvProgressMessage);
        tvProgressMessage.setText("Loading Please Wait....");
        tvProgressMessage.setVisibility(View.VISIBLE);

     // Dialog  progressDialog= null;

        if (baseActivity.mDialog != null) {
            baseActivity.mDialog.dismiss();
            baseActivity.mDialog = null;
        }

        baseActivity.mDialog = mDialog;

        mDialog.setCancelable(cancelable);
        mDialog.setCanceledOnTouchOutside(cancelable);
        mDialog.show();

        return mDialog;
    }

    /*HIDE THE PROGRESS DIALOG*/
    public static void hideProgressBar(BaseActivity parent) {
        if (parent.mDialog != null)
            parent.mDialog.dismiss();
    }
}
