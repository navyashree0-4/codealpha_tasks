import javax.swing.*;
import java.awt.event.*;

public class ChatBotGUI extends JFrame implements ActionListener {

    JTextArea chatArea;
    JTextField inputField;
    JButton sendButton, clearButton;

    public ChatBotGUI() {

        setTitle("AI ChatBot");
        setSize(600, 500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel heading = new JLabel("AI ChatBot");
        heading.setBounds(250, 10, 100, 30);
        add(heading);

        chatArea = new JTextArea();
        chatArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(chatArea);
        scrollPane.setBounds(20, 50, 540, 300);
        add(scrollPane);

        inputField = new JTextField();
        inputField.setBounds(20, 370, 400, 30);
        add(inputField);

        sendButton = new JButton("Send");
        sendButton.setBounds(430, 370, 100, 30);
        add(sendButton);

        clearButton = new JButton("Clear");
        clearButton.setBounds(230, 420, 100, 30);
        add(clearButton);

        sendButton.addActionListener(this);
        clearButton.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == sendButton) {

            String message = inputField.getText().toLowerCase();

            chatArea.append("You: " + message + "\n");

            String response;

            if (message.contains("hello") || message.contains("hi")) {
                response = "Hello! How can I help you?";
            }

            else if (message.contains("how are you")) {
                response = "I am fine. Thank you!";
            }

            else if (message.contains("your name")) {
                response = "I am CodeAlpha AI ChatBot.";
            }

            else if (message.contains("java")) {
                response = "Java is a popular object-oriented programming language.";
            }

            else if (message.contains("course")) {
                response = "I can help you learn programming concepts.";
            }

            else if (message.contains("bye")) {
                response = "Goodbye! Have a nice day.";
            }

            else {
                response = "Sorry, I don't understand that question.";
            }

            chatArea.append("Bot: " + response + "\n\n");

            inputField.setText("");
        }

        if (e.getSource() == clearButton) {
            chatArea.setText("");
        }
    }

    public static void main(String[] args) {
        new ChatBotGUI();
    }
}