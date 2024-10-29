package duck.chinh.nhomhdc_appdatxe;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import duck.chinh.nhomhdc_appdatxe.fragment_nv1.DiamondFragment_nv;
import duck.chinh.nhomhdc_appdatxe.fragment_nv1.IncomeFragment_nv;
import duck.chinh.nhomhdc_appdatxe.fragment_nv1.WalletFragment_nv;


public class MyViewPagerAdapter_nv extends FragmentStateAdapter {
    public MyViewPagerAdapter_nv(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new IncomeFragment_nv();
            case 1:
                return new DiamondFragment_nv();
            case 2:
                return new WalletFragment_nv();
            default:
                return new IncomeFragment_nv();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
