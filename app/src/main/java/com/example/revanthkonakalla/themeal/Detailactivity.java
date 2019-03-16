package com.example.revanthkonakalla.themeal;

import android.annotation.SuppressLint;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class Detailactivity extends AppCompatActivity implements View.OnClickListener {
    @InjectView(R.id.dettextcat)
    TextView tags;
    @InjectView(R.id.dettextcon)
    TextView cons;
    @InjectView(R.id.dettextinst)
    TextView insts;
    @InjectView(R.id.dettexttag)
    TextView cats;
    @InjectView(R.id.dettextuurl)
    TextView urls;
    @InjectView(R.id.dettextname)
    TextView names;
    public static final String namesss = "com.example.revanthkonakalla.themeal.action";
    @InjectView(R.id.detimg)
    ImageView iv;
    @InjectView(R.id.detailfav)
    Button detfav;
    @InjectView(R.id.editfeed)
    EditText editText;
    String id, url, t, ca, co, in, nam, im;
    @InjectView(R.id.mytoolkit)
    Toolbar toolbar;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent i = getIntent();
        setTitle("details");
        ButterKnife.inject(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        t = i.getStringExtra("tag_key");
        ca = i.getStringExtra("cat_key");
        co = i.getStringExtra("con_key");
        in = i.getStringExtra("inst_key");
        url = i.getStringExtra("vid_key");
        nam = i.getStringExtra("name_key");
        im = i.getStringExtra("img_key");
        id = i.getStringExtra("id_key");
        tags.setText(t);
        cons.setText(co);
        cats.setText(ca);
        insts.setText(in);
        if (url.equals("")) {

            urls.setText(R.string.no_url);
        } else {
            urls.setText(url);
            urls.setOnClickListener(this);
        }

        names.setText(nam);
        Picasso.with(this).load(im).into(iv);
     //   getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Jsonlistdata jsonmovie1 = Mealroom.getDatabase(this).mealDao().getonly(id);
        if (jsonmovie1 == null) {
            detfav.setBackgroundResource(R.drawable.ic_favorite_border_black_24dp);
        } else {
            detfav.setBackgroundResource(R.drawable.ic_favorite_red_24dp);
        }
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onfav(View view) {
        Jsonlistdata jsm = Mealroom.getDatabase(this).mealDao().getonly(id);
        if (jsm != null) {
            Mealroom.getDatabase(this).mealDao().deleteAll(jsm);
            detfav.setBackgroundResource(R.drawable.ic_favorite_border_black_24dp);
            Snackbar.make(view, R.string.remove_snackbar, Snackbar.LENGTH_SHORT)
                    .show();
        } else {
            Jsonlistdata jsonlistdata = new Jsonlistdata();
            jsonlistdata.setImageUrl(im);
            jsonlistdata.setDishId(id);
            jsonlistdata.setInstructions(in);
            jsonlistdata.setYoutubeUrl(url);
            jsonlistdata.setTags(t);
            jsonlistdata.setCountry(co);
            jsonlistdata.setCategory(ca);
            jsonlistdata.setDishName(nam);
            Mealroom.getDatabase(this).mealDao().insert(jsonlistdata);
            detfav.setBackgroundResource(R.drawable.ic_favorite_red_24dp);
            Snackbar.make(view, R.string.add_snackbar, Snackbar.LENGTH_SHORT)
                    .show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        } else if (item.getItemId() == R.id.widmenu) {
            StringBuilder builder = new StringBuilder();
            builder.append(nam + "\n" + ca);
            SharedPreferences sharedPreferences = getSharedPreferences("sp", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.putString("keyshare", builder.toString());
            editor.putString("keyimg", im);
            editor.commit();
            Intent intent = new Intent(this, MealWidget.class);
            intent.setAction("android.appwidget.action.APPWIDGET_UPDATE");
            int widgetId[] = AppWidgetManager.getInstance(getApplication()).getAppWidgetIds(new ComponentName(getApplication(), MealWidget.class));
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, widgetId);
            sendBroadcast(intent);
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        this.startActivity(intent);
    }


    public void sendfeedback(View view) {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("yourfeedbacks");
        String pushId = reference.push().getKey();
        reference.child(pushId).setValue(editText.getText().toString());
        Toast.makeText(this, R.string.thanks_feed, Toast.LENGTH_SHORT).show();
    }
}
