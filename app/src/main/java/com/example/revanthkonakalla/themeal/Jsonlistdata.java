package com.example.revanthkonakalla.themeal;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

@Entity(tableName = "mealtable")
public class Jsonlistdata implements Parcelable {
    String dishId;
    @NonNull
    @PrimaryKey
    String dishName;
    String country;
    String imageUrl;
    String youtubeUrl;
    String instructions;
    String category;
    String tags;

    public Jsonlistdata() {
    }

    protected Jsonlistdata(Parcel in) {
        dishId = in.readString();
        dishName = in.readString();
        country = in.readString();
        imageUrl = in.readString();
        youtubeUrl = in.readString();
        instructions = in.readString();
        category = in.readString();
        tags = in.readString();
    }

    public static final Creator<Jsonlistdata> CREATOR = new Creator<Jsonlistdata>() {
        @Override
        public Jsonlistdata createFromParcel(Parcel in) {
            return new Jsonlistdata(in);
        }

        @Override
        public Jsonlistdata[] newArray(int size) {
            return new Jsonlistdata[size];
        }
    };

    public String getDishId() {
        return dishId;
    }

    public void setDishId(String dishId) {
        this.dishId = dishId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getYoutubeUrl() {
        return youtubeUrl;
    }

    public void setYoutubeUrl(String youtubeUrl) {
        this.youtubeUrl = youtubeUrl;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(dishId);
        parcel.writeString(dishName);
        parcel.writeString(country);
        parcel.writeString(imageUrl);
        parcel.writeString(youtubeUrl);
        parcel.writeString(instructions);
        parcel.writeString(category);
        parcel.writeString(tags);
    }
}
