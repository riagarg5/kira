package com.practo.kira;



import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.os.Bundle;
import android.app.Activity;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.content.Context;
import android.content.SharedPreferences;


public class MainActivity extends AppCompatActivity
{
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

        submit = (Button)findViewById(R.id.button);
        email = (EditText)findViewById(R.id.editText2);
        password = (EditText)findViewById(R.id.editText);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_top);


        mToolbar.setTitle("Login");

        setSupportActionBar(mToolbar);

        e = email.getText().toString();
        p = password.getText().toString();

        if ( e.indexOf('@') > 0) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("email", e);
            editor.putString("password", p);
            editor.commit();
        }

        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DoctorScheduleActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_doctor_schedule, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}