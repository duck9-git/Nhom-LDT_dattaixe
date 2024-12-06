package duck.chinh.nhomhdc_appdatxe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class dangnhap extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance()
            .getReferenceFromUrl("https://apptaixe-da6ad-default-rtdb.asia-southeast1.firebasedatabase.app/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);

        final EditText email = findViewById(R.id.txtemail);
        final EditText password = findViewById(R.id.txtmatkhau);
        final Button loginbtn = findViewById(R.id.btnDangNhap);
        final Button dkngay = findViewById(R.id.btnDangKi);

        // Xử lý sự kiện khi nhấn nút đăng nhập
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String emailText = email.getText().toString();
                final String passwordText = password.getText().toString();

                if (emailText.isEmpty() || passwordText.isEmpty()) {
                    Toast.makeText(dangnhap.this, "Bạn nhập thiếu, xin nhập lại", Toast.LENGTH_SHORT).show();
                } else {
                    final String emailKey = emailText.replace(".", ",");
                    // Kiểm tra trong nhánh "khach"
                    databaseReference.child("User").child("khach").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(emailKey)) {
                                final String getPassword = snapshot.child(emailKey).child("password").getValue(String.class);
                                if (getPassword != null && getPassword.equals(passwordText)) {
                                    Toast.makeText(dangnhap.this, "Đăng nhập thành công (Khách hàng)", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(dangnhap.this, HomeActivity.class)); //
                                    finish();
                                } else {
                                    Toast.makeText(dangnhap.this, "Nhập sai email hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                // Kiểm tra trong nhánh "taixe"
                                databaseReference.child("User").child("taixe").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if (snapshot.hasChild(emailKey)) {
                                            final String getPassword = snapshot.child(emailKey).child("password").getValue(String.class);
                                            if (getPassword != null && getPassword.equals(passwordText)) {
                                                Toast.makeText(dangnhap.this, "Đăng nhập thành công (Tài xế)", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(dangnhap.this, nv_home.class)); // Trang dành cho tài xế
                                                finish();
                                            } else {
                                                Toast.makeText(dangnhap.this, "Nhập sai email hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                                            }
                                        } else {
                                            Toast.makeText(dangnhap.this, "Email không tồn tại", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                        Toast.makeText(dangnhap.this, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(dangnhap.this, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        // Xử lý sự kiện khi nhấn nút "Đăng ký ngay"
        dkngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dangnhap.this, dangki.class));
            }
        });
    }
}
