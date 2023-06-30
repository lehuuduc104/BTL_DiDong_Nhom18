package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class AccountActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    BottomNavigationView bottomNavigationView;

    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;
    private Button buttonSave,buttonDelete,buttonBack;

    private DataManager dataManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        getSupportActionBar().setTitle("Thông tin tài khoản");

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.account);

        dataManager = new DataManager(this);

        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        buttonSave = findViewById(R.id.buttonSave);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonBack = findViewById(R.id.btnback);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAccount();
            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loggedInEmail = editTextEmail.getText().toString();
                dataManager.delete(loggedInEmail);

                // Hiển thị thông báo
                Toast.makeText(getApplicationContext(), "Tài khoản đã được xóa", Toast.LENGTH_SHORT).show();

                try {
                    sendEmail(editTextFirstName.getText().toString(), editTextLastName.getText().toString(), editTextEmail.getText().toString(), editTextPassword.getText().toString());
                } catch (MessagingException e) {
                    e.printStackTrace();
                    // Xử lý ngoại lệ hoặc hiển thị thông báo lỗi
                }
                // Chuyển đến trang chính
                Intent intent = new Intent(AccountActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        loadAccount(dataManager);
    }

    private void loadAccount(DataManager dataManager) {
        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        String loggedInEmail = sharedPreferences.getString("email", "");
        String loggedInPassword = sharedPreferences.getString("password", "");
        // Thực hiện truy vấn để lấy dữ liệu người dùng đã đăng nhập
        Cursor cursor = dataManager.searchByEmailAndPassword(loggedInEmail,loggedInPassword);


        if (cursor.moveToFirst()) {
            // Lấy dữ liệu từ con trỏ và hiển thị lên giao diện
            String firstName = cursor.getString(cursor.getColumnIndex(DataManager.TABLE_ROW_FIRSTNAME));
            String lastName = cursor.getString(cursor.getColumnIndex(DataManager.TABLE_ROW_LASTNAME));
            String email = cursor.getString(cursor.getColumnIndex(DataManager.TABLE_ROW_EMAIL));
            String password = cursor.getString(cursor.getColumnIndex(DataManager.TABLE_ROW_PASSWORD));
            String confirmPassword = cursor.getString(cursor.getColumnIndex(DataManager.TABLE_ROW_CONFIRM_PASSWORD));

            editTextFirstName.setText(firstName);
            editTextLastName.setText(lastName);
            editTextEmail.setText(email);
            editTextPassword.setText(password);
            editTextConfirmPassword.setText(confirmPassword);
        }

        if (cursor != null) {
            cursor.close();
        }

    }


    private void saveAccount() {
        // Lấy thông tin người dùng từ giao diện và lưu vào cơ sở dữ liệu
        String firstName = editTextFirstName.getText().toString();
        String lastName = editTextLastName.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        String confirmPassword = editTextConfirmPassword.getText().toString();

        // Kiểm tra tính hợp lệ của dữ liệu nhập vào
        if (isValidInput(firstName, lastName, email, password, confirmPassword)) {
            // Lấy dữ liệu người dùng đã đăng nhập từ DataManager
            Cursor cursor = dataManager.searchByEmail(email);
            if (cursor != null && cursor.moveToFirst()) {
                int userId = cursor.getInt(cursor.getColumnIndex(DataManager.TABLE_ROW_ID));
                // Cập nhật thông tin người dùng trong cơ sở dữ liệu bằng phương thức updateUser() của DataManager
                dataManager.updateUser(userId, firstName, lastName, email, password, confirmPassword);
                try {
                    sendEmail(editTextFirstName.getText().toString(), editTextLastName.getText().toString(), editTextEmail.getText().toString(), editTextPassword.getText().toString());
                } catch (MessagingException e) {
                    e.printStackTrace();
                    // Xử lý ngoại lệ hoặc hiển thị thông báo lỗi
                }
                Toast.makeText(this, "Đã lưu tài khoản thành công", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "Không tìm thấy người dùng", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Đâu vào không hợp lệ. Vui lòng kiểm tra thông tin của bạn.", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendEmail(String firstName, String lastName, String email, String password) throws MessagingException {

        // Khởi tạo các thông tin liên quan đến email
        String stringSenderEmail = "buianhduc115@gmail.com";
        String stringPasswordSenderEmail = "secpffckqvhnsfbt";
        //email = editTextEmail.getText().toString();

        // Địa chỉ máy chủ email
        String stringHost = "smtp.gmail.com";

        // Tạo đối tượng Properties và cấu hình thông tin máy chủ email
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", stringHost);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Tạo đối tượng Session để thiết lập phiên làm việc email
        javax.mail.Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // Xác thực bằng tài khoản email gửi
                return new PasswordAuthentication(stringSenderEmail, stringPasswordSenderEmail);
            }
        });

        String subject = "";
        String emailContent = "";

        if (firstName.isEmpty() && lastName.isEmpty() && password.isEmpty()) {
            subject = "[MoviePlex] Thông báo xoá tài khoản";
            emailContent = "Xin chào " + lastName + ",\n\nTài khoản của bạn đã bị xoá khỏi hệ thống MoviePlex.";
        } else {
            subject = "[MoviePlex] Thông báo thay đổi thông tin";
            emailContent = "Xin chào " + firstName+ lastName + password+ ",\n\nThông tin tài khoản của bạn đã được thay đổi.";
        }

        // Tạo đối tượng MimeMessage để tạo nội dung email
        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

//        mimeMessage.setSubject("[MoviePlex]Chào mừng bạn đến với MoviePlex!\n"); // Đặt chủ đề email
//        String emailContent = "Hello " + lastName + ",\n\nChúc mừng bạn đã tạo tài khoản thành công!\n"+inputEmail+"\nTên người dùng này của bạn được dùng để đăng nhập vào tài khoản của chúng tôi.\n\nADMIN!\nMoviePlex";  // đặt nội dung
        mimeMessage.setSubject(subject);
        mimeMessage.setText(emailContent);
        // Gửi email trong một luồng mới
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Transport.send(mimeMessage); // Gửi email
                } catch (MessagingException e) {
                    e.printStackTrace();
                    // Handle the exception or display an error message
                }
            }
        });
        thread.start(); // Khởi động luồng gửi email
    }
    private boolean isValidInput(String firstName, String lastName, String email, String password, String confirmPassword) {
        // Kiểm tra các trường không được để trống
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            return false;
        }

        // Kiểm tra định dạng email
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return false;
        }

        // Kiểm tra mật khẩu và mật khẩu xác nhận phải giống nhau
        if (!password.equals(confirmPassword)) {
            return false;
        }

        if (password.length() < 6) {
            return false;
        }
        return true; // Trả về true nếu dữ liệu hợp lệ, ngược lại trả về false
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.home:
                startActivity(new Intent(this, HomeActivity.class));
                return true;

            case R.id.account:
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