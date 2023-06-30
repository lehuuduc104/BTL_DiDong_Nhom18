package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.HashSet;
import java.util.Set;

public class PayActivity extends AppCompatActivity {

    private Button buttonConfirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        getSupportActionBar().setTitle("Hóa đơn");

        displayPay();

        buttonConfirm = findViewById(R.id.btnconfirm);
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PayActivity.this, MovieListActivity.class);
                startActivity(intent);
            }
        });
    }

    public void displayPay() {
        TextView textViewTitle = findViewById(R.id.textViewMovieName);
        TextView textViewCinema = findViewById(R.id.textViewCinemaName);
        TextView textViewTime = findViewById(R.id.textViewTimeSlot);
        TextView textViewSeatCount = findViewById(R.id.textViewQuantity);
        TextView textViewSeatNumber = findViewById(R.id.textViewSeatNumber);
        TextView textViewPrice = findViewById(R.id.textViewPrice);
        // Lấy thông tin phim từ SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MovieInfo", Context.MODE_PRIVATE);
        String title = sharedPreferences.getString("title", "");

        SharedPreferences sharedPreferencesCinema = getSharedPreferences("MyCinema", Context.MODE_PRIVATE);
        String cinema = sharedPreferencesCinema.getString("selectedCinema","");

        SharedPreferences sharedPreferencesTime = getSharedPreferences("MyTime", Context.MODE_PRIVATE);
        String time = sharedPreferencesTime.getString("selectedShowtime", "");

        SharedPreferences sharedPreferencesBook = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        int seatCount = sharedPreferencesBook.getInt("seatCount", 0);
        Set<String> selectedSeatsSet = sharedPreferencesBook.getStringSet("selectedSeats", new HashSet<>());
        int totalPrice = sharedPreferencesBook.getInt("totalPrice", 0);


        textViewTitle.setText(title);
        textViewCinema.setText(cinema);
        textViewTime.setText(time);
        textViewSeatCount.setText(String.valueOf(seatCount));
        textViewSeatNumber.setText(TextUtils.join(", ", selectedSeatsSet));
        textViewPrice.setText(String.format("%d VND", totalPrice));


    }
}