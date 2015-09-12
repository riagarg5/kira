package com.practo.kira;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Ria on 9/11/15.
 */

public class Schedule implements Parcelable {
    ArrayList<fields> schedule = new ArrayList<fields>();


    public Schedule(Parcel in ) {

    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public static final Parcelable.Creator<Schedule> CREATOR = new Parcelable.Creator<Schedule>() {

        @Override
        public Schedule createFromParcel(Parcel source) {
            return new Schedule(source);
        }

        @Override
        public Schedule[] newArray(int size) {
            return new Schedule[size];
        }
    };


    public static class fields implements Parcelable {
        public String patient = "";
        public boolean has_photo = false;
        public int doctor = 0;
        public String photourl = "";
        public String treatmentplans = "";
        public boolean cancelled = false;
        public String cancelled_reason = "";
        public String scheduled_at = "";
        public String scheduled_till = "";

        public fields() {

        }

        protected fields(Parcel in) {

        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {

        }

        public static final Parcelable.Creator<fields> CREATOR = new Parcelable.Creator<fields>() {
            @Override
            public fields createFromParcel(Parcel in) {
                return new fields(in);
            }

            @Override
            public fields[] newArray(int size) {
                return new fields[size];
            }
        };
    }
}
