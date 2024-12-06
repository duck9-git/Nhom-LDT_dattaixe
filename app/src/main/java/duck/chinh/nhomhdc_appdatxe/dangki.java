package duck.chinh.nhomhdc_appdatxe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class dangki extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://apptaixe-da6ad-default-rtdb.asia-southeast1.firebasedatabase.app/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangki);

        final EditText fullname = findViewById(R.id.txtnguoidung);
        final EditText email = findViewById(R.id.txtemail);
        final EditText password = findViewById(R.id.txtmatkhau);
        final EditText conpassword = findViewById(R.id.txtmatkhaulai);

        final Button registerBtn = findViewById(R.id.btnDangki);
        final RadioGroup roleGroup = findViewById(R.id.roleGroup);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String fullnametxt = fullname.getText().toString().trim();
                final String emailtxt = email.getText().toString().trim().replace(".", ",");
                final String passwordtxt = password.getText().toString().trim();
                final String conpasswordtxt = conpassword.getText().toString().trim();

                if (fullnametxt.isEmpty() || emailtxt.isEmpty() || passwordtxt.isEmpty() || conpasswordtxt.isEmpty()) {
                    Toast.makeText(dangki.this, "Bạn nhập còn thiếu", Toast.LENGTH_SHORT).show();
                } else if (!passwordtxt.equals(conpasswordtxt)) {
                    Toast.makeText(dangki.this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                } else {
                    int selectedRoleId = roleGroup.getCheckedRadioButtonId();
                    final String role; // Biến được khai báo final

                    if (selectedRoleId == R.id.rbKhach) {
                        role = "khach";
                    } else {
                        role = "taixe";
                    }

                    databaseReference.child("User").child(role).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(emailtxt)) {
                                Toast.makeText(dangki.this, "Email đã tồn tại", Toast.LENGTH_SHORT).show();
                            } else {
                                databaseReference.child("User").child(role).child(emailtxt).child("fullname").setValue(fullnametxt);
                                databaseReference.child("User").child(role).child(emailtxt).child("password").setValue(passwordtxt);

                                Toast.makeText(dangki.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                Intent intent;
                                if (role.equals("taixe")) {
                                    intent = new Intent(dangki.this, nv_home.class); // Trang chủ của tài xế
                                } else {
                                    intent = new Intent(dangki.this, HomeActivity.class); // Trang chủ của khách
                                }
                                startActivity(intent);
                                finish();
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(dangki.this, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
