package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<DataModel> movies;
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        ImageView buy_tickets_button = findViewById(R.id.buy_tickets_button);
        buy_tickets_button.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        movies = new ArrayList<>();
        movies.add(new DataModel("Arvenger", R.drawable.avengers, "Mô tả phim 1"));
        movies.add(new DataModel("Spiderman", R.drawable.spiderman, "Mô tả phim 2"));
        movies.add(new DataModel("Thor", R.drawable.thor, "Mô tả phim 3"));

        // Khởi tạo RecyclerView và Adapter
        RecyclerView recyclerView = findViewById(R.id.movie_recycler_view);
        movieAdapter = new MovieAdapter(movies);

        // Thiết lập LayoutManager và Adapter cho RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(movieAdapter);
    }
}