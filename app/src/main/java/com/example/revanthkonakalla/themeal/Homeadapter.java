package com.example.revanthkonakalla.themeal;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;

public class Homeadapter extends RecyclerView.Adapter<Homeadapter.Holder> {
    Context context;
    List<Jsonlistdata> jsonlistdata;

    public Homeadapter(HomeFragment homeFragment, List<Jsonlistdata> jsonlistdata) {
        this.jsonlistdata = jsonlistdata;
        context = homeFragment.getContext();
    }

    @NonNull
    @Override
    public Homeadapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(context).inflate(R.layout.home_recyle, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Homeadapter.Holder holder, int position) {
        Uri uri = Uri.parse(jsonlistdata.get(position).getImageUrl());
        Picasso.with(context).load(uri).placeholder(R.drawable.loading).into(holder.imageView);
        holder.t.setText(jsonlistdata.get(position).getDishName());
    }

    @Override
    public int getItemCount() {
        return jsonlistdata.size();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView t;

        public Holder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imagehome);
            t = itemView.findViewById(R.id.texthometitle);
            imageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, Detailactivity.class);
            intent.putExtra("id_key", jsonlistdata.get(getAdapterPosition()).getDishId());
            intent.putExtra("name_key", jsonlistdata.get(getAdapterPosition()).getDishName());
            intent.putExtra("cat_key", jsonlistdata.get(getAdapterPosition()).getCategory());
            intent.putExtra("con_key", jsonlistdata.get(getAdapterPosition()).getCountry());
            intent.putExtra("img_key", jsonlistdata.get(getAdapterPosition()).getImageUrl());
            intent.putExtra("inst_key", jsonlistdata.get(getAdapterPosition()).getInstructions());
            intent.putExtra("vid_key", jsonlistdata.get(getAdapterPosition()).getYoutubeUrl());
            intent.putExtra("tag_key", jsonlistdata.get(getAdapterPosition()).getTags());
            context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((Activity) context).toBundle());
        }
    }
}
