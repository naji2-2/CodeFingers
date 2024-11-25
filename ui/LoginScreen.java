package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class LoginScreen extends JPanel {

    private MainFrame mainFrame;
    private BufferedImage backgroundImage;
    private BufferedImage inputbackgroundImage;
    private BufferedImage smallTitleImage;
    private BufferedImage idinputImage;
    private BufferedImage passwordinputImgae;
    private BufferedImage nextbuttonImage;

    public LoginScreen(MainFrame mainFrame) {

        this.mainFrame = mainFrame;

        setLayout(null);
        setPreferredSize(new Dimension(1280, 720));

        // 배경 이미지 로드
        try {
            backgroundImage = ImageIO.read(getClass().getResource("/images/Background.png"));  // 상대 경로로 수정
            inputbackgroundImage = ImageIO.read(getClass().getResource("/images/Input_Background.png"));
            smallTitleImage = ImageIO.read(getClass().getResource("/images/Small_Title.png"));
            idinputImage = ImageIO.read(getClass().getResource("/images/Input.png"));
            passwordinputImgae = ImageIO.read((getClass().getResource("/images/Input.png")));
            nextbuttonImage = ImageIO.read(getClass().getResource("/images/Next_Button.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 입력 필드 및 버튼 설정
        JLabel loginLabel = createLabel("로그인", 645, 235, 50, Color.WHITE);
        JLabel idLabel = createLabel("아이디", 482, 395, 35, new Color(41, 105, 195));
        JTextField idField = createTextField(620, 395);
        JLabel pwLabel = createLabel("비밀번호", 450, 517, 35, new Color(41, 105, 195));
        JPasswordField pwField = createPasswordField(620, 517);

        JButton JoButton = createButton("아직 회원이 아니신가요?", 569, 630, 30, new Color(160, 186, 223));
        JButton loginButton = createButton("다음", 1060, 780, 30, Color.WHITE);
        loginButton.setBounds(1060, 780, 182, 81);

        // 로그인 버튼 클릭 이벤트
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Login Button clicked");
                String id = idField.getText(); // 입력한 아이디
                String password = new String(pwField.getPassword()); // 입력한 비밀번호
                if (validateCredentials(id, password)) {
                    System.out.println("로그인 성공");
                    // 성공 시 ChooseGameScreen으로 전환
                } else {
                    System.out.println("로그인 실패");
                    // 로그인 실패 메시지 표시
                    //JOptionPane.showMessageDialog(mainFrame, "아이디 또는 비밀번호가 잘못되었습니다.", "로그인 실패", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // 패널에 컴포넌트 추가
        add(loginLabel);
        add(idLabel);
        add(idField);
        add(pwLabel);
        add(pwField);
        add(JoButton);
        add(loginButton);

        // 회원가입 버튼 클릭 이벤트
        JoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 회원가입 화면으로 전환
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
        if (inputbackgroundImage != null) {
            g.drawImage(inputbackgroundImage, 297, 156, 846, 589, this);
        }
        if(smallTitleImage != null) {
            g.drawImage(smallTitleImage, 433, 204, 575, 125, this);
        }
        if (idinputImage != null) {
            g.drawImage(idinputImage, 603, 382, 384, 77, this);
        }
        if (passwordinputImgae != null) {
            g.drawImage(passwordinputImgae, 603, 505, 384, 77, this);
        }
        if (nextbuttonImage != null) {
            g.drawImage(nextbuttonImage, 1061, 784, 182, 81, this);
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
        textField.setBounds(x, y, 280, 45);
        textField.setOpaque(false);
        textField.setForeground(new Color(41, 105, 195));
        textField.setFont(new Font("SansSerif", Font.BOLD, 25));
        textField.setBorder(null);
        return textField;
    }

    private JPasswordField createPasswordField(int x, int y) {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(x, y, 280, 45);
        passwordField.setOpaque(false);
        passwordField.setForeground(new Color(41, 105, 195));
        passwordField.setFont(new Font("SansSerif", Font.PLAIN, 25));
        passwordField.setBorder(null);
        return passwordField;
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

    private boolean validateCredentials(String id, String password) {
        // 로그인 기능 테스트
        return id.equals("naji22") && password.equals("1234");
    }
}