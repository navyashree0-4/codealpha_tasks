import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class HotelReservationSystem extends JFrame implements ActionListener {

    JLabel lblName, lblRoomType;
    JTextField txtName;

    JComboBox<String> roomBox;

    JButton btnSearch, btnBook, btnView, btnCancel;

    JTextArea area;

    ArrayList<Room> rooms = new ArrayList<>();
    ArrayList<Booking> bookings = new ArrayList<>();

    int bookingCounter = 1;

    public HotelReservationSystem() {

        setTitle("Hotel Reservation System");
        setSize(700, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        rooms.add(new Room(101, "Standard"));
        rooms.add(new Room(102, "Standard"));
        rooms.add(new Room(201, "Deluxe"));
        rooms.add(new Room(202, "Deluxe"));
        rooms.add(new Room(301, "Suite"));

        lblName = new JLabel("Customer Name:");
        lblName.setBounds(30, 30, 120, 30);
        add(lblName);

        txtName = new JTextField();
        txtName.setBounds(160, 30, 200, 30);
        add(txtName);

        lblRoomType = new JLabel("Room Type:");
        lblRoomType.setBounds(30, 80, 120, 30);
        add(lblRoomType);

        roomBox = new JComboBox<>();
        roomBox.addItem("Standard");
        roomBox.addItem("Deluxe");
        roomBox.addItem("Suite");
        roomBox.setBounds(160, 80, 200, 30);
        add(roomBox);

        btnSearch = new JButton("Search Room");
        btnSearch.setBounds(30, 140, 140, 35);
        add(btnSearch);

        btnBook = new JButton("Book Room");
        btnBook.setBounds(190, 140, 140, 35);
        add(btnBook);

        btnView = new JButton("View Bookings");
        btnView.setBounds(350, 140, 150, 35);
        add(btnView);

        btnCancel = new JButton("Cancel Booking");
        btnCancel.setBounds(520, 140, 150, 35);
        add(btnCancel);

        area = new JTextArea();
        area.setEditable(false);

        JScrollPane sp = new JScrollPane(area);
        sp.setBounds(30, 220, 620, 300);
        add(sp);

        btnSearch.addActionListener(this);
        btnBook.addActionListener(this);
        btnView.addActionListener(this);
        btnCancel.addActionListener(this);

        loadBookings();

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnSearch) {

            String type = roomBox.getSelectedItem().toString();

            area.setText("Available Rooms\n\n");

            for (Room r : rooms) {

                if (r.roomType.equals(type) && r.available) {
                    area.append("Room No: " + r.roomNo +
                            " (" + r.roomType + ")\n");
                }
            }
        }

        if (e.getSource() == btnBook) {

            String name = txtName.getText();

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Enter Customer Name");
                return;
            }

            String type = roomBox.getSelectedItem().toString();

            Room selectedRoom = null;

            for (Room r : rooms) {

                if (r.roomType.equals(type) && r.available) {
                    selectedRoom = r;
                    break;
                }
            }

            if (selectedRoom == null) {

                JOptionPane.showMessageDialog(this,
                        "No Rooms Available");
                return;
            }

            double amount = 0;

            if (type.equals("Standard"))
                amount = 1000;

            else if (type.equals("Deluxe"))
                amount = 2000;

            else if (type.equals("Suite"))
                amount = 3500;

            int option = JOptionPane.showConfirmDialog(
                    this,
                    "Room Charge = ₹" + amount +
                            "\nProceed Payment?",
                    "Payment",
                    JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {

                selectedRoom.available = false;

                Booking b = new Booking(
                        bookingCounter++,
                        name,
                        selectedRoom.roomNo,
                        type,
                        amount);

                bookings.add(b);

                saveBooking(b);

                JOptionPane.showMessageDialog(this,
                        "Payment Successful!\nRoom Booked.");

                txtName.setText("");
            }
        }

        if (e.getSource() == btnView) {

            area.setText("BOOKING DETAILS\n\n");

            for (Booking b : bookings) {

                area.append(
                        "Booking ID : " + b.bookingId +
                                "\nCustomer : " + b.customerName +
                                "\nRoom No : " + b.roomNo +
                                "\nRoom Type : " + b.roomType +
                                "\nAmount : ₹" + b.amount +
                                "\n------------------------\n");
            }
        }

        if (e.getSource() == btnCancel) {

            String idStr = JOptionPane.showInputDialog(
                    this,
                    "Enter Booking ID");

            if (idStr == null)
                return;

            int id = Integer.parseInt(idStr);

            Booking removeBooking = null;

            for (Booking b : bookings) {

                if (b.bookingId == id) {

                    removeBooking = b;

                    for (Room r : rooms) {

                        if (r.roomNo == b.roomNo) {
                            r.available = true;
                        }
                    }
                }
            }

            if (removeBooking != null) {

                bookings.remove(removeBooking);

                JOptionPane.showMessageDialog(this,
                        "Booking Cancelled");
            } else {

                JOptionPane.showMessageDialog(this,
                        "Booking ID Not Found");
            }
        }
    }

    private void saveBooking(Booking b) {

        try {

            FileWriter fw =
                    new FileWriter("bookings.txt", true);

            fw.write(
                    b.bookingId + "," +
                            b.customerName + "," +
                            b.roomNo + "," +
                            b.roomType + "," +
                            b.amount + "\n");

            fw.close();

        } catch (Exception e) {

            System.out.println(e);
        }
    }

    private void loadBookings() {

        try {

            BufferedReader br =
                    new BufferedReader(
                            new FileReader("bookings.txt"));

            String line;

            while ((line = br.readLine()) != null) {

                String data[] = line.split(",");

                Booking b = new Booking(
                        Integer.parseInt(data[0]),
                        data[1],
                        Integer.parseInt(data[2]),
                        data[3],
                        Double.parseDouble(data[4]));

                bookings.add(b);
            }

            br.close();

        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {

        new HotelReservationSystem();
    }
}