package com.example.revanthkonakalla.themeal;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

public class Modelview extends AndroidViewModel {
    private MealRepoClass mealRepoClass;
    LiveData<List<Jsonlistdata>> listMutableLiveData;
    Context c;

    public Modelview(@NonNull Application application) {
        super(application);
        mealRepoClass = new MealRepoClass(application);
        listMutableLiveData = mealRepoClass.getGetlist();
    }

    public LiveData<List<Jsonlistdata>> getListMutableLiveData() {
        return listMutableLiveData;
    }

    public void inserttorepoclass(Jsonlistdata jsonlistdata) {
        mealRepoClass.insert(jsonlistdata);
    }

    public void deletetorepoclass(Jsonlistdata jsonlistdata) {
        mealRepoClass.deletedata(jsonlistdata);
    }

}
