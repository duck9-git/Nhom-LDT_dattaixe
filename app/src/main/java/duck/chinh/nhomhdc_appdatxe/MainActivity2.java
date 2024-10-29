package duck.chinh.nhomhdc_appdatxe;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity2 extends AppCompatActivity {

    private ViewPager2 nvViewPager2;
    private BottomNavigationView nvBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nvViewPager2 = findViewById(R.id.view_pager_2_nv);
        nvBottomNavigationView = findViewById(R.id.bottom_navigation_nv);

        MyViewPagerAdapter_nv myViewPagerAdapterNv = new MyViewPagerAdapter_nv(this);
        nvViewPager2.setAdapter(myViewPagerAdapterNv);

        nvBottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.bottom_income_nv){
                    nvViewPager2.setCurrentItem(0);
                } else if(id == R.id.bottom_diamond_nv){
                    nvViewPager2.setCurrentItem(1);
                } else if(id == R.id.bottom_wallet_nv){
                    nvViewPager2.setCurrentItem(2);
                }
                return true;
            }
        });
        nvViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case 0:
                        nvBottomNavigationView.getMenu().findItem(R.id.bottom_income_nv).setChecked(true);
                        break;
                    case 1:
                        nvBottomNavigationView.getMenu().findItem(R.id.bottom_diamond_nv).setChecked(true);
                        break;
                    case 2:
                        nvBottomNavigationView.getMenu().findItem(R.id.bottom_wallet_nv).setChecked(true);
                        break;
                }
            }
        });
    }
}