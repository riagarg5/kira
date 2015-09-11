package com.practo.kira;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ria on 9/11/15.
 */
public class patient_object implements Parcelable {
    public String patient_name = "";
    public boolean has_photo = false;
    public String photourl = "";
    public String treatmentplans = "";
    public boolean cancelled = false;
    public String cancelled_reason = "";
    public String scheduled_at = "";
    public String scheduled_till = "";

    public patient_object(){

    }

    protected patient_object(Parcel in) {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
    public static final Parcelable.Creator<patient_object> CREATOR = new Parcelable.Creator<patient_object>() {
        @Override
        public patient_object createFromParcel(Parcel in) {
            return new patient_object(in);
        }

        @Override
        public patient_object[] newArray(int size) {
            return new patient_object[size];
        }
    };
}
