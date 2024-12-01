package com.example.tictactoe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TicTacToe implements ActionListener {
    private JFrame frame;
    private JPanel panel;
    private JButton[] buttons = new JButton[9];
    private boolean xTurn = true;

    public TicTacToe() {
        // Initialize the frame
        frame = new JFrame("Tic-Tac-Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize the panel with a grid layout
        panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Initialize the buttons and add them to the panel
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 40));
            buttons[i].addActionListener(this);
            panel.add(buttons[i]);
        }

        // Add panel to the frame
        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(400, 400);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if (button.getText().isEmpty()) {
            button.setText(xTurn ? "X" : "O");
            button.setEnabled(false);
            xTurn = !xTurn;

            checkForWinner();
        }
    }

    private void checkForWinner() {
        // Check rows
        for (int i = 0; i < 9; i += 3) {
            if (!buttons[i].getText().isEmpty() &&
                buttons[i].getText().equals(buttons[i + 1].getText()) &&
                buttons[i].getText().equals(buttons[i + 2].getText())) {
                showWinner(buttons[i].getText());
                return;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (!buttons[i].getText().isEmpty() &&
                buttons[i].getText().equals(buttons[i + 3].getText()) &&
                buttons[i].getText().equals(buttons[i + 6].getText())) {
                showWinner(buttons[i].getText());
                return;
            }
        }

        // Check diagonals
        if (!buttons[0].getText().isEmpty() &&
            buttons[0].getText().equals(buttons[4].getText()) &&
            buttons[0].getText().equals(buttons[8].getText())) {
            showWinner(buttons[0].getText());
            return;
        }

        if (!buttons[2].getText().isEmpty() &&
            buttons[2].getText().equals(buttons[4].getText()) &&
            buttons[2].getText().equals(buttons[6].getText())) {
            showWinner(buttons[2].getText());
            return;
        }

        // Check for tie
        boolean tie = true;
        for (JButton button : buttons) {
            if (button.getText().isEmpty()) {
                tie = false;
                break;
            }
        }

        if (tie) {
            JOptionPane.showMessageDialog(frame, "It's a tie!");
            resetGame();
        }
    }

    private void showWinner(String winner) {
        JOptionPane.showMessageDialog(frame, winner + " wins!");
        resetGame();
    }

    private void resetGame() {
        for (JButton button : buttons) {
            button.setText("");
            button.setEnabled(true);
        }
        xTurn = true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TicTacToe::new);
    }
}
