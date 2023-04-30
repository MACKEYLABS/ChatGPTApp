package org.example;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class App extends JFrame implements ActionListener {

    private final ChatPanel chatPanel;
    private final GPTClient gptClient; // Add this line

    public App() {
        chatPanel = new ChatPanel(this);
        gptClient = new GPTClient(); // Add this line
        setTitle("Mackey GPT");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(chatPanel.getMainPanel());
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //if (e.getSource() == chatPanel.getInputField()) {
            if (e.getSource() == chatPanel.getInputField() || e.getSource() == chatPanel.getSendButton()) {
            String message = chatPanel.getInputField().getText();
            chatPanel.addUserMessage(message);

            // Add the following code block
            gptClient.sendMessage(message, responseMessage -> {
                SwingUtilities.invokeLater(() -> {
                    chatPanel.addGPTMessage(responseMessage);
                });
            });

            chatPanel.getInputField().setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new App());
    }
}
