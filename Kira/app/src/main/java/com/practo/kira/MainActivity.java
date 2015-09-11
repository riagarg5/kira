package com.practo.kira;



import android.view.View;
import android.os.Bundle;
import android.app.Activity;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.content.Context;
import android.content.SharedPreferences;


public class MainActivity extends Activity
{
    String e, p;
    Button submit;
    EditText email;
    Context context;
    EditText password;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        sp = getSharedPreferences("User", context.MODE_PRIVATE);

        submit = (Button)findViewById(R.id.button);
        email = (EditText)findViewById(R.id.editText2);
        password = (EditText)findViewById(R.id.editText);

        e = email.getText().toString();
        p = password.getText().toString();

        SharedPreferences.Editor editor = sp.edit();
        editor.putString("email", e);
        editor.putString("password", p);
        editor.commit();

        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DoctorScheduleActivity.class);
                startActivity(intent);
            }
        });
    }
}