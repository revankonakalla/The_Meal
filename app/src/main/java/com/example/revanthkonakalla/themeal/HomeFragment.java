package com.example.revanthkonakalla.themeal;


import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements LoaderManager.LoaderCallbacks<String> {

    RecyclerView recyclerView;
    List<Jsonlistdata> jsonlistdata = new ArrayList<>();
    MainActivity a = new MainActivity();
    String s;

    public HomeFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public HomeFragment(String s) {
        this.s = s;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = v.findViewById(R.id.homerecyle);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        if (s == "home") {
            getLoaderManager().initLoader(1, null, this);
        } else if (s == "fav") {
            Modelview modelview = ViewModelProviders.of(getActivity()).get(Modelview.class);

            modelview.getListMutableLiveData().observe(HomeFragment.this, new Observer<List<Jsonlistdata>>() {
                @Override
                public void onChanged(@Nullable List<Jsonlistdata> jsonlistdataList) {
                    if (jsonlistdataList.size() != 0) {
                        recyclerView.setAdapter(new Homeadapter(HomeFragment.this, jsonlistdataList));
                    }
                }
            });
        }
        return v;

    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        return new AsyncTaskLoader<String>(getContext()) {
            String s = "";

            @Override
            protected void onStartLoading() {
                super.onStartLoading();
                forceLoad();
            }

            @Nullable
            @Override
            public String loadInBackground() {

                try {

                    s = Jsonlistparse.getType();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                return s;
            }
        };
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        jsonlistdata = Jsonlistparse.jsonParse(data, getContext());
        Homeadapter myadapter = new Homeadapter(HomeFragment.this, jsonlistdata);
        recyclerView.setAdapter(myadapter);
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}
