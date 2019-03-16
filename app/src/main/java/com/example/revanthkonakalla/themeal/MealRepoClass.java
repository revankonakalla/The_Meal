package com.example.revanthkonakalla.themeal;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class MealRepoClass {
    private MealDao mealDao;
    private LiveData<List<Jsonlistdata>> getlist;

    public MealRepoClass(Application application) {
        Mealroom mealroom = Mealroom.getDatabase(application);
        mealDao = mealroom.mealDao();
        getlist = mealDao.getmeals();
    }

    public LiveData<List<Jsonlistdata>> getGetlist() {
        return getlist;
    }

    public void insert(Jsonlistdata jsonlistdata) {
        insertAsynctask asynctask = new insertAsynctask(mealDao);
        asynctask.execute(jsonlistdata);
    }

    private class insertAsynctask extends AsyncTask<Jsonlistdata, Void, Void> {

        MealDao mealDao;

        public insertAsynctask(MealDao mealDao) {
            this.mealDao = mealDao;
        }

        @Override
        protected Void doInBackground(Jsonlistdata... jsonlistdata) {
            mealDao.insert(jsonlistdata[0]);
            return null;
        }
    }

    public void deletedata(Jsonlistdata jsonlistdata) {
        new deleteasynctask(mealDao).execute(jsonlistdata);
    }

    private class deleteasynctask extends AsyncTask<Jsonlistdata, Void, Void> {

        MealDao mealDao;

        public deleteasynctask(MealDao mealDao) {
            this.mealDao = mealDao;
        }

        @Override
        protected Void doInBackground(Jsonlistdata... jsonlistdata) {
            mealDao.deleteAll(jsonlistdata[0]);
            return null;
        }
    }
}
