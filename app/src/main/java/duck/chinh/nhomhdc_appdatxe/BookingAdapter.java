package duck.chinh.nhomhdc_appdatxe;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder> {

    // Danh sách Booking
    private List<Booking> bookingList;

    // Constructor
    public BookingAdapter(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    // Tạo ViewHolder (gọi 1 lần khi cần tạo View mới)
    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate layout item_booking.xml để tạo View
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_booking, parent, false);
        return new BookingViewHolder(view); // Trả về ViewHolder
    }

    // Gắn dữ liệu từ danh sách vào View (cho từng item)
    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
        // Lấy dữ liệu Booking tại vị trí position
        Booking booking = bookingList.get(position);

        // Set dữ liệu vào các TextView
        holder.textStartLocation.setText("Start: " + booking.getStartLocation());
        holder.textEndLocation.setText("End: " + booking.getEndLocation());
    }

    // Trả về số lượng item trong danh sách
    @Override
    public int getItemCount() {
        return bookingList.size();
    }

    // ViewHolder (lớp giúp quản lý View của từng item)
    public static class BookingViewHolder extends RecyclerView.ViewHolder {
        TextView textStartLocation, textEndLocation;

        public BookingViewHolder(@NonNull View itemView) {
            super(itemView);

            // Ánh xạ các View trong item_booking.xml
            textStartLocation = itemView.findViewById(R.id.textStartLocation);
            textEndLocation = itemView.findViewById(R.id.textEndLocation);
        }
    }
}
