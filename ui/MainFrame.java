package ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{

    private CardLayout cardLayout;
    private JPanel mainPanel;

    public MainFrame() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // StartScreen, JoinScreen 등 필요한 화면을 추가
        mainPanel.add(new StartScreen(this), "StartScreen");
        mainPanel.add(new LoginScreen(this), "LoginScreen");

        add(mainPanel);
        cardLayout.show(mainPanel, "StartScreen");  // 초기 화면 설정

        setTitle("CodeFingers");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void switchTo(String screenName) {
        cardLayout.show(mainPanel, screenName);
    }

}