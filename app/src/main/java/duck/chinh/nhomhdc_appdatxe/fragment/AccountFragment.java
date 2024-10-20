package duck.chinh.nhomhdc_appdatxe.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import duck.chinh.nhomhdc_appdatxe.CaidatActivity;
import duck.chinh.nhomhdc_appdatxe.CapnhatActivity;
import duck.chinh.nhomhdc_appdatxe.NhanxetActivity;
import duck.chinh.nhomhdc_appdatxe.PtthanhtoanActivity;
import duck.chinh.nhomhdc_appdatxe.R;
import duck.chinh.nhomhdc_appdatxe.TtamtrogiupActivity;

public class AccountFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.activity_taikhoan, container, false);

        // Khởi tạo các LinearLayout và thiết lập listener cho chúng
        LinearLayout tk1 = rootView.findViewById(R.id.tk1);
        tk1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CapnhatActivity.class);
                startActivity(intent);
            }
        });

        LinearLayout tk2 = rootView.findViewById(R.id.tk2);
        tk2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NhanxetActivity.class);
                startActivity(intent);
            }
        });

        LinearLayout tk3 = rootView.findViewById(R.id.tk3);
        tk3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PtthanhtoanActivity.class);
                startActivity(intent);
            }
        });

        LinearLayout tk4 = rootView.findViewById(R.id.tk4);
        tk4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TtamtrogiupActivity.class);
                startActivity(intent);
            }
        });

        LinearLayout tk5 = rootView.findViewById(R.id.tk5);
        tk5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CaidatActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
