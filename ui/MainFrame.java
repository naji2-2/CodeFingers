package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;

    public MainFrame() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // StartScreen, JoinScreen 등 필요한 화면을 추가
        mainPanel.add(new StartScreen(this), "StartScreen");
        mainPanel.add(new LoginScreen(this), "LoginScreen");
        mainPanel.add(new JoinScreen(this), "JoinScreen");
        mainPanel.add(new ChooseGameScreen(this), "ChooseGameScreen");

        add(mainPanel);
        cardLayout.show(mainPanel, "StartScreen");  // 초기 화면 설정

        setTitle("CodeFingers");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 전체 화면 설정
        setExtendedState(JFrame.MAXIMIZED_BOTH); // 창을 최대화
        setUndecorated(true); // 창 테두리 제거
        setVisible(true);

        // ESC 키 이벤트 처리
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) { // ESC 키 감지
                    exitFullscreen();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        // 포커스 설정 (KeyListener가 동작하려면 포커스가 필요)
        setFocusable(true);
        requestFocusInWindow();
    }

    private void exitFullscreen() {
        dispose(); // 기존 창을 닫음
        setUndecorated(false); // 테두리를 복원
        setExtendedState(JFrame.NORMAL); // 창 크기를 기본으로 설정
        setVisible(true); // 다시 표시
    }

    public void switchTo(String screenName) {
        cardLayout.show(mainPanel, screenName);
    }
}
