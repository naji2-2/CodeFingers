package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoginScreen extends JFrame {

    private BufferedImage backgroundImage;

    public LoginScreen() {
        setTitle("Login");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // JLayeredPane을 이용해 배경 이미지와 입력 필드를 레이어별로 배치
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1280, 720));

        // 배경 이미지 로드 및 패널 설정
        try {
            backgroundImage = ImageIO.read(new File("C:/CodeFingers_JAVA_Project/CodeFingers/images/Login_n.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        backgroundPanel.setBounds(0, 0, 1280, 720);
        layeredPane.add(backgroundPanel, JLayeredPane.DEFAULT_LAYER);

        // 입력 필드 및 버튼이 있는 패널 설정
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(null);
        inputPanel.setBounds(0, 0, 1280, 720);
        inputPanel.setOpaque(false); // 배경 투명하게 설정

        JLabel Login = new JLabel("로그인");
        Login.setForeground(Color.WHITE); // 글씨 색상을 흰색으로
        Login.setFont(new Font("SansSerif", Font.BOLD, 44));
        Login.setBounds(570, 150, 150, 75);
        //Login.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        // 아이디
        JLabel idLabel = new JLabel("아이디");
        idLabel.setForeground(new Color(52, 71, 200)); // 글씨 색상 getHSBColor(232, 74, 78)
        idLabel.setFont(new Font("SansSerif", Font.BOLD, 44));
        idLabel.setBounds(366, 315, 135, 52);

        // 아이디 입력창
        JTextField idField = new JTextField();
        idField.setBounds(560, 325, 250, 40);
        idField.setOpaque(false); // 필드 투명하게 설정
        idField.setForeground(new Color(52, 71, 200));
        idField.setFont(new Font("SansSerif", Font.BOLD, 20));
        idField.setBorder(null);

        // 비밀번호
        JLabel pwLabel = new JLabel("비밀번호");
        pwLabel.setForeground(new Color(52, 71, 200));
        pwLabel.setFont(new Font("SansSerif", Font.BOLD, 44));
        pwLabel.setBounds(323, 430, 200, 40);

        // 비밀번호 입력 창
        JPasswordField pwField = new JPasswordField();
        pwField.setBounds(560, 440, 250, 40);
        pwField.setOpaque(false);
        pwField.setForeground(new Color(52, 71, 200));
        pwField.setFont(new Font("SansSerif", Font.PLAIN, 20));
        pwField.setBorder(null);

        // 회원가입 버튼
        JButton loButton = new JButton("아직 회원이 아니신가요?");
        loButton.setForeground(Color.WHITE);  // 글자색
        loButton.setBounds(470, 618, 350, 30);
        loButton.setBackground(new Color(0, 0, 0, 0)); // 버튼 배경을 투명하게
        //loginButton.setForeground(null);
        loButton.setFont(new Font("SansSerif", Font.BOLD, 30));
        loButton.setBorder(null);

        // 로그인 버튼
        JButton loginButton = new JButton("▶");
        loginButton.setForeground(new Color(52, 71, 200));  // 글자색
        loginButton.setBounds(957, 463, 60, 60);
        loginButton.setBackground(new Color(0, 0, 0, 0)); // 버튼 배경을 투명하게
        //loginButton.setForeground(null);
        loginButton.setFont(new Font("SansSerif", Font.BOLD, 55));
        loginButton.setBorder(null);

        // 로그인 파일 클릭 이벤트
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Login Button clicked");
                String id = idField.getText(); // 입력한 아이디
                String password = new String(pwField.getPassword()); // 입력한 비밀번호
                if (validateCredentials(id, password)) {
                    System.out.println("로그인 성공");
                    dispose(); // 현재 창 닫기
                    new LoginScreen(); // LoginScreen 창 열기
                } else {
                    System.out.println("로그인 실패");
                    // 로그인 실패 메시지 표시
                }
            }
        });

        // 패널에 컴포넌트 추가
        inputPanel.add(Login);
        inputPanel.add(idLabel);
        inputPanel.add(idField);
        inputPanel.add(pwLabel);
        inputPanel.add(pwField);
        inputPanel.add(loButton);
        inputPanel.add(loginButton);

        // layeredPane에 입력 패널을 추가
        layeredPane.add(inputPanel, JLayeredPane.PALETTE_LAYER);

        // 프레임에 layeredPane 추가
        this.add(layeredPane);
        this.pack();
        this.setVisible(true);
    }

    private boolean validateCredentials(String id, String password) {
        // 로그인 기능 테스트
        return id.equals("naji22") && password.equals("1234");
    }

    public static void main(String[] args) {
        new LoginScreen();
    }
}