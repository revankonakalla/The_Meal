package com.example.revanthkonakalla.themeal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements View.OnClickListener, LoaderManager.LoaderCallbacks<String> {

    EditText editText;
    Button bu;

    private AdView mAdView;


    RecyclerView recyclerView;
    String result;
    List<Jsonserachdata> jsonserachdata = new ArrayList<>();

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_search, container, false);

        mAdView = v.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        bu = v.findViewById(R.id.searchbutton);
        editText = v.findViewById(R.id.editsearch);
        recyclerView = v.findViewById(R.id.recyclesearch);
        bu.setOnClickListener(this);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    myMethod();
                    handled = true;
                }
                return handled;
            }
        });

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    onClick(v);
                    return true;
                }
                return false;
            }
        });
        return v;
    }


    @Override
    public void onClick(View view) {

        myMethod();
    }

    public void myMethod() {
        result = editText.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString("key", result);
        getLoaderManager().restartLoader(1, bundle, this);
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable final Bundle args) {

        return new AsyncTaskLoader<String>(getContext()) {
            String s = "";
            String mealdata = args.getString("key");

            @Override
            protected void onStartLoading() {
                super.onStartLoading();
                forceLoad();
            }

            @Nullable
            @Override
            public String loadInBackground() {
                try {
                    s = Jsonsearchlistpar.getcon(mealdata);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                return s;
            }
        };
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {

        jsonserachdata = Jsonsearchlistpar.jsonsearchParse(data, getContext());
        Searchadapter searchadapter = new Searchadapter(SearchFragment.this, jsonserachdata);
        recyclerView.setAdapter(searchadapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }

}
