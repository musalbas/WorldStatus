package uk.ac.kcl.worldstatus.app;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;

/**
 * A class that implements {@link android.os.Parcelable} used for sending objects between activities.
 *
 *
 *
 * @author Team 2-L
 */
public class ParcelableMap implements Parcelable {
    private HashMap<String, Integer> indicatorDataMap;

    /**
     * Default constructor for initialising the map containing the indicator information.
     *
     * @param indicatorDataMap the map containing the indicators
     */
    public ParcelableMap(HashMap<String, Integer> indicatorDataMap) {
        this.indicatorDataMap = indicatorDataMap;
    }

    /**
     * The construction used to initialise the object when reading from it.
     *
     * @param in the object to read from
     */
    public ParcelableMap(Parcel in) {
        this.indicatorDataMap = in.readHashMap(Integer.class.getClassLoader());
    }

    /**
     * Default method for describing the contents.
     *
     * @return
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Invoked when writing to the {@link android.os.Parcel}.
     * @param dest the Parcel to write to
     * @param flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeMap(indicatorDataMap);
    }

    /**
     * Invoked when creating the Parcelable object.
     */
    @SuppressWarnings("unchecked")
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public ParcelableMap createFromParcel(Parcel in) {
            return new ParcelableMap(in);
        }

        public ParcelableMap[] newArray(int size) {
            return new ParcelableMap[size];
        }
    };

    /**
     * Gets the map containing the indicator
     * @return Returns the map containing the indicator
     */
    public HashMap<String, Integer> getIndicatorDataMap() {
        return indicatorDataMap;
    }
}
