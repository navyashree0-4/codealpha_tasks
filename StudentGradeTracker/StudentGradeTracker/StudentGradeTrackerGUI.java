import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

class Student {
    String name;
    int marks;

    Student(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }
}

public class StudentGradeTrackerGUI extends JFrame implements ActionListener {

    JLabel lblCount, lblName, lblMarks;

    JTextField txtCount, txtName, txtMarks;

    JButton btnSet, btnAdd, btnReport, btnClear;

    JTextArea outputArea;

    ArrayList<Student> students = new ArrayList<>();

    int totalStudents = 0;

    public StudentGradeTrackerGUI() {

        setTitle("Student Grade Tracker");
        setSize(650, 550);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Number of Students
        lblCount = new JLabel("No. of Students:");
        lblCount.setBounds(30, 20, 120, 30);
        add(lblCount);

        txtCount = new JTextField();
        txtCount.setBounds(160, 20, 150, 30);
        add(txtCount);

        btnSet = new JButton("Set Students");
        btnSet.setBounds(330, 20, 130, 30);
        add(btnSet);

        // Student Name
        lblName = new JLabel("Student Name:");
        lblName.setBounds(30, 80, 120, 30);
        add(lblName);

        txtName = new JTextField();
        txtName.setBounds(160, 80, 150, 30);
        add(txtName);

        // Marks
        lblMarks = new JLabel("Marks:");
        lblMarks.setBounds(30, 130, 120, 30);
        add(lblMarks);

        txtMarks = new JTextField();
        txtMarks.setBounds(160, 130, 150, 30);
        add(txtMarks);

        // Buttons
        btnAdd = new JButton("Add Student");
        btnAdd.setBounds(30, 190, 130, 35);
        add(btnAdd);

        btnReport = new JButton("Show Report");
        btnReport.setBounds(180, 190, 130, 35);
        add(btnReport);

        btnClear = new JButton("Clear");
        btnClear.setBounds(330, 190, 130, 35);
        add(btnClear);

        // Output Area
        outputArea = new JTextArea();
        outputArea.setEditable(false);

        JScrollPane sp = new JScrollPane(outputArea);
        sp.setBounds(30, 250, 570, 220);
        add(sp);

        // Events
        btnSet.addActionListener(this);
        btnAdd.addActionListener(this);
        btnReport.addActionListener(this);
        btnClear.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        // Set Number of Students
        if (e.getSource() == btnSet) {

            try {

                totalStudents = Integer.parseInt(txtCount.getText());

                if (totalStudents <= 0) {
                    JOptionPane.showMessageDialog(this,
                            "Enter a valid number of students.");
                    return;
                }

                JOptionPane.showMessageDialog(this,
                        "Now enter details for " + totalStudents + " students.");

                txtCount.setEditable(false);
                btnSet.setEnabled(false);

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(this,
                        "Please enter a valid number.");

            }
        }

        // Add Student
        if (e.getSource() == btnAdd) {

            if (totalStudents == 0) {
                JOptionPane.showMessageDialog(this,
                        "Please set the number of students first.");
                return;
            }

            if (students.size() >= totalStudents) {
                JOptionPane.showMessageDialog(this,
                        "All students have already been added.");
                return;
            }

            try {

                String name = txtName.getText();
                int marks = Integer.parseInt(txtMarks.getText());

                students.add(new Student(name, marks));

                JOptionPane.showMessageDialog(this,
                        "Student Added Successfully!");

                txtName.setText("");
                txtMarks.setText("");

                if (students.size() == totalStudents) {

                    JOptionPane.showMessageDialog(this,
                            "All students entered successfully.\nClick Show Report.");

                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(this,
                        "Please enter valid marks.");

            }
        }

        // Show Report
        if (e.getSource() == btnReport) {

            if (students.isEmpty()) {

                outputArea.setText("No student records available.");
                return;

            }

            int total = 0;
            int highest = students.get(0).marks;
            int lowest = students.get(0).marks;

            String report = "========== STUDENT SUMMARY REPORT ==========\n\n";

            for (Student s : students) {

                report += "Name : " + s.name +
                        "\tMarks : " + s.marks + "\n";

                total += s.marks;

                if (s.marks > highest)
                    highest = s.marks;

                if (s.marks < lowest)
                    lowest = s.marks;
            }

            double average = (double) total / students.size();

            report += "\n--------------------------------------------\n";
            report += "Total Students : " + students.size() + "\n";
            report += "Average Marks  : " + String.format("%.2f", average) + "\n";
            report += "Highest Marks  : " + highest + "\n";
            report += "Lowest Marks   : " + lowest + "\n";

            outputArea.setText(report);
        }

        // Clear
        if (e.getSource() == btnClear) {

            txtCount.setText("");
            txtName.setText("");
            txtMarks.setText("");

            outputArea.setText("");

            students.clear();

            totalStudents = 0;

            txtCount.setEditable(true);
            btnSet.setEnabled(true);
        }
    }

    public static void main(String[] args) {

        new StudentGradeTrackerGUI();

    }
}