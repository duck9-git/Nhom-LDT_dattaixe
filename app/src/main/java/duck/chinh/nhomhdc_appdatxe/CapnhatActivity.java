package duck.chinh.nhomhdc_appdatxe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class CapnhatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capnhatprof);

        ImageView backCn = findViewById(R.id.backCn);
        backCn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Button dangxat = findViewById(R.id.dangxuat);
        dangxat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CapnhatActivity.this, dangnhap.class);
                startActivity(intent);
            }
        });

    }
}

