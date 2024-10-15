package duck.chinh.nhomhdc_appdatxe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class PhoneInputActivity extends AppCompatActivity {

    private EditText phoneEditText;
    private Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_input);

        phoneEditText = findViewById(R.id.phoneEditText);
        continueButton = findViewById(R.id.continueButton);

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = phoneEditText.getText().toString().trim();

                if (isValidPhoneNumber(phoneNumber)) {

                    Intent intent = new Intent(PhoneInputActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(PhoneInputActivity.this, "Số điện thoại không hợp lệ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.length() >= 10;
    }

}
