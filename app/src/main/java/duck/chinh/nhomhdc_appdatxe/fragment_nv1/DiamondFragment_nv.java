package duck.chinh.nhomhdc_appdatxe.fragment_nv1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;


import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import duck.chinh.nhomhdc_appdatxe.Diamond_tab_nv.ViewPagerAdapter_Diamond_nv;
import duck.chinh.nhomhdc_appdatxe.R;

public class DiamondFragment_nv extends Fragment  {

    private TabLayout tabLayout;
    private View mView;
    private ViewPager2 viewPager2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_diamond_nv,container,false);

        tabLayout = mView.findViewById(R.id.tablayout_nv);
        viewPager2 = mView.findViewById(R.id.Diamond_tab_nv);
        ViewPagerAdapter_Diamond_nv adapter2 = new ViewPagerAdapter_Diamond_nv(getActivity());
        viewPager2.setAdapter(adapter2);

        new TabLayoutMediator(tabLayout, viewPager2,
                (tab, position) -> tab.setText(adapter2.getPageTitle(position)) // Thiết lập tiêu đề cho từng tab
        ).attach();

        return mView;
    }
}
