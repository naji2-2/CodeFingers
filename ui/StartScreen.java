package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class StartScreen extends JFrame {

    private BufferedImage backgroundImage;

    public StartScreen() {
        // 배경 이미지 로드
        try {
            backgroundImage = ImageIO.read(new File("C:/CodeFingers_JAVA_Project/CodeFingers/images/StartScreen_n.png"));
        } catch (IOException e) { // IOException을 catch하여 처리
            e.printStackTrace();
        }

        // 기본 설정
        setLayout(new BorderLayout());
        setTitle("CodeFingers");
        setSize(1280, 720); // 화면 크기 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임 닫기 설정
        setLocationRelativeTo(null); // 화면 중앙

        // 패널 생성 및 배경 이미지 설정
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); // 이미지 그리기
                }
            }
        };

        panel.setLayout(null); // 절대 위치 배치 사용

        // CodeFingers
        JLabel titleLabel = new JLabel("CodeFingers", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 170));
        titleLabel.setForeground(Color.white); // 글씨 색상
        titleLabel.setBounds(106, 180, 1100, 280); // 레이블 위치와 크기 설정
        panel.add(titleLabel);

        // 타자연습
        JLabel minLabel = new JLabel("타자연습", JLabel.CENTER);
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("C:/CodeFingers_JAVA_Project/CodeFingers/fonts/JejuGothic.ttf"));
            minLabel.setFont(customFont.deriveFont(30f)); // 폰트 크기 조정
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            minLabel.setFont(new Font("Arial", Font.BOLD, 30)); // 대체 폰트
        }

        minLabel.setForeground(Color.white); // 글씨 색상
        minLabel.setBounds(855, 320, 500, 180); // 레이블 위치와 크기 설정
        panel.add(minLabel);

        // 시작 버튼
        JButton start_button = new JButton(new ImageIcon("C:/CodeFingers_JAVA_Project/CodeFingers/images/StartButton.png"));
        start_button.setBounds(391, 500, 500, 150); // 버튼 위치와 크기 설정
        start_button.setBorderPainted(false);
        start_button.setFocusPainted(false);
        start_button.setContentAreaFilled(false);

        start_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginScreen(); // LoginScreen 창 열기
                dispose(); // 현재 StartScreen 창 닫기
                System.out.println("Start button clicked");
            }
        });

        // 패널에 버튼 추가
        panel.add(start_button);

        // 프레임에 패널 추가
        this.add(panel);
        this.setVisible(true); // 프레임 보이게 하기
    }
}
