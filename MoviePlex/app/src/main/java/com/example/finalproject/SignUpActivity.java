package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SignUpActivity extends AppCompatActivity {
    EditText firstname;
    EditText lastname;
    EditText email;
    EditText password;
    EditText confirmedpassword;
    Button createAccount;

    DataManager dm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().setTitle("Đăng ký");

        firstname = findViewById(R.id.FirstName);
        lastname = findViewById(R.id.LastName);
        email = findViewById(R.id.NewEmail);
        password = findViewById(R.id.NewPassword);
        confirmedpassword = findViewById(R.id.ConfirmNewPassword);
        createAccount = findViewById(R.id.CreateAccountButton);

        dm = new DataManager(this);

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputFirstname = firstname.getText().toString();
                String inputLastname = lastname.getText().toString();
                String inputEmail = email.getText().toString();
                String inputPassword = password.getText().toString();
                String inputConfirmedPassword = confirmedpassword.getText().toString();

                // Kiểm tra các trường nhập liệu
                if (inputFirstname.isEmpty() || inputLastname.isEmpty() || inputEmail.isEmpty() ||
                        inputPassword.isEmpty() || inputConfirmedPassword.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else if (!isValidEmail(inputEmail)) {
                    Toast.makeText(SignUpActivity.this, "Vui lòng nhập email hợp lệ", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!isValidPassword(inputPassword)) {
                    Toast.makeText(SignUpActivity.this, "Mật khẩu phải dài hơn 6 ký tự", Toast.LENGTH_SHORT).show();
                    return;
                }else if (!inputPassword.equals(inputConfirmedPassword)) {
                    Toast.makeText(SignUpActivity.this, "Mật khẩu xác nhận không khớp", Toast.LENGTH_SHORT).show();
                }else if (dm.isEmailExists(inputEmail)) { // Kiểm tra email đã tồn tại
                        Toast.makeText(SignUpActivity.this, "Email đã được sử dụng", Toast.LENGTH_SHORT).show();
                }else {
                    // Lưu dữ liệu người dùng
                    dm.insert(inputFirstname, inputLastname, inputEmail, inputPassword, inputConfirmedPassword);
                    // Gửi email chúc mừng
                    try {
                        sendEmail(inputEmail, inputLastname);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }

                    Toast.makeText(SignUpActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();

                    // Chuyển đến trang đăng nhập
                    Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void sendEmail(String inputEmail, String lastName) throws MessagingException {

        // Khởi tạo các thông tin liên quan đến email
        String stringSenderEmail = "buianhduc115@gmail.com";
        String stringPasswordSenderEmail = "secpffckqvhnsfbt";
        inputEmail = email.getText().toString();

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

        // Tạo đối tượng MimeMessage để tạo nội dung email
        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(inputEmail));

        mimeMessage.setSubject("[MoviePlex]Chào mừng bạn đến với MoviePlex!\n"); // Đặt chủ đề email
        String emailContent = "Hello " + lastName + ",\n\nChúc mừng bạn đã tạo tài khoản thành công!\n"+inputEmail+"\nTên người dùng này của bạn được dùng để đăng nhập vào tài khoản của chúng tôi.\n\nADMIN!\nMoviePlex";  // đặt nội dung
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

    private boolean isValidEmail(String email) {
        // Sử dụng mẫu regex để kiểm tra định dạng email
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        // Kiểm tra sự khớp với mẫu regex
        return email.matches(emailPattern);
    }

    private boolean isValidPassword(String password) {
        // Kiểm tra chiều dài của mật khẩu
        if (password.length() < 6) {
            return false;
        }


        return true;
    }
}
