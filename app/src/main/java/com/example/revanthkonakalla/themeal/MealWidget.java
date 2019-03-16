package com.example.revanthkonakalla.themeal;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import android.widget.RemoteViews;

import java.io.InputStream;

import java.net.HttpURLConnection;

import java.net.URL;

import static android.content.Context.MODE_PRIVATE;

/**
 * Implementation of App Widget functionality.
 */
public class MealWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.meal_widget);
        SharedPreferences sharedPreferences = context.getSharedPreferences("sp", MODE_PRIVATE);
        String ding = sharedPreferences.getString("keyshare", "");
        String zing = sharedPreferences.getString("keyimg", "");
        Uri uri = Uri.parse(zing);

        try {
            URL url = new URL(zing);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            Bitmap factory = BitmapFactory.decodeStream(inputStream);
            views.setImageViewBitmap(R.id.widimg, factory);

        } catch (Exception e) {
            e.printStackTrace();
        }

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        views.setTextViewText(R.id.appwidget_text, ding);


        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

