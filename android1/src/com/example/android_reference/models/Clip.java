package com.example.android_reference.models;

import java.util.Date;

import android.os.Parcel;
import android.os.Parcelable;

// VO class
public class Clip implements Parcelable {
    public long id;
	public String title;
	public String description;
	public int playtime;
	public String thumbnail; // url
	public String youtube;
    public Date created;
    public Date updated;

	public Clip() {

	}

	public Clip(Parcel in) {
	    readFromParcel(in);
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
	    dest.writeLong(id);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeInt(playtime);
        dest.writeString(thumbnail);
        dest.writeString(youtube);
        dest.writeLong(created.getTime());
        dest.writeLong(updated.getTime());
	}

	@Override
    public int describeContents() {
	    return 0;
	}

    private void readFromParcel(Parcel in) {
        id = in.readLong();
        title = in.readString();
        description = in.readString();
        playtime = in.readInt();
        thumbnail = in.readString();
        youtube = in.readString();
        created = new Date(in.readLong());
        updated = new Date(in.readLong());
    }

    public static final Parcelable.Creator<Clip> CREATOR = new Parcelable.Creator<Clip>() {
        @Override
        public Clip createFromParcel(Parcel in) {
           return new Clip(in);
        }

        @Override
        public Clip[] newArray(int size) {
            return new Clip[size];
        }
    };
}
