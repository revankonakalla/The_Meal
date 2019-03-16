package com.example.revanthkonakalla.themeal;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Searchadapter extends RecyclerView.Adapter<Searchadapter.MyHolder> {
    Context context;
    List<Jsonserachdata> jsonserachdata = new ArrayList<>();

    public Searchadapter(SearchFragment searchFragment, List<Jsonserachdata> jsonserachdata) {
        context = searchFragment.getContext();
        this.jsonserachdata = jsonserachdata;
    }

    @NonNull
    @Override
    public Searchadapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Searchadapter.MyHolder(LayoutInflater.from(context).inflate(R.layout.search_recycle, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Searchadapter.MyHolder holder, int position) {
        Uri uri = Uri.parse(jsonserachdata.get(position).getSearchimg());
        Picasso.with(context).load(uri).placeholder(R.drawable.loading).into(holder.iv);
        holder.textView.setText(jsonserachdata.get(position).getSearchname());
    }

    @Override
    public int getItemCount() {
        return jsonserachdata.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView iv;

        public MyHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.imagesearch);
            textView = itemView.findViewById(R.id.textsearchtitle);
        }
    }
}
