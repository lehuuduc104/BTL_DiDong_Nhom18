package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BookNowActivity extends AppCompatActivity {
    private ArrayList<String> selectedSeats = new ArrayList<>();

    private TextView textViewPrice;

    private int ticketPrice = 50000;
    private int totalPrice = 0;

    private Spinner spinnerCinema;
    private ArrayAdapter<String> spinnerAdapter;
    private String[] cinemaList;
    private  Button btnPay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_now);
        getSupportActionBar().setTitle("Đặt vé");

        textViewPrice = findViewById(R.id.textViewPrice);
        setSeatClickListener();

        displayMovieDescription();

        spinnerCinema = findViewById(R.id.spinnerCinema);

        // Danh sách các rạp phim
        cinemaList = new String[]{"Beta Giải Phóng", "CGV Vincom Bà Triệu", "BHD Star Phạm Ngọc Thạch"};

        // Tạo ArrayAdapter và thiết lập danh sách các mục
        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cinemaList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCinema.setAdapter(spinnerAdapter);

        spinnerCinema.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCinema = cinemaList[position];
                saveSelectedCinema(selectedCinema);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Không làm gì khi không có mục nào được chọn
            }
        });

        RadioGroup radioGroupShowtimes = findViewById(R.id.radioGroupShowtimes);

        radioGroupShowtimes.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton selectedRadioButton = findViewById(checkedId);
                String selectedShowtime = selectedRadioButton.getText().toString();
                saveSelectedShowtime(selectedShowtime);
            }
        });

        btnPay = findViewById(R.id.buttonPayment);

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Chuyển sang trang SignUpActivity
                Intent intent = new Intent(BookNowActivity.this, PayActivity.class);
                startActivity(intent);
            }
        });
    }

    private void saveSelectedShowtime(String showtime) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyTime", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("selectedShowtime", showtime);
        editor.apply();
    }

    private void saveSelectedCinema(String cinema) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyCinema", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("selectedCinema", cinema);
        editor.apply();
    }

    public void displayMovieDescription() {
        ImageView imageViewMovie = findViewById(R.id.imageViewMovie);
        TextView textViewTitle = findViewById(R.id.textView_MovieTitle);
        TextView textViewDescription = findViewById(R.id.textViewMovieDescription);

        // Lấy thông tin phim từ SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MovieInfo", Context.MODE_PRIVATE);
        int imageResource = sharedPreferences.getInt("imageResource", 0);
        String title = sharedPreferences.getString("title", "");
        String description = sharedPreferences.getString("description", "");

        imageViewMovie.setImageResource(imageResource);
        textViewTitle.setText(title);
        textViewDescription.setText(description);
    }


    private void setSeatClickListener() {
        GridLayout gridLayoutSeats = findViewById(R.id.gridLayoutSeats);

        for (int i = 0; i < gridLayoutSeats.getChildCount(); i++) {
            View childView = gridLayoutSeats.getChildAt(i);
            if (childView instanceof Button) {
                final Button seatButton = (Button) childView;
                seatButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Check if the seat is already selected
                        if (selectedSeats.contains(seatButton.getText().toString())) {
                            // Seat is already selected, remove it from selectedSeats
                            selectedSeats.remove(seatButton.getText().toString());
                            seatButton.setBackgroundColor(ContextCompat.getColor(BookNowActivity.this, R.color.purple_500));

//                            seatButton.setBackgroundColor(Color.parseColor("#0080ff")); // Set background color to deselected state
                        } else {
                            // Seat is not selected, add it to selectedSeats
                            selectedSeats.add(seatButton.getText().toString());
                            seatButton.setBackgroundColor(Color.parseColor("#ff0000")); // Set background color to selected state
                        }
                        totalPrice = ticketPrice * selectedSeats.size();
                        textViewPrice.setText(String.format("%d VND", totalPrice));

                        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                        // Tạo đối tượng Editor để chỉnh sửa SharedPreferences
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        // Lưu số lượng ghế
                        editor.putInt("seatCount", selectedSeats.size());
                        // Lưu dữ liệu ghế đã chọn
                        Set<String> selectedSeatsSet = new HashSet<>(selectedSeats);
                        editor.putStringSet("selectedSeats", selectedSeatsSet);
                        // Lưu giá tiền
                        editor.putInt("totalPrice", totalPrice);
                        // Lưu các thay đổi vào SharedPreferences
                        editor.apply();
                    }
                });
            }
        }
    }

}
