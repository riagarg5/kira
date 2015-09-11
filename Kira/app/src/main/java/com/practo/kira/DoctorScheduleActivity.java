package com.practo.kira;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class DoctorScheduleActivity extends AppCompatActivity {

    private ScheduleAdaptor mScheduleAdaptor;
    private ListView mListView;
    private Toolbar mToolBar;
    private SwipeDetector mSwipeDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_schedule);
        mListView =(ListView) findViewById(R.id.patient_card);
        mToolBar = (Toolbar) findViewById(R.id.toolbar_top);
        mScheduleAdaptor = new ScheduleAdaptor(getApplicationContext(),R.id.patient_card);
        mToolBar.setTitle("Schedule");
        setSupportActionBar(mToolBar);
      //  mToolBar.showOverflowMenu();
        patient_object abc1 = new patient_object();
        ArrayList<patient_object> abc = new ArrayList<patient_object>();
        abc.add(abc1);
        abc.add(abc1);

        mScheduleAdaptor.setData(abc,true);
        mListView.setAdapter(mScheduleAdaptor);
        mScheduleAdaptor.notifyDataSetChanged();
        mSwipeDetector = new SwipeDetector();

        mListView.setOnTouchListener(mSwipeDetector);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(mSwipeDetector.swipeDetected()) {
                    Toast.makeText(getApplicationContext(),"It was Swiped",Toast.LENGTH_SHORT).show();
                    mScheduleAdaptor.removedata(position);
                    mScheduleAdaptor.notifyDataSetChanged();
                }
            }
        });
    }








    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_doctor_schedule, menu);

        return true;
    }

    class ScheduleAdaptor extends ArrayAdapter<patient_object> {

        private LayoutInflater mInflater;
        private Context context;
        private ArrayList<patient_object> mValues = new ArrayList<patient_object>();

        public ScheduleAdaptor(Context context, int resource) {
            super(context, resource);
            this.context = context;
            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }


        public void setData(List<patient_object> data, boolean add) {
            if (!add) {
                clear();
                mValues.clear();
            }

            if (data != null){
                mValues.addAll(data);
                if (Utils.hasHoneycomb()) {
                    addAll(data);

                } else {
                    if (data != null) {
                        for (patient_object patient : data) {
                            add(patient);
                        }
                    }
                }
            }
        }

        public void removedata(int patient){
            remove(mValues.get(patient));
            mValues.remove(patient);
        }



        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView info_child_text;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.item_patient_card, parent, false);
            }


            patient_object facility = getItem(position);

            return convertView;
        }
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

        if (id == R.id.google_accounts) {

        }

        return super.onOptionsItemSelected(item);
    }
}
