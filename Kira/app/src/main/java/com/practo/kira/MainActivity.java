package com.practo.kira;


import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.content.Context;
import android.content.SharedPreferences;


public class MainActivity extends AppCompatActivity {
    String e, p;
    Button submit;
    EditText email;
    Context context;
    EditText password;
    SharedPreferences sp;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        sp = getSharedPreferences("User", context.MODE_PRIVATE);

        submit = (Button) findViewById(R.id.button);
        email = (EditText) findViewById(R.id.editText2);
        password = (EditText) findViewById(R.id.editText);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_top);


        mToolbar.setTitle("Login");

        setSupportActionBar(mToolbar);

        submit.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                e = email.getText().toString();
                p = password.getText().toString();

                Log.d("email", e);
                Log.d("password", p);
                if (e.indexOf('@') > 0) {
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("email", e);
                    editor.putString("password", p);
                    editor.commit();
                }

                Intent intent = new Intent(MainActivity.this, DoctorScheduleActivity.class);
                startActivity(intent);
            }
        });

    }
}