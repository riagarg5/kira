package com.practo.kira;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;


public class SplashScreen extends Activity
{
    Context context;
    SharedPreferences sp;
    private final int SPLASH_DISPLAY_LENGTH = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        sp = getSharedPreferences("User", context.MODE_PRIVATE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {

                if(sp.contains("email"))
                {
                    Log.d("here", sp.getString("email", null));
                    Intent intent=new Intent(SplashScreen.this,DoctorScheduleActivity.class);
                    SplashScreen.this.startActivity(intent);

                } else {
                    Log.d("hereeee", "thereee");
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    SplashScreen.this.startActivity(intent);
                }
                SplashScreen.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

}