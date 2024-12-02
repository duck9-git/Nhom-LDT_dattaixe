package duck.chinh.nhomhdc_appdatxe;

public class Request {
    private String customerName;
    private String destination;
    private String status;

    // Constructor rỗng để Firebase có thể khởi tạo đối tượng
    public Request() {
    }

    // Constructor đầy đủ
    public Request(String customerName, String destination, String status) {
        this.customerName = customerName;
        this.destination = destination;
        this.status = status;
    }

    // Getter và Setter
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

