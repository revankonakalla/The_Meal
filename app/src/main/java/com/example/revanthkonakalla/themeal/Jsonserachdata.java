package com.example.revanthkonakalla.themeal;

import android.os.Parcel;
import android.os.Parcelable;

public class Jsonserachdata implements Parcelable {
    String searchimg;

    public Jsonserachdata() {
    }

    String searchname;
    String searchid;

    protected Jsonserachdata(Parcel in) {
        searchimg = in.readString();
        searchname = in.readString();
        searchid = in.readString();
    }

    public static final Parcelable.Creator<Jsonserachdata> CREATOR = new Parcelable.Creator<Jsonserachdata>() {
        @Override
        public Jsonserachdata createFromParcel(Parcel in) {
            return new Jsonserachdata(in);
        }

        @Override
        public Jsonserachdata[] newArray(int size) {
            return new Jsonserachdata[size];
        }
    };

    public String getSearchimg() {
        return searchimg;
    }

    public void setSearchimg(String searchimg) {
        this.searchimg = searchimg;
    }

    public String getSearchname() {
        return searchname;
    }

    public void setSearchname(String searchname) {
        this.searchname = searchname;
    }

    public String getSearchid() {
        return searchid;
    }

    public void setSearchid(String searchid) {
        this.searchid = searchid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(searchimg);
        parcel.writeString(searchname);
        parcel.writeString(searchid);
    }
}
