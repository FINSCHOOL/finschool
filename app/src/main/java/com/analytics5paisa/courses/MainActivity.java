package com.analytics5paisa.courses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.androidbrowserhelper.trusted.LauncherActivity;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Check if the device is rooted
        if (isDeviceRooted()) {
            // Show an error message or perform appropriate action
            Toast.makeText(this, "This application cannot be installed on a rooted device.", Toast.LENGTH_SHORT).show();
            finish(); // Close the application or perform any necessary actions
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.finimage);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        imageView.setAnimation(animation);

        Uri uri = Uri.parse("https://www.5paisa.com/finschool/login/");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final String PREFS_NAME = "MyPrefsFile";

                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

                if (settings.getBoolean("my_first_time", true)) {
                    settings.edit().putBoolean("my_first_time", false).commit();
                    startActivity(new Intent(MainActivity.this, WelcomeActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(MainActivity.this, LauncherActivity.class).setData(uri));
                    finish();
                }
            }
        }, 2200);
    }
    private boolean isDeviceRooted() {
        String buildTags = android.os.Build.TAGS;
        if (buildTags != null && buildTags.contains("test-keys")) {
            return true;
        }

        // Check for the existence of known root apps
        String[] knownRootApps = {
                "/system/app/Superuser.apk",
                "/sbin/su",
                "/system/bin/su",
                "/system/xbin/su",
                "/data/local/xbin/su",
                "/data/local/bin/su",
                "/system/sd/xbin/su",
                "/system/bin/failsafe/su",
                "/data/local/su"
        };
        for (String path : knownRootApps) {
            if (new File(path).exists()) {
                return true;
            }
        }

        // Check for the presence of the Superuser.apk package
        try {
            PackageManager packageManager = getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo("eu.chainfire.supersu", PackageManager.GET_ACTIVITIES);
            if (packageInfo != null) {
                return true;
            }
        } catch (PackageManager.NameNotFoundException e) {
            // Superuser package not found, not rooted
        }

        // Device is not rooted
        return false;
    }

}