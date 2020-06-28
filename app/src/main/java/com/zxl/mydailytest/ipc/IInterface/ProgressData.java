package com.zxl.mydailytest.ipc.IInterface;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by apple on 2019-10-31.
 * description:
 */
public class ProgressData  implements Parcelable {
    private int progress;
    private String name;

    public ProgressData(int progress, String name) {
        this.progress = progress;
        this.name = name;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Creator<ProgressData> getCREATOR() {
        return CREATOR;
    }

    private ProgressData(Parcel in) {
        progress = in.readInt();
        name = in.readString();
    }

    public static final Creator<ProgressData> CREATOR = new Creator<ProgressData>() {
        @Override
        public ProgressData createFromParcel(Parcel in) {
            return new ProgressData(in);
        }

        @Override
        public ProgressData[] newArray(int size) {
            return new ProgressData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(progress);
        dest.writeString(name);
    }

    public void readFromParcel(Parcel in) {
        progress = in.readInt();
        name = in.readString();
    }
}
