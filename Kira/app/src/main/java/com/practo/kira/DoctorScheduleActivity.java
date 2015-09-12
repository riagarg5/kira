package com.practo.kira;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.JsonReader;
import android.util.Log;
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

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        Schedule.fields abc1 = new Schedule.fields();
        ArrayList<Schedule.fields> abc = new ArrayList<Schedule.fields>();
        abc.add(abc1);
        abc.add(abc1);

        mScheduleAdaptor.setData(abc, true);
        mListView.setAdapter(mScheduleAdaptor);
        mScheduleAdaptor.notifyDataSetChanged();
        mSwipeDetector = new SwipeDetector();
        String url="http://192.168.1.4:8000/command/?command=today";

        CustomRequest request = new CustomRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject patient_object) {
                JSONArray data = new JSONArray();

                Log.v("SomeResponse",patient_object.toString());
                try {
                   data =  patient_object.getJSONArray("data");
                }catch(JSONException e) {
                    Log.v("error", "error");
                }
                int i;
                JSONObject fields1 = new JSONObject();
                for (i=0; i< data.length(); i++) {
                    try {
                        fields1 = data.getJSONObject(i);
                        JSONObject fields = fields1.getJSONObject("fields");
                        Schedule.fields obj = new Schedule.fields();
                        obj.patient = fields.getString("patient");
                        obj.cancelled = fields.getBoolean("cancelled");
                        obj.cancelled_reason = fields.getString("cancelled_reason");
                        obj.has_photo = fields.getBoolean("has_photo");
                        obj.doctor = fields.getInt("doctor");
                        obj.photourl = fields.getString("photo_url");
                        obj.scheduled_at = fields.getString("scheduled_at");
                        obj.scheduled_till = fields.getString("scheduled_till");
                        obj.treatmentplans = fields.getString("treatmentPlanName");
                        mScheduleAdaptor.add(obj);
                        mScheduleAdaptor.notifyDataSetChanged();



                    }catch ( JSONException e){

                    }

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        Volley.newRequestQueue(this).add(request);

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

    class ScheduleAdaptor extends ArrayAdapter<Schedule.fields> {

        private LayoutInflater mInflater;
        private Context context;
        private ArrayList<Schedule.fields> mValues = new ArrayList<Schedule.fields>();

        public ScheduleAdaptor(Context context, int resource) {
            super(context, resource);
            this.context = context;
            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public void add(Schedule.fields object) {
            super.add(object);
            mValues.add(object);
        }

        public void setData(List<Schedule.fields> data, boolean add) {
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
                        for (Schedule.fields patient : data) {
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


            final Schedule.fields facility = getItem(position);
            TextView detail_view = (TextView) convertView.findViewById(R.id.detail_view);
            TextView patient_name = (TextView) convertView.findViewById(R.id.textView);
            if(!TextUtils.isEmpty(facility.patient))
            patient_name.setText(facility.patient);
            detail_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), DetailView.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("patient_data", facility);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });

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
