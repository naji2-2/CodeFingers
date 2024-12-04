package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class WordPracticeScreen extends JPanel {

    private MainFrame mainFrame;

    private BufferedImage backgroundImage;
    private BufferedImage gamebackgroundImage;
    private BufferedImage wordbackgroundImage;
    private BufferedImage gobackbuttonImage;
    private BufferedImage inputImage;
    private BufferedImage[] keyboardImage1;
    private BufferedImage[] keyboardImage2;
    private BufferedImage[] keyboardImage3;
    private BufferedImage[] keyboardImage4;
    private BufferedImage[] keyboardImage5;

    public WordPracticeScreen(MainFrame mainFrame) {

        this.mainFrame = mainFrame;
        setLayout(null);
        setPreferredSize(new Dimension(1280, 720));

        // 키보드 이미지 배열 초기화
        keyboardImage1 = new BufferedImage[14];
        keyboardImage2 = new BufferedImage[14];
        keyboardImage3 = new BufferedImage[13];
        keyboardImage4 = new BufferedImage[12];
        keyboardImage5 = new BufferedImage[5];

        // 이미지 로드
        try {
            backgroundImage = ImageIO.read(getClass().getResource("/images/Background.png"));  // 상대 경로로 수정
            gamebackgroundImage = ImageIO.read(getClass().getResource("/images/Game_Background.png"));
            wordbackgroundImage = ImageIO.read(getClass().getResource("/images/Word_Background.png"));
            gobackbuttonImage = ImageIO.read(getClass().getResource("/images/Goback_Button.png"));
            inputImage = ImageIO.read(getClass().getResource("/images/Input_Point.png"));
            // 키보드 1번째 줄
            keyboardImage1[0] = ImageIO.read(getClass().getResource("/images/keyboard/~ `.png"));
            keyboardImage1[1] = ImageIO.read(getClass().getResource("/images/keyboard/! 1.png"));
            keyboardImage1[2] = ImageIO.read(getClass().getResource("/images/keyboard/@ 2.png"));
            keyboardImage1[3] = ImageIO.read(getClass().getResource("/images/keyboard/# 3.png"));
            keyboardImage1[4] = ImageIO.read(getClass().getResource("/images/keyboard/$ 4.png"));
            keyboardImage1[5] = ImageIO.read(getClass().getResource("/images/keyboard/% 5.png"));
            keyboardImage1[6] = ImageIO.read(getClass().getResource("/images/keyboard/^ 6.png"));
            keyboardImage1[7] = ImageIO.read(getClass().getResource("/images/keyboard/& 7.png"));
            keyboardImage1[8] = ImageIO.read(getClass().getResource("/images/keyboard/_ 8.png"));
            keyboardImage1[9] = ImageIO.read(getClass().getResource("/images/keyboard/( 9.png"));
            keyboardImage1[10] = ImageIO.read(getClass().getResource("/images/keyboard/) 0.png"));
            keyboardImage1[11] = ImageIO.read(getClass().getResource("/images/keyboard/_ -.png"));
            keyboardImage1[12] = ImageIO.read(getClass().getResource("/images/keyboard/+ =.png"));
            keyboardImage1[13] = ImageIO.read(getClass().getResource("/images/keyboard/Backspase.png"));
            // 키보드 2번째 줄
            keyboardImage2[0] = ImageIO.read(getClass().getResource("/images/keyboard/tab.png"));
            keyboardImage2[1] = ImageIO.read(getClass().getResource("/images/keyboard/Q.png"));
            keyboardImage2[2] = ImageIO.read(getClass().getResource("/images/keyboard/W.png"));
            keyboardImage2[3] = ImageIO.read(getClass().getResource("/images/keyboard/E.png"));
            keyboardImage2[4] = ImageIO.read(getClass().getResource("/images/keyboard/R.png"));
            keyboardImage2[5] = ImageIO.read(getClass().getResource("/images/keyboard/T.png"));
            keyboardImage2[6] = ImageIO.read(getClass().getResource("/images/keyboard/Y.png"));
            keyboardImage2[7] = ImageIO.read(getClass().getResource("/images/keyboard/U.png"));
            keyboardImage2[8] = ImageIO.read(getClass().getResource("/images/keyboard/I.png"));
            keyboardImage2[9] = ImageIO.read(getClass().getResource("/images/keyboard/O.png"));
            keyboardImage2[10] = ImageIO.read(getClass().getResource("/images/keyboard/P.png"));
            keyboardImage2[11] = ImageIO.read(getClass().getResource("/images/keyboard/{ [.png"));
            keyboardImage2[12] = ImageIO.read(getClass().getResource("/images/keyboard/} ].png"));
            keyboardImage2[13] = ImageIO.read(getClass().getResource("/images/keyboard/_.png"));
            // 키보드 3번째 줄
            keyboardImage3[0] = ImageIO.read(getClass().getResource("/images/keyboard/Caps Lock.png"));
            keyboardImage3[1] = ImageIO.read(getClass().getResource("/images/keyboard/A.png"));
            keyboardImage3[2] = ImageIO.read(getClass().getResource("/images/keyboard/S.png"));
            keyboardImage3[3] = ImageIO.read(getClass().getResource("/images/keyboard/D.png"));
            keyboardImage3[4] = ImageIO.read(getClass().getResource("/images/keyboard/F.png"));
            keyboardImage3[5] = ImageIO.read(getClass().getResource("/images/keyboard/G.png"));
            keyboardImage3[6] = ImageIO.read(getClass().getResource("/images/keyboard/H.png"));
            keyboardImage3[7] = ImageIO.read(getClass().getResource("/images/keyboard/J.png"));
            keyboardImage3[8] = ImageIO.read(getClass().getResource("/images/keyboard/K.png"));
            keyboardImage3[9] = ImageIO.read(getClass().getResource("/images/keyboard/L.png"));
            keyboardImage3[10] = ImageIO.read(getClass().getResource("/images/keyboard/_ ;.png"));
            keyboardImage3[11] = ImageIO.read(getClass().getResource("/images/keyboard/_ '.png"));
            keyboardImage3[12] = ImageIO.read(getClass().getResource("/images/keyboard/Enter.png"));
            // 키보드 4번째 줄
            keyboardImage4[0] = ImageIO.read(getClass().getResource("/images/keyboard/Shift_L.png"));
            keyboardImage4[1] = ImageIO.read(getClass().getResource("/images/keyboard/Z.png"));
            keyboardImage4[2] = ImageIO.read(getClass().getResource("/images/keyboard/X.png"));
            keyboardImage4[3] = ImageIO.read(getClass().getResource("/images/keyboard/C.png"));
            keyboardImage4[4] = ImageIO.read(getClass().getResource("/images/keyboard/V.png"));
            keyboardImage4[5] = ImageIO.read(getClass().getResource("/images/keyboard/B.png"));
            keyboardImage4[6] = ImageIO.read(getClass().getResource("/images/keyboard/N.png"));
            keyboardImage4[7] = ImageIO.read(getClass().getResource("/images/keyboard/N.png"));
            keyboardImage4[8] = ImageIO.read(getClass().getResource("/images/keyboard/M.png"));
            keyboardImage4[9] = ImageIO.read(getClass().getResource("/images/keyboard/_ ..png"));
            keyboardImage4[10] = ImageIO.read(getClass().getResource("/images/keyboard/_ _.png"));
            keyboardImage4[11] = ImageIO.read(getClass().getResource("/images/keyboard/Shift_R.png"));
            // 키보드 5번째 줄
            keyboardImage5[0] = ImageIO.read(getClass().getResource("/images/keyboard/Ctrl.png"));
            keyboardImage5[1] = ImageIO.read(getClass().getResource("/images/keyboard/Alt_L.png"));
            keyboardImage5[2] = ImageIO.read(getClass().getResource("/images/keyboard/Space bar.png"));
            keyboardImage5[3] = ImageIO.read(getClass().getResource("/images/keyboard/Alt_R.png"));
            keyboardImage5[4] = ImageIO.read(getClass().getResource("/images/keyboard/Fn.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JLabel logoLabel = createLabel("CodeFingers", 57, 22, 50, Color.WHITE);
        logoLabel.setBounds(57, 22, 350, 75);
        JLabel nextwordLable = createLabel("다음 단어", 1122, 228, 28, new Color(29,73, 122));

        JButton gobackButton = createButton("돌아가기", 1205, 40, 30, Color.WHITE);
        gobackButton.setBounds(1205, 40, 131, 35);

        add(logoLabel);
        add(nextwordLable);
        add(gobackButton);

        // 되돌아가기 버튼을 누르면 시작 화면으로 전환
        gobackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.switchTo("ChooseOptionScreen");
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
        if (wordbackgroundImage != null) {
            g.drawImage(wordbackgroundImage, 487, 170, 452, 200, this);
        }
        if (gobackbuttonImage != null) {
            g.drawImage(gobackbuttonImage, 1160, 28, 215, 63, this);
        }
        if (inputImage != null) {
            g.drawImage(inputImage, 531, 340, 370, 1, this);
        }
        // 키보드 첫번째 줄
        if ( keyboardImage1 != null) {
            // 반복문을 이용해 규칙적으로 키보드 이미지 배치
            int x = 87; int y = 452; int margin = 88;
            for(int i=0; i<keyboardImage1.length-1; i++){
                g.drawImage(keyboardImage1[i], x+margin*i, y, 85, 70, this);
            }
            g.drawImage(keyboardImage1[13], 1231, 452, 116, 70, this);
        }
        // 키보드 두번째 줄
        if ( keyboardImage2 != null) {
            // 반복문을 이용해 규칙적으로 키보드 이미지 배치
            int x = 119; int y = 529; int margin = 88;
            g.drawImage(keyboardImage2[0], 87, y,116, 70, this);
            for(int i=1; i<keyboardImage2.length; i++){
                g.drawImage(keyboardImage2[i], x+margin*i, y, 85, 70, this);
            }
        }
        // 키보드 세번째 줄
        if ( keyboardImage3 != null) {
            // 반복문을 이용해 규칙적으로 키보드 이미지 배치
            int x = 140; int y = 606; int margin = 88;
            g.drawImage(keyboardImage3[0], 87, y,135, 70, this);
            for(int i=1; i<keyboardImage3.length-1; i++){
                g.drawImage(keyboardImage3[i], x+margin*i, y, 85, 70, this);
            }
            g.drawImage(keyboardImage3[12], 1196, y,152, 70, this);
        }
        // 키보드 네번째 줄
        if ( keyboardImage4 != null) {
            // 반복문을 이용해 규칙적으로 키보드 이미지 배치
            int x = 191; int y = 681; int margin = 88;
            g.drawImage(keyboardImage4[0], 87, y,188, 70, this);
            for(int i=1; i<keyboardImage4.length-1; i++){
                g.drawImage(keyboardImage4[i], x+margin*i, y, 85, 70, this);
            }
            g.drawImage(keyboardImage4[11], 1159, y,188, 70, this);
        }
        // 키보드 다섯번째 줄
        if ( keyboardImage4 != null) {
            int x = 87; int y = 758; int margin = 92;
            g.drawImage(keyboardImage5[0], x, y,85, 70, this);
            g.drawImage(keyboardImage5[1], x+margin, y, 85,70, this);
            g.drawImage(keyboardImage5[2], x+margin*2, y,887,70, this);
            g.drawImage(keyboardImage5[3], 889+margin*3, y, 85,70, this);
            g.drawImage(keyboardImage5[4], 889+margin*4, y, 85, 70, this);
        }
    }

    private JPanel createKeyboardPanel() {
        JPanel keyboardPanel = new JPanel();
        keyboardPanel.setLayout(new GridLayout(4, 1, 5, 5)); // 키보드 줄

        // 키 배열 생성
        String[] row1 = {"~", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "-", "=", "Backspace"};
        String[] row2 = {"Tab", "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "[", "]", "\\"};
        String[] row3 = {"Caps Lock", "A", "S", "D", "F", "G", "H", "J", "K", "L", ";", "\"", "Enter"};
        String[] row4 = {"Shift", "Z", "X", "C", "V", "B", "N", "M", ",", ".", "/", "Shift"};

        keyboardPanel.add(createKeyRow(row1));
        keyboardPanel.add(createKeyRow(row2));
        keyboardPanel.add(createKeyRow(row3));
        keyboardPanel.add(createKeyRow(row4));

        return keyboardPanel;
    }

    private JPanel createKeyRow(String[] keys) {
        JPanel rowPanel = new JPanel();
        rowPanel.setLayout(new GridLayout(1, keys.length, 5, 5));

        for (String key : keys) {
            JButton keyButton = new JButton(key);
            keyButton.setFont(new Font("SansSerif", Font.BOLD, 20));
            keyButton.setFocusPainted(false);
            keyButton.setBackground(Color.WHITE);
            keyButton.setBorder(BorderFactory.createLineBorder(Color.GRAY));

            // 이벤트 리스너 추가
            keyButton.addActionListener(e -> System.out.println("Key pressed: " + key));
            rowPanel.add(keyButton);
        }

        return rowPanel;
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
