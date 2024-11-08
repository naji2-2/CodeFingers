package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoginScreen extends JPanel {

    private MainFrame mainFrame;
    private BufferedImage backgroundImage;

    public LoginScreen(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        setLayout(null);
        setPreferredSize(new Dimension(1280, 720));

        // 배경 이미지 로드
        try {
            backgroundImage = ImageIO.read(new File("C:/CodeFingers_JAVA_Project/CodeFingers/images/Login_n.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 입력 필드 및 버튼 설정
        JLabel loginLabel = createLabel("로그인", 570, 150, 44, Color.WHITE);
        JLabel idLabel = createLabel("아이디", 366, 315, 44, new Color(52, 71, 200));
        JTextField idField = createTextField(560, 325);
        JLabel pwLabel = createLabel("비밀번호", 323, 430, 44, new Color(52, 71, 200));
        JPasswordField pwField = createPasswordField(560, 440);

        JButton loButton = createButton("아직 회원이 아니신가요?", 470, 618, 30, Color.WHITE);
        JButton loginButton = createButton("▶", 957, 463, 55, new Color(52, 71, 200));

        // 로그인 버튼 클릭 이벤트
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Login Button clicked");
                String id = idField.getText(); // 입력한 아이디
                String password = new String(pwField.getPassword()); // 입력한 비밀번호
                if (validateCredentials(id, password)) {
                    System.out.println("로그인 성공");
                    mainFrame.switchTo("MainScreen"); // 성공 시 MainScreen으로 전환
                } else {
                    System.out.println("로그인 실패");
                    // 로그인 실패 메시지 표시
                }
            }
        });

        // 패널에 컴포넌트 추가
        add(loginLabel);
        add(idLabel);
        add(idField);
        add(pwLabel);
        add(pwField);
        add(loButton);
        add(loginButton);

        // 회원가입 버튼 클릭 이벤트
        loButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.switchTo("JoinScreen");
                System.out.println("Join Button clicked");
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    private JLabel createLabel(String text, int x, int y, int fontSize, Color color) {
        JLabel label = new JLabel(text);
        label.setForeground(color);
        label.setFont(new Font("SansSerif", Font.BOLD, fontSize));
        label.setBounds(x, y, 220, 50);
        return label;
    }

    private JTextField createTextField(int x, int y) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, 250, 40);
        textField.setOpaque(false);
        textField.setForeground(new Color(52, 71, 200));
        textField.setFont(new Font("SansSerif", Font.BOLD, 20));
        textField.setBorder(null);
        return textField;
    }

    private JPasswordField createPasswordField(int x, int y) {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(x, y, 250, 40);
        passwordField.setOpaque(false);
        passwordField.setForeground(new Color(52, 71, 200));
        passwordField.setFont(new Font("SansSerif", Font.PLAIN, 20));
        passwordField.setBorder(null);
        return passwordField;
    }

    private JButton createButton(String text, int x, int y, int fontSize, Color color) {
        JButton button = new JButton(text);
        button.setForeground(color);
        button.setBounds(x, y, 350, 30);
        button.setBackground(new Color(0, 0, 0, 0)); // 배경 투명
        button.setFont(new Font("SansSerif", Font.BOLD, fontSize));
        button.setBorder(null);
        return button;
    }

    private boolean validateCredentials(String id, String password) {
        // 로그인 테스트
        return id.equals("naji22") && password.equals("1234");
    }
}