package duck.chinh.nhomhdc_appdatxe.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import duck.chinh.nhomhdc_appdatxe.R;


public class WalletFragment extends Fragment {

    private View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the fragment's layout
        rootView = inflater.inflate(R.layout.activity_wallet, container, false);

        // Initialize views or set up listeners here

        return rootView;
    }
}
