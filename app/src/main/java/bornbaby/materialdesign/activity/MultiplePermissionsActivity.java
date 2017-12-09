package bornbaby.materialdesign.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.karan.churi.PermissionManager.PermissionManager;

import java.util.ArrayList;

import bornbaby.materialdesign.R;
import bornbaby.materialdesign.Utils.Utility;

public class MultiplePermissionsActivity extends AppCompatActivity {

    private int PERMISSIONS_ALL;
    private Button btn_check;
    // int PERMISSIONS_ALL = 1;

    PermissionManager permissionManager;
    String[] permissions;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_permissions);


        permissionManager = new PermissionManager() {};
        permissionManager.checkAndRequestPermissions(this);


       /* permissions = new String[]{Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_COARSE_LOCATION
                , Manifest.permission.READ_EXTERNAL_STORAGE};

        if (!hasPermissions(this, permissions)) {
            ActivityCompat.requestPermissions(this, permissions, PERMISSIONS_ALL);
        }
*/

        inItUI();
    }

    private void inItUI() {


        btn_check = findViewById(R.id.btn_check);
        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAllPermissions();
            }
        });
    }

    private void getAllPermissions() {
        if (!hasPermissions(this, permissions)) {
            ActivityCompat.requestPermissions(this, permissions, PERMISSIONS_ALL);
        }


    }

    public static boolean hasPermissions(Context context, String... permissions) {

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M && context != null && permissions != null) {

            for (String permission : permissions) {

                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {

                    return false;

                }
            }

        }
        return true;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionManager.checkResult(requestCode, permissions, grantResults);

        ArrayList<String> granted = permissionManager.getStatus().get(0).granted;
        ArrayList<String> denied = permissionManager.getStatus().get(0).denied;


    }
}
