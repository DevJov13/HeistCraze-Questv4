package dev.joven.nosdk1.view;


import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.view.WindowManager;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import dev.joven.nosdk1.R;
import dev.joven.nosdk1.libs.NetworkLibrary;

public class SplashAct extends AppCompatActivity {
    private NetworkLibrary networkReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(1);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_splash);

        networkReceiver = new NetworkLibrary();

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            if (NetworkLibrary.isNetworkAvailable(this)) {
                VideoView videoView = findViewById(R.id.videoView);

                // Set the video path from the resources
                String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.load; // Replace with your video file name
                Uri uri = Uri.parse(videoPath);

                // Set the video URI to the VideoView
                videoView.setVideoURI(uri);

                // Set a completion listener to finish the activity when the video playback is complete
                videoView.setOnCompletionListener(mp -> {
                    // Add any additional code to navigate to the main activity or perform other actions
                    Intent intent = new Intent(SplashAct.this, PolicyAct.class);
                    startActivity(intent);
                    finish(); // Finish the current activity
                });

                // Start playing the video
                videoView.start();
            } else {
                Intent i = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                startActivity(i);
                finish();
            }
        }, 1800);

    }
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(networkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(networkReceiver);
    }
}