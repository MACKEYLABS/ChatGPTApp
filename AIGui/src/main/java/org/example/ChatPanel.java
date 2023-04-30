package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ChatPanel {
    private JPanel mainPanel;
    private JTextField inputField;
    private JPanel chatPanel;
    private JButton sendButton;

    public ChatPanel(ActionListener listener) {
        // ... (same code as in the constructor of the App class for setting up the UI)


        // Set up dark theme
        UIManager.put("TextField.background", new Color(50, 50, 50));
        UIManager.put("TextField.foreground", Color.WHITE);
        UIManager.put("TextField.caretForeground", Color.WHITE);
        UIManager.put("Button.background", new Color(50, 50, 50));
        UIManager.put("Button.foreground", Color.WHITE);

        // Set up input field
        inputField = new JTextField(30);
        inputField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        inputField.setMargin(new Insets(5, 5, 5, 5));

        // Set up chat panel
        chatPanel = new JPanel();
        chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));
        chatPanel.setBackground(new Color(50, 50, 50));

        // Set up scroll pane for chat area
        JScrollPane scrollPane = new JScrollPane(chatPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Add this line
        scrollPane.setBackground(new Color(50, 50, 50));
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setPreferredSize(new Dimension(500, 500));

        // Set up send button
        sendButton = new JButton("\uD83D\uDCE9");
        sendButton.setFont(new Font("SansSerif", Font.PLAIN, 20));
        sendButton.addActionListener(listener);
        sendButton.setFocusPainted(false);
        sendButton.setBackground(new Color(66, 133, 244));
        sendButton.setForeground(Color.WHITE);
        sendButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 1), BorderFactory.createEmptyBorder(5, 10, 5, 10)));

        // Set up panel for input field and send button
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Set up main panel
        mainPanel = new JPanel(); // <- Corrected line
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(inputPanel, BorderLayout.SOUTH);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputField.addActionListener(listener);
    }
    public JPanel getMainPanel() {
        return mainPanel;
    }
    public JButton getSendButton() {
        return sendButton;
    }
    public JTextField getInputField() {
        return inputField;
    }

    public void addUserMessage(String message) {
        JLabel userMessage = new JLabel("<html><body style='width: 250px'>" + "User: " + message + "</body></html>");
        userMessage.setFont(new Font("SansSerif", Font.PLAIN, 16));
        userMessage.setOpaque(true);
        userMessage.setBackground(new Color(100, 100, 100));
        userMessage.setForeground(Color.WHITE); // Fixed line
        userMessage.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 1), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        userMessage.setAlignmentX(Component.LEFT_ALIGNMENT);
        chatPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        chatPanel.add(userMessage);
        chatPanel.revalidate();
    }

    public void addGPTMessage(String message) {
        JLabel gptMessage = new JLabel("<html><body style='width: 350px'>" + "Chat GPT: " + message + "</body></html>"); // Changed width from 250px to 350px
        gptMessage.setFont(new Font("SansSerif", Font.PLAIN, 16));
        gptMessage.setOpaque(true);
        gptMessage.setBackground(new Color(66, 133, 244));
        gptMessage.setForeground(Color.WHITE);
        gptMessage.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 1), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        gptMessage.setAlignmentX(Component.LEFT_ALIGNMENT);

        chatPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        chatPanel.add(gptMessage);
        chatPanel.revalidate();
    }
}