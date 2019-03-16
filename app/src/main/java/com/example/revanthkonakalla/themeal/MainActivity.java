package com.example.revanthkonakalla.themeal;


import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Jsonlistdata> jsonlistdata = new ArrayList<>();
    String opt = "home";
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomnav);
        if (savedInstanceState != null) {

            opt = savedInstanceState.getString("savekey");
            getFrag(opt);


        } else {
            getFrag(opt);
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.homemenu:
                        opt = "home";
                        getFrag(opt);
                        setTitle("home");
                        break;
                    case R.id.favmenu:
                        opt = "fav";
                        getFrag(opt);
                        setTitle("favourites");
                        break;
                    case R.id.searchmenu:
                        setTitle("search");
                        getSearchFrag();
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getFrag(opt);
    }

    public void getFrag(String s) {

        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        HomeFragment fragment1 = new HomeFragment(s);
        manager.beginTransaction().replace(R.id.homeframe, fragment1).commit();
    }

    public void getSearchFrag() {
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        SearchFragment searchFragment = new SearchFragment();
        fragmentManager.beginTransaction().replace(R.id.homeframe, searchFragment).commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("list", (ArrayList<? extends Parcelable>) jsonlistdata);
        outState.putString("savekey", opt);
    }
}
