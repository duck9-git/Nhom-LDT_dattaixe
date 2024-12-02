package duck.chinh.nhomhdc_appdatxe;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class nv_home extends AppCompatActivity {

    private boolean isConnected = false;  // Trạng thái kết nối
    private TextView requestListTextView; // TextView hiển thị yêu cầu

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nv_home);

        // Áp dụng lề hệ thống
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Tham chiếu các thành phần UI
        ImageView dichvu = findViewById(R.id.dichvu);
        ImageView tudongnhan = findViewById(R.id.tudongnhan);
        TextView offline = findViewById(R.id.offline);
        TextView batketnoi = findViewById(R.id.batketnoi);
        TextView textView13 = findViewById(R.id.textView13);
        requestListTextView = findViewById(R.id.requestTextView); // Kết nối TextView để hiển thị yêu cầu

        // Chuyển Activity khi nhấn vào các nút
        dichvu.setOnClickListener(v -> {
            Intent intent = new Intent(nv_home.this, nvdichvu.class);
            startActivity(intent);
        });

        tudongnhan.setOnClickListener(v -> {
            Intent intent = new Intent(nv_home.this, nvtudong.class);
            startActivity(intent);
        });

        textView13.setOnClickListener(v -> {
            Intent intent = new Intent(nv_home.this, MainActivity2.class);
            startActivity(intent);
        });

        // Sự kiện bật/tắt kết nối
        batketnoi.setOnClickListener(v -> {
            if (isConnected) {
                offline.setText("Bạn đang offline.");
                offline.setCompoundDrawablesWithIntrinsicBounds(R.drawable.chamdo, 0, 0, 0);
                batketnoi.setText("Bật kết nối");
                batketnoi.setCompoundDrawablesWithIntrinsicBounds(R.drawable.nuton, 0, 0, 0);
                batketnoi.setBackgroundResource(R.drawable.vienden);
                isConnected = false;
            } else {
                offline.setText("Bạn đang online");
                offline.setCompoundDrawablesWithIntrinsicBounds(R.drawable.chamxanh, 0, 0, 0);
                batketnoi.setText("Tắt kết nối");
                batketnoi.setCompoundDrawablesWithIntrinsicBounds(R.drawable.nuton, 0, 0, 0);
                batketnoi.setBackgroundResource(R.drawable.vienxanh);
                isConnected = true;
            }
        });

        // Lắng nghe yêu cầu từ Firebase
        listenForRequests();
    }

    private void listenForRequests() {
        // Tham chiếu đến Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference requestsRef = database.getReference("requests");

        // Lắng nghe thay đổi trong Firebase
        requestsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                StringBuilder requests = new StringBuilder();
                for (DataSnapshot requestSnapshot : snapshot.getChildren()) {
                    // Lấy dữ liệu từ Firebase
                    String customerName = requestSnapshot.child("customerName").getValue(String.class);
                    String destination = requestSnapshot.child("destination").getValue(String.class);
                    String status = requestSnapshot.child("status").getValue(String.class);

                    // Thêm yêu cầu vào danh sách
                    requests.append("Khách hàng: ").append(customerName)
                            .append("\nĐiểm đến: ").append(destination)
                            .append("\nTrạng thái: ").append(status)
                            .append("\n\n");
                }

                // Hiển thị danh sách yêu cầu trong TextView
                requestListTextView.setText(requests.toString());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Nếu có lỗi khi đọc dữ liệu
                Log.e("nv_home", "Firebase Error: " + error.getMessage());
            }
        });
    }
}
