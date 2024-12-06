package duck.chinh.nhomhdc_appdatxe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class lotrinh extends AppCompatActivity {

    private EditText edtStartLocation, edtEndLocation;
    private Button btnBookRide;

    // Khai báo Firebase
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://apptaixe-da6ad-default-rtdb.asia-southeast1.firebasedatabase.app/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lotrinh);

        // Khởi tạo các thành phần giao diện
        edtStartLocation = findViewById(R.id.vthientai);
        edtEndLocation = findViewById(R.id.vttoi);
        btnBookRide = findViewById(R.id.btn_book);

        // Xử lý sự kiện khi nhấn nút "Đặt xe"
        btnBookRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String startLocation = edtStartLocation.getText().toString();
                String endLocation = edtEndLocation.getText().toString();

                // Kiểm tra xem người dùng có nhập đủ thông tin không
                if (startLocation.isEmpty() || endLocation.isEmpty()) {
                    Toast.makeText(lotrinh.this, "Hãy nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Thay thế FirebaseUser bằng một email giả định
                String senderEmail = "example@example.com"; // Email giả định

                // Lưu thông tin yêu cầu đặt xe vào Firebase
                String bookingId = databaseReference.child("Bookings").push().getKey();
                if (bookingId != null) {
                    // Lưu thông tin vào Firebase
                    databaseReference.child("Bookings").child(bookingId).child("startLocation").setValue(startLocation);
                    databaseReference.child("Bookings").child(bookingId).child("endLocation").setValue(endLocation);
                    databaseReference.child("Bookings").child(bookingId).child("senderEmail").setValue(senderEmail);  // Lưu email người gửi
                    databaseReference.child("Bookings").child(bookingId).child("status").setValue("pending");

                    Toast.makeText(lotrinh.this, "Đặt xe thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(lotrinh.this, HomeActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(lotrinh.this, "Lỗi khi đặt xe", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
