package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;
    private ArrayList<DataModel> movies;
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        getSupportActionBar().hide();

        ImageView buy_tickets_button = findViewById(R.id.buy_tickets_button);
        buy_tickets_button.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, MovieListActivity.class);
            startActivity(intent);
        });

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);

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

    @Override
    protected void onResume() {
        super.onResume();
        bottomNavigationView.getMenu().getItem(0).setChecked(true);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.home:
                return true;

            case R.id.account:
                startActivity(new Intent(this, AccountActivity.class));
                return true;

            case R.id.movies_list:
                startActivity(new Intent(this, MovieListActivity.class));
                return true;

            case R.id.contact_us:
                startActivity(new Intent(this, ContactUsActivity.class));
                return true;
        }
        return false;
    }
}