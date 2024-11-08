package main;

import ui.MainFrame;  // MainFrame을 import

public class main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new MainFrame();  // MainFrame 호출
        });
    }
}