package duck.chinh.nhomhdc_appdatxe.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import duck.chinh.nhomhdc_appdatxe.Booking;
import duck.chinh.nhomhdc_appdatxe.BookingAdapter;
import duck.chinh.nhomhdc_appdatxe.R;

public class HistoryFragment extends Fragment {

    private RecyclerView recyclerView;
    private BookingAdapter bookingAdapter;
    private List<Booking> bookingList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_history, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerView_bookings);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        bookingList = new ArrayList<>();
        bookingAdapter = new BookingAdapter(bookingList);
        recyclerView.setAdapter(bookingAdapter);

        // Kết nối Firebase và lấy dữ liệu
        DatabaseReference databaseReference = FirebaseDatabase.getInstance()
                .getReference("Bookings");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                bookingList.clear();
                for (DataSnapshot data : snapshot.getChildren()) {
                    Booking booking = data.getValue(Booking.class);
                    if (booking != null) {
                        bookingList.add(booking);
                    }
                }
                bookingAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Xử lý lỗi nếu cần
            }
        });

        return rootView;
    }
}
