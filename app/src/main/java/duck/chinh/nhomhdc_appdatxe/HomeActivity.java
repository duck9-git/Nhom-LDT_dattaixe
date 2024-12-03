package duck.chinh.nhomhdc_appdatxe;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import duck.chinh.nhomhdc_appdatxe.fragment.AccountFragment;
import duck.chinh.nhomhdc_appdatxe.fragment.HistoryFragment;
import duck.chinh.nhomhdc_appdatxe.fragment.MessFragment;
import duck.chinh.nhomhdc_appdatxe.fragment.WalletFragment;

public class HomeActivity extends AppCompatActivity {

    private ImageView btnXeMay, btnOto, btnAll;
    private EditText searchBar;
    private TextView messageTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home); // tệp layout activity_home.xml
        messageTextView = findViewById(R.id.messageTextView);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.messageTextView), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        // Khởi tạo các view
        btnXeMay = findViewById(R.id.btn_xemay);
        btnOto = findViewById(R.id.btn_oto);
        btnAll = findViewById(R.id.btn_all);
        searchBar = findViewById(R.id.search_bar);

        // Thiết lập onClickListener cho nút "Xe máy"
        btnXeMay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToTripActivity();
            }
        });

        // Thiết lập onClickListener cho nút "Ô tô"
        btnOto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToTripActivity();
            }
        });

        // Thiết lập onClickListener cho nút "Tất cả"
        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý hành động cho nút tất cả ở đây
            }
        });


        Intent intent = getIntent();
        String message = intent.getStringExtra("message");
        if (message != null) {
            messageTextView.setText(message);
        }
        // Xử lý Bottom Navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_menu);

        // Thiết lập trang chủ được chọn mặc định
        bottomNavigationView.setSelectedItemId(R.id.Home);

        // Thiết lập listener cho sự kiện chọn item trong bottom navigation
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null; // Khởi tạo fragment được chọn

                // Thay thế switch-case bằng if-else
                if (item.getItemId() == R.id.Home) {
                    Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                    return true; // Ở lại trang hiện tại
                } else if (item.getItemId() == R.id.activity) {
                    selectedFragment = new HistoryFragment();
                } else if (item.getItemId() == R.id.mess) {
                    selectedFragment = new MessFragment();
                } else if (item.getItemId() == R.id.payment) {
                    selectedFragment = new WalletFragment();
                } else if (item.getItemId() == R.id.profile) {
                    selectedFragment = new AccountFragment();
                }

                return loadFragment(selectedFragment);
            }

        });
    }

    private void navigateToTripActivity() {
        Intent intent = new Intent(HomeActivity.this, TripActivity.class);
        startActivity(intent);
    }

    private boolean loadFragment(Fragment fragment) {
        // Thay đổi fragment
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment) // R.id.fragment_container là ID của ViewGroup chứa fragment
                    .commit();
            return true;
        }
        return false;
    }
}
