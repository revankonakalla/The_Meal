package com.example.revanthkonakalla.themeal;

import android.content.Context;
import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Jsonsearchlistpar {
    public static final String url = "https://www.themealdb.com/api/json/v1/1/filter.php?a=";

    public static String getcon(String s) throws MalformedURLException {
        String u = url.concat(s);
        Uri uri = Uri.parse(u).buildUpon().build();
        URL stringurl = new URL(uri.toString());

        StringBuffer sb = new StringBuffer();
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) stringurl.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String l = "";
            while ((l = br.readLine()) != null) {
                sb.append(l);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    public static List<Jsonserachdata> jsonsearchParse(String jsonurl, Context context) {
        List<Jsonserachdata> jsonserachdataArrayList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonurl);
            JSONArray jsonArray = jsonObject.getJSONArray("meals");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                String id = object.getString("idMeal");
                String name = object.getString("strMeal");
                String im = object.getString("strMealThumb");
                Jsonserachdata jsonserachdata = new Jsonserachdata();

                jsonserachdata.setSearchid(id);
                jsonserachdata.setSearchname(name);
                jsonserachdata.setSearchimg(im);
                jsonserachdataArrayList.add(jsonserachdata);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonserachdataArrayList;
    }
}
