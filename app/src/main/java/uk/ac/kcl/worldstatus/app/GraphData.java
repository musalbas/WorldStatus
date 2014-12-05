package uk.ac.kcl.worldstatus.app;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;

/**
 * Created by Kristin on 05-12-14.
 */
public class GraphData implements Parcelable {

    private String countryName;
    private HashMap<String, Double> indicatorDataMap;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(countryName);
        dest.writeMap(indicatorDataMap);
    }

    public GraphData() {

    }

    public GraphData(Parcel in) {
        this.countryName = in.readString();
        this.indicatorDataMap = in.readHashMap(Double.class.getClassLoader());
    }

    @SuppressWarnings("unchecked")
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public GraphData createFromParcel(Parcel in) {
            return new GraphData(in);
        }

        public GraphData[] newArray(int size) {
            return new GraphData[size];
        }
    };

    public String getCountryName() {
        return countryName;
    }

    public HashMap<String, Double> getIndicatorDataMap() {
        return indicatorDataMap;
    }
}
