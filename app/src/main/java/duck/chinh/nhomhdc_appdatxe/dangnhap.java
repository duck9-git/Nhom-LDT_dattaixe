package duck.chinh.nhomhdc_appdatxe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class dangnhap extends AppCompatActivity {
    private Button DangKi;
    private Button DangNhap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dangnhap);
       DangKi = findViewById(R.id.btnDangKi);
       DangNhap = findViewById(R.id.btnDangNhap);
        DangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(dangnhap.this, dangki.class);
                startActivity(intent);
                finish();
            }
        });
        DangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(dangnhap.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}