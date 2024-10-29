package duck.chinh.nhomhdc_appdatxe.fragment_nv1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import duck.chinh.nhomhdc_appdatxe.R;
import duck.chinh.nhomhdc_appdatxe.nv_home;



public class IncomeFragment_nv extends Fragment {

    private View mView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_income_nv,container,false);

        // Tìm Button
        Button buttonBackNv = mView.findViewById(R.id.button_backnv);

        // Thiết lập sự kiện khi nhấn vào Button
        buttonBackNv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang nv_home
                Intent intent = new Intent(getActivity(), nv_home.class);
                startActivity(intent);
            }
        });

        return mView;
    }
}
