package duck.chinh.nhomhdc_appdatxe;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Button;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class TripActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);

        ImageButton backButton = findViewById(R.id.back_button);
        Button bookNowButton = findViewById(R.id.btn_book_now);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bookNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang màn hình LotrinhActivity
                Intent intent = new Intent(TripActivity.this, lotrinh.class);
                startActivity(intent);
            }
        });

    }
}
