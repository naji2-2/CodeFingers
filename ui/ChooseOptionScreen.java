package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ChooseOptionScreen extends JPanel {

    private MainFrame mainFrame;

    private BufferedImage backgroundImage;
    private BufferedImage gamebackgroundImage;
    private BufferedImage gobackbuttonImage;
    private BufferedImage record;
    private BufferedImage wordpractice;
    private BufferedImage sentencepractice;

    public ChooseOptionScreen(MainFrame mainFrame) {

        this.mainFrame = mainFrame;
        setLayout(null);
        setPreferredSize(new Dimension(1280, 720));

        // 이미지 로드
        try {
            backgroundImage = ImageIO.read(getClass().getResource("/images/Background.png"));  // 상대 경로로 수정
            gamebackgroundImage = ImageIO.read(getClass().getResource("/images/Game_Background.png"));
            gobackbuttonImage = ImageIO.read(getClass().getResource("/images/Goback_Button.png"));
            record = ImageIO.read(getClass().getResource("/images/Option_Button.png"));
            wordpractice = ImageIO.read(getClass().getResource("/images/Option_Button.png"));
            sentencepractice = ImageIO.read(getClass().getResource("/images/Option_Button.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JLabel logoLabel = createLabel("CodeFingers", 57, 22, 50, Color.WHITE);
        logoLabel.setBounds(57, 22, 350, 75);
        JLabel recordtitleLabel = createLabel("기록 확인", 130, 183, 50, new Color(41, 105, 195));
        JLabel practicetitleLabel = createLabel("타자 연습", 130, 522, 50, new Color(41, 105, 195));

        JButton recordButton = createButton("기록함", 65, 334, 45, Color.WHITE);
        JButton wordpracticeButton = createButton("<html>단어<br>연습</html>", 185, 635, 45, Color.WHITE);
        wordpracticeButton.setBounds(185, 635, 120, 120);
        JButton sentencepracticeButton = createButton("<html>문장<br>연습</html>", 465, 635, 45, Color.WHITE);
        sentencepracticeButton.setBounds(465, 635, 120, 120);
        JButton gobackButton = createButton("돌아가기", 1205, 40, 30, Color.WHITE);
        gobackButton.setBounds(1205, 40, 131, 35);

        add(logoLabel);
        add(recordtitleLabel);
        add(recordButton);
        add(practicetitleLabel);
        add(wordpracticeButton);
        add(sentencepracticeButton);
        add(gobackButton);

        // 기록함 버튼을 눌렀을 때
        recordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO : 기록함 화면으로 넘어가는 코드 구현
                System.out.println("Record Button clicked");
            }
        });

        // 단어 연습 버튼을 눌렀을 때
        wordpracticeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO : 단어 연습 화면으로 넘어가는 코드 구현
                mainFrame.switchTo("WordPracticeScreen");
                System.out.println("WordPractice Button clicked");
            }
        });

        // 문장 연습 버튼을 눌렀을 때
        sentencepracticeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO : 문장 연습 화면으로 넘어가는 코드 구현
                System.out.println("SentencePractice Button clicked");
            }
        });

        // 되돌아가기 버튼을 누르면 시작 화면으로 전환
        gobackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.switchTo("StartScreen");
                System.out.println("Goback Button clicked");
            }
        });

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
        if (gamebackgroundImage != null) {
            g.drawImage(gamebackgroundImage, 46, 113, 1350, 750, this);
        }
        if (gobackbuttonImage != null) {
            g.drawImage(gobackbuttonImage, 1160, 28, 215, 63, this);
        }
        if (record != null) {
            g.drawImage(record, 130, 271, 238, 180, this);
        }
        if (wordpractice != null) {
            g.drawImage(wordpractice, 130, 609, 238, 180, this);
        }
        if (sentencepractice != null){
            g.drawImage(sentencepractice, 406, 609, 238, 180, this);
        }
    }

    private JLabel createLabel(String text, int x, int y, int fontSize, Color color) {
        JLabel label = new JLabel(text);
        label.setForeground(color);
        label.setFont(new Font("SansSerif", Font.BOLD, fontSize));
        label.setBounds(x, y, 220, 50);
        return label;
    }

    private JButton createButton(String text, int x, int y, int fontSize, Color color) {
        JButton button = new JButton(text);
        button.setForeground(color);
        button.setBounds(x, y, 350, 50);
        button.setBackground(new Color(0, 0, 0, 0)); // 배경 투명
        button.setFont(new Font("SansSerif", Font.BOLD, fontSize));
        button.setBorder(null);

        // 호버 및 포커스 효과 제거
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setRolloverEnabled(false);
        return button;
    }

}
