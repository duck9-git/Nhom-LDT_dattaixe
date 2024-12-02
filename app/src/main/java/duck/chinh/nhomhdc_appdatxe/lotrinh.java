package duck.chinh.nhomhdc_appdatxe;

import android.os.Bundle;
import android.util.Log;
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
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference ref = database.getReference("requests");

            String requestId = ref.push().getKey();
            Request request = new Request("Khách hàng A", "Điểm đến: XYZ", "Chờ xử lý");

            if (requestId != null) {
                ref.child(requestId).setValue(request)
                        .addOnSuccessListener(aVoid -> {
                            Toast.makeText(lotrinh.this, "Đặt xe thành công!", Toast.LENGTH_SHORT).show();
                        })
                        .addOnFailureListener(e -> {
                            // Log lỗi chi tiết để xem nguyên nhân
                            Log.e("FirebaseError", "Lỗi khi ghi dữ liệu vào Firebase", e);
                            Toast.makeText(lotrinh.this, "Đặt xe thất bại. Vui lòng thử lại.", Toast.LENGTH_SHORT).show();
                        });
            }
        });

    }
}
