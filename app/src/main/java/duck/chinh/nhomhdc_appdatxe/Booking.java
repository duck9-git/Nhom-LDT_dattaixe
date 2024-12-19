package duck.chinh.nhomhdc_appdatxe;

public class Booking {
    private String startLocation;
    private String endLocation;

    public Booking() {
        // Constructor mặc định (yêu cầu của Firebase)
    }

    public Booking(String startLocation, String endLocation) {
        this.startLocation = startLocation;
        this.endLocation = endLocation;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public String getEndLocation() {
        return endLocation;
    }
}

