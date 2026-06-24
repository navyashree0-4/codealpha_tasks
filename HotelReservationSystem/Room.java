public class Room {
    int roomNo;
    String roomType;
    boolean available;

    public Room(int roomNo, String roomType) {
        this.roomNo = roomNo;
        this.roomType = roomType;
        this.available = true;
    }
}