package duck.chinh.nhomhdc_appdatxe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class lotrinh extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lotrinh);

        // Áp dụng lề hệ thống cho giao diện
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Khởi tạo nút đặt xe
        Button bookButton = findViewById(R.id.btn_book);
        bookButton.setOnClickListener(v -> {
            // Tạo một tham chiếu tới Firebase Realtime Database
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference ref = database.getReference("requests");

            // Tạo ID yêu cầu ngẫu nhiên và gửi thông tin yêu cầu
            String requestId = ref.push().getKey(); // Tạo ID ngẫu nhiên cho yêu cầu
            Request request = new Request(
                    "Khách hàng A", // Tên khách hàng
                    "Điểm đến: XYZ", // Điểm đến
                    "Chờ xử lý"      // Trạng thái ban đầu
            );

            // Lưu yêu cầu vào Firebase
            if (requestId != null) {
                ref.child(requestId).setValue(request)
                        .addOnSuccessListener(aVoid -> {
                            // Sau khi đặt xe thành công, chuyển về HomeActivity và thông báo cho người dùng
                            Intent intent = new Intent(lotrinh.this, HomeActivity.class);
                            intent.putExtra("message", "Yêu cầu của bạn đang chờ tài xế xác nhận.");
                            startActivity(intent);
                        })
                        .addOnFailureListener(e -> {
                            // Xử lý nếu có lỗi khi lưu vào Firebase
                            Toast.makeText(lotrinh.this, "Đặt xe thất bại. Vui lòng thử lại!", Toast.LENGTH_SHORT).show();
                        });
            }
        });
    }
}
