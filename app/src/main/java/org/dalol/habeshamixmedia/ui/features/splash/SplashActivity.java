package org.dalol.habeshamixmedia.ui.features.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.dalol.habeshamixmedia.ui.features.MainActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
