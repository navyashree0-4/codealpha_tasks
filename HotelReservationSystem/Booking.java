public class Booking {
    int bookingId;
    String customerName;
    int roomNo;
    String roomType;
    double amount;

    public Booking(int bookingId, String customerName,
                   int roomNo, String roomType, double amount) {

        this.bookingId = bookingId;
        this.customerName = customerName;
        this.roomNo = roomNo;
        this.roomType = roomType;
        this.amount = amount;
    }
}