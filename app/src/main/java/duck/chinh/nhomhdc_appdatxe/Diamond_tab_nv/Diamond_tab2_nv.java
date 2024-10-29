package duck.chinh.nhomhdc_appdatxe.Diamond_tab_nv;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import duck.chinh.nhomhdc_appdatxe.R;


public class Diamond_tab2_nv extends Fragment {

    private View mView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_tab2_diamond_nv,container,false);

        return mView;
    }
}
