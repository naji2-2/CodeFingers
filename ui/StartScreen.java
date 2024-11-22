package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class StartScreen extends JPanel {

    private MainFrame mainFrame;
    private BufferedImage backgroundImage;

    public StartScreen(MainFrame mainFrame) {

        this.mainFrame = mainFrame;

        // 배경 이미지 로드
        try {
            backgroundImage = ImageIO.read(new File("C:/CodeFingers_JAVA_Project/CodeFingers/images/Background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setLayout(null); // 절대 위치 배치 사용

        // CodeFingers 제목 설정
        JLabel titleLabel = new JLabel("CodeFingers", JLabel.CENTER);
        titleLabel.setFont(new Font("Host Grotesk", Font.BOLD, 200));
        titleLabel.setForeground(Color.white);
        titleLabel.setBounds(100, 250, 1250, 261);
        add(titleLabel);

        // 타자연습 레이블 설정
        JLabel minLabel = new JLabel("타자연습", JLabel.CENTER);
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("C:/CodeFingers_JAVA_Project/CodeFingers/fonts/JejuGothic.ttf"));
            minLabel.setFont(customFont.deriveFont(30f));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            minLabel.setFont(new Font("Host Grotesk", Font.BOLD, 30)); // 대체 폰트
        }
        minLabel.setForeground(Color.white);
        minLabel.setBounds(1170, 465, 178, 50);
        add(minLabel);

        // 시작 버튼 설정
        JButton startButton = new JButton(new ImageIcon("C:/CodeFingers_JAVA_Project/CodeFingers/images/Start_Button.png"));
        startButton.setBounds(383, 610, 675, 203);
        startButton.setBorderPainted(false);
        startButton.setFocusPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Start button clicked");
                mainFrame.switchTo("LoginScreen"); // 화면 전환
            }
        });
        add(startButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
