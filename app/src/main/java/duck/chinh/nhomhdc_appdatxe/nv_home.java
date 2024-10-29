package duck.chinh.nhomhdc_appdatxe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class nv_home extends AppCompatActivity {


    private boolean isConnected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nv_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView dichvu = findViewById(R.id.dichvu);
        dichvu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(nv_home.this, nvdichvu.class);
                startActivity(intent);
            }
        });

        ImageView tudongnhan = findViewById(R.id.tudongnhan);
        tudongnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(nv_home.this, nvtudong.class);
                startActivity(intent);
            }
        });


        TextView offline = findViewById(R.id.offline);
        TextView batketnoi = findViewById(R.id.batketnoi);

        batketnoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnected) {
                    offline.setText("Bạn đang offline."); // Nội dung khi tắt kết nối
                    offline.setCompoundDrawablesWithIntrinsicBounds(R.drawable.chamdo,0, 0, 0);
                    batketnoi.setText("Bật kết nối"); // Nội dung khi tắt kết nối
                    batketnoi.setCompoundDrawablesWithIntrinsicBounds(R.drawable.nuton,0, 0, 0);
                    batketnoi.setBackgroundResource(R.drawable.vienden);
                    isConnected = false;
                } else {
                    offline.setText("Bạn đang online"); // Nội dung khi bật kết nối
                    offline.setCompoundDrawablesWithIntrinsicBounds( R.drawable.chamxanh,0, 0, 0);
                    batketnoi.setText("Tắt kết nối"); // Nội dung khi tắt kết nối
                    batketnoi.setCompoundDrawablesWithIntrinsicBounds(R.drawable.nuton,0, 0, 0);
                    batketnoi.setBackgroundResource(R.drawable.vienxanh);
                    isConnected = true;
                }
            }
        });
        TextView textView13 = findViewById(R.id.textView13);

        // Thiết lập sự kiện khi nhấn vào TextView
        textView13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang MainActivity2
                Intent intent = new Intent(nv_home.this, MainActivity2.class);
                startActivity(intent);
            }
        });

    }
}