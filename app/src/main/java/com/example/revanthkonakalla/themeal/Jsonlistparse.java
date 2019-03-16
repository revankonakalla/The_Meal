package com.example.revanthkonakalla.themeal;

import android.content.Context;

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

public class Jsonlistparse {
    public static final String url = "https://www.themealdb.com/api/json/v1/1/latest.php";

    public static String getType() throws MalformedURLException {
        URL stringurl = new URL(url);

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

    public static List<Jsonlistdata> jsonParse(String jsonurl, Context context) {
        List<Jsonlistdata> jsonlistdataList = new ArrayList<Jsonlistdata>();

        try {
            JSONObject jsonObject = new JSONObject(jsonurl);
            JSONArray jsonArray = jsonObject.getJSONArray("meals");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                String id = object.getString("idMeal");
                String name = object.getString("strMeal");
                String country = object.getString("strArea");
                String category = object.getString("strCategory");
                String image = object.getString("strMealThumb");
                String youtubeUrl = object.getString("strYoutube");
                String tag = object.getString("strTags");
                String instru = object.getString("strInstructions");
                Jsonlistdata jsonlistdata = new Jsonlistdata();

                jsonlistdata.setDishId(id);
                jsonlistdata.setDishName(name);
                jsonlistdata.setCountry(country);
                jsonlistdata.setCategory(category);
                jsonlistdata.setImageUrl(image);
                jsonlistdata.setTags(tag);
                jsonlistdata.setYoutubeUrl(youtubeUrl);
                jsonlistdata.setInstructions(instru);
                jsonlistdataList.add(jsonlistdata);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonlistdataList;
    }
}

