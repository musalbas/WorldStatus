package uk.ac.kcl.worldstatus.app;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;

/**
 * Created by Kristin on 05-12-14.
 */
public class ParcelableMap implements Parcelable {
    private HashMap<String, Integer> indicatorDataMap;

    public ParcelableMap(HashMap<String, Integer> indicatorDataMap) {
        this.indicatorDataMap = indicatorDataMap;
    }

    public ParcelableMap(Parcel in) {
        this.indicatorDataMap = in.readHashMap(Integer.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeMap(indicatorDataMap);
    }

    @SuppressWarnings("unchecked")
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public ParcelableMap createFromParcel(Parcel in) {
            return new ParcelableMap(in);
        }

        public ParcelableMap[] newArray(int size) {
            return new ParcelableMap[size];
        }
    };

    public HashMap<String, Integer> getIndicatorDataMap() {
        return indicatorDataMap;
    }
}
