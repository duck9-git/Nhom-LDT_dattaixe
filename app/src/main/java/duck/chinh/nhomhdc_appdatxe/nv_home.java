package duck.chinh.nhomhdc_appdatxe;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://apptaixe-da6ad-default-rtdb.asia-southeast1.firebasedatabase.app/");
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
        LinearLayout requestListContainer = findViewById(R.id.requestListContainer);

        databaseReference.child("Bookings").orderByChild("status").equalTo("pending").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                requestListContainer.removeAllViews(); // Xóa các yêu cầu cũ nếu có

                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String startLocation = snapshot.child("startLocation").getValue(String.class);
                        String endLocation = snapshot.child("endLocation").getValue(String.class);
                        String senderEmail = snapshot.child("senderEmail").getValue(String.class);

                        // Tạo TextView hiển thị thông tin yêu cầu
                        TextView requestInfoTextView = new TextView(nv_home.this);
                        requestInfoTextView.setText("Yêu cầu mới từ " + senderEmail + ": Từ " + startLocation + " đến " + endLocation);
                        requestInfoTextView.setPadding(10, 10, 10, 10);
                        requestInfoTextView.setBackgroundColor(Color.WHITE); // Đặt nền trắng
                        requestInfoTextView.setTextColor(Color.BLACK); // Đặt chữ màu đen
                        requestInfoTextView.setTypeface(null, Typeface.BOLD); // Đặt chữ đậm

                        // Tạo nút "Chấp nhận"
                        Button acceptButton = new Button(nv_home.this);
                        acceptButton.setText("Chấp nhận");
                        acceptButton.setBackgroundColor(Color.parseColor("#4CAF50")); // Đặt màu nền xanh lá
                        acceptButton.setTextColor(Color.WHITE); // Chữ trắng

                        // Tạo nút "Hủy"
                        Button cancelButton = new Button(nv_home.this); // Khai báo nút Hủy
                        cancelButton.setText("Hủy");
                        cancelButton.setBackgroundColor(Color.parseColor("#F44336")); // Đặt màu nền đỏ
                        cancelButton.setTextColor(Color.WHITE); // Chữ trắng

                        // Xử lý sự kiện cho nút "Chấp nhận"
                        acceptButton.setOnClickListener(v -> {
                            // Cập nhật trạng thái yêu cầu thành "accepted"
                            snapshot.getRef().child("status").setValue("accepted");
                            Toast.makeText(nv_home.this, "Yêu cầu đã được chấp nhận", Toast.LENGTH_SHORT).show();
                            requestListContainer.removeView(requestInfoTextView);
                            requestListContainer.removeView(acceptButton);
                            requestListContainer.removeView(cancelButton);
                        });

                        // Xử lý sự kiện cho nút "Hủy"
                        cancelButton.setOnClickListener(v -> {
                            // Cập nhật trạng thái yêu cầu thành "canceled"
                            snapshot.getRef().child("status").setValue("canceled");
                            Toast.makeText(nv_home.this, "Yêu cầu đã bị hủy", Toast.LENGTH_SHORT).show();
                            requestListContainer.removeView(requestInfoTextView);
                            requestListContainer.removeView(acceptButton);
                            requestListContainer.removeView(cancelButton);
                        });

                        // Thêm TextView và các nút vào LinearLayout
                        requestListContainer.addView(requestInfoTextView);
                        requestListContainer.addView(acceptButton);
                        requestListContainer.addView(cancelButton);
                    }
                } else {
                    TextView noRequestTextView = new TextView(nv_home.this);
                    noRequestTextView.setText("Không có yêu cầu nào");
                    requestListContainer.addView(noRequestTextView);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("nv_home", "loadPost:onCancelled", databaseError.toException());
            }
        });



    }
}