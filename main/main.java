package main;

import data.DBConnection;
import ui.LoginScreen;
import ui.MainFrame;  // MainFrame을 import

import java.sql.Connection;
import java.sql.SQLException;

public class main {
    public static void main(String[] args) {

        try (Connection connection = DBConnection.getConnection()) {
            System.out.println("데이터베이스 연결 성공!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        javax.swing.SwingUtilities.invokeLater(() -> {
            new MainFrame();  // MainFrame 호출
        });
    }
}