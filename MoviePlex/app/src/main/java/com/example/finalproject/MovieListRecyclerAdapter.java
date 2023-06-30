package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MovieListRecyclerAdapter extends RecyclerView.Adapter<MovieListRecyclerAdapter.RecyclerViewHolder> {

    private ArrayList<DataModel> movieDataArrayList;
    private Context mcontext;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public MovieListRecyclerAdapter(ArrayList<DataModel> recyclerDataArrayList, Context mcontext) {
        this.movieDataArrayList = recyclerDataArrayList;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_recyler_view, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        // Set the data to textview and imageview.
        DataModel recyclerData = movieDataArrayList.get(position);
        holder.text.setText(recyclerData.getTitle());
        holder.image.setImageResource(recyclerData.getImageResource());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Lưu thông tin phim vào SharedPreferences
                sharedPreferences = mcontext.getSharedPreferences("MovieInfo", Context.MODE_PRIVATE);
                editor = sharedPreferences.edit();
                editor.putInt("imageResource", recyclerData.getImageResource());
                editor.putString("title", recyclerData.getTitle());
                editor.putString("description", recyclerData.getDescription());
                editor.apply();


                // Tạo Intent để chuyển đến trang BookNowActivity
                Intent intent = new Intent(mcontext, BookNowActivity.class);

                // Chạy Intent
                mcontext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        // this method returns the size of recyclerview
        return movieDataArrayList.size();
    }

    // View Holder Class to handle Recycler View.
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView text;
        private ImageView image;
        private Button button;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.movie_title);
            image = itemView.findViewById(R.id.movie_image);
            button = itemView.findViewById(R.id.book_now_button);


        }
    }
}