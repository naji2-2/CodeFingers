package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginScreen extends JPanel {

    private MainFrame mainFrame;
    private boolean loginFailed = false;

    private BufferedImage backgroundImage;
    private BufferedImage inputbackgroundImage;
    private BufferedImage smallTitleImage;
    private BufferedImage idinputImage;
    private BufferedImage passwordinputImgae;
    private BufferedImage nextbuttonImage;
    private BufferedImage previousbuttonImage;
    private BufferedImage loginfailedImage;
    private BufferedImage popupImage;

    public LoginScreen(MainFrame mainFrame) {

        this.mainFrame = mainFrame;

        setLayout(null);
        setPreferredSize(new Dimension(1280, 720));

        // 이미지 로드
        try {
            backgroundImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/images/Background.png")));  // 상대 경로로 수정
            inputbackgroundImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/images/Input_Background.png")));
            smallTitleImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/images/Small_Title.png")));
            idinputImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/images/Input.png")));
            passwordinputImgae = ImageIO.read(Objects.requireNonNull(getClass().getResource("/images/Input.png")));
            nextbuttonImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/images/Next_Button.png")));
            previousbuttonImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/images/Next_Button.png")));
            loginfailedImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/images/Background.png")));
            popupImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/images/Popup.png")));
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
        JButton nextButton = createButton("다음", 1060, 780, 30, Color.WHITE);
        nextButton.setBounds(1060, 780, 182, 81);
        JButton previousButton = createButton("이전",215,780,30, Color.WHITE);
        previousButton.setBounds(215,780, 182, 81);

        // 로그인 버튼 클릭 이벤트
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Next Button clicked");
                String id = idField.getText();
                String password = new String(pwField.getPassword());
                if (validateCredentials(id, password)) {
                    System.out.println("로그인 성공");
                    loginFailed = false; // 실패 상태 초기화
                    mainFrame.switchTo("ChooseGameScreen");
                } else {
                    System.out.println("로그인 실패");
                    loginFailed = true; // 실패 상태 설정
                    repaint(); // 패널 다시 그리기
                }
            }
        });

        // 이전 버튼을 누르면 시작 화면으로 전환
        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.switchTo("StartScreen");
                System.out.println("Previous Button clicked");
            }
        });

        // 패널에 컴포넌트 추가
        add(loginLabel);
        add(idLabel);
        add(idField);
        add(pwLabel);
        add(pwField);
        add(JoButton);
        add(nextButton);
        add(previousButton);

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
        Graphics2D g2d = (Graphics2D) g;
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
        if (previousbuttonImage != null) {
            g.drawImage(previousbuttonImage, 215,784, 182, 81, this);
        }

        // 로그인 실패 이미지 표시
        if (loginFailed && loginfailedImage != null && popupImage != null) {
            float alpha = 0.5f; // 투명도 설정
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            g2d.drawImage(loginfailedImage, 0, 0, getWidth(), getHeight(), this);

            // 팝업 이미지
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
            JLabel popupLabel = new JLabel(new ImageIcon(popupImage));
            popupLabel.setBounds(468, 350, 505, 200);
            add(popupLabel);
            setComponentZOrder(popupLabel, 1);

            // 에러 메시지
            JLabel errorMessage = createLabel(
                    "<html>아이디 또는 비밀번호가<br>올바르지 않습니다!</html>",
                    528, 412, 35, new Color(41, 105, 195)
            );
            errorMessage.setBounds(530, 380, 400, 150);
            add(errorMessage);
            setComponentZOrder(errorMessage, 0);

            // 패널에 마우스 리스너 추가
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // 화면 클릭 시 loginfailedImage, popupLabel, errorMessage 제거
                    if (popupLabel != null) {
                        remove(popupLabel);
                    }
                    if (errorMessage != null) {
                        remove(errorMessage);
                    }
                    loginFailed = false;

                    revalidate(); // 레이아웃 갱신
                    repaint();    // 화면 다시 그리기

                    // 리스너 제거 (다시 클릭 시 불필요한 동작 방지)
                    removeMouseListener(this);
                }
            });

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