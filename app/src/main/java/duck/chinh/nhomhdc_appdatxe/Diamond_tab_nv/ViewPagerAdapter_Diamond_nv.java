package duck.chinh.nhomhdc_appdatxe.Diamond_tab_nv;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter_Diamond_nv extends FragmentStateAdapter {
    public ViewPagerAdapter_Diamond_nv(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new Diamond_tab1_nv();
            case 1:
                return new Diamond_tab2_nv();
            case 2:
                return new Diamond_tab3_nv();
            default:
                return new Diamond_tab1_nv();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Hôm nay";
            case 1:
                return "Sắp tới";
            case 2:
                return "Đã qua";
            default:
                return "Hôm nay";
        }
    }

}
