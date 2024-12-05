package ui;

import data.DBConnection;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;

public class JoinScreen extends JPanel {

    private MainFrame mainFrame;
    private boolean joinFailed = false;
    private boolean inputid = false;
    private JLabel errorMessage = null;

    private BufferedImage backgroundImage;
    private BufferedImage inputbackgroundImage;
    private BufferedImage smallTitleImage;
    private BufferedImage idinputImage;
    private BufferedImage passwordinputImgae;
    private BufferedImage checkpasswordinputImgae;
    private BufferedImage nextbuttonImage;
    private BufferedImage previousbuttonImage;
    private BufferedImage joinfailedImage;
    private BufferedImage popupImage;

    private void removeComponents() {
        if (errorMessage != null) {
            remove(errorMessage);
            errorMessage = null; // 참조 제거
        }
        for (Component component : getComponents()) {
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                if (label.getIcon() != null) { // 이미지가 있는 JLabel만 제거
                    remove(label);
                }
            }
        }
        joinFailed = false;
        inputid = false;

        // 화면 갱신
        revalidate();
        repaint();
    }

    public JoinScreen(MainFrame mainFrame) {

        this.mainFrame = mainFrame;

        setLayout(null);
        setPreferredSize(new Dimension(1280, 720));

        // 이미지 로드
        try {
            backgroundImage = ImageIO.read(getClass().getResource("/images/Background.png"));  // 상대 경로로 수정
            inputbackgroundImage = ImageIO.read(getClass().getResource("/images/Input_Background.png"));
            smallTitleImage = ImageIO.read(getClass().getResource("/images/Small_Title.png"));
            idinputImage = ImageIO.read(getClass().getResource("/images/Input.png"));
            passwordinputImgae = ImageIO.read(getClass().getResource("/images/Input.png"));
            checkpasswordinputImgae = ImageIO.read(getClass().getResource("/images/Input.png"));
            nextbuttonImage = ImageIO.read(getClass().getResource("/images/Next_Button.png"));
            previousbuttonImage = ImageIO.read(getClass().getResource("/images/Next_Button.png"));
            joinfailedImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/images/Background.png")));
            popupImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/images/Popup.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 입력 필드 및 버튼 설정
        JLabel joinLabel = createLabel("회원가입", 620, 235, 50, Color.WHITE);
        JLabel idLabel = createLabel("아이디", 482, 395, 35, new Color(41, 105, 195));
        JTextField idField = createTextField(620, 395);
        JLabel pwLabel = createLabel("비밀번호", 450, 517, 35, new Color(41, 105, 195));
        JPasswordField pwField = createPasswordField(620, 517);
        JLabel pwokLabel = createLabel("비밀번호확인", 381, 630, 35, new Color(41, 105, 195));
        pwokLabel.setBounds(381, 630, 400, 50);
        JPasswordField pwokField = createPasswordField(620, 635);

        JButton nextButton = createButton("다음", 1060, 780, 30, Color.WHITE);
        nextButton.setBounds(1060, 780, 182, 81);
        JButton previousButton = createButton("이전",215,780,30, Color.WHITE);
        previousButton.setBounds(215,780, 182, 81);

        String id = idField.getText();
        String password = new String(pwField.getPassword()); // 입력한 비밀번호
        String confirmPassword = new String(pwokField.getPassword()); // 입력한 비밀번호 확인

        // 다음 버튼 클릭 이벤트
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText(); // 입력 필드의 최신 값 가져오기
                String password = new String(pwField.getPassword());
                String confirmPassword = new String(pwokField.getPassword());

                if (!id.isEmpty() && !password.isEmpty() && !confirmPassword.isEmpty()) {
                    if (password.equals(confirmPassword)) {
                        // 데이터베이스 연결
                        try (Connection connection = DBConnection.getConnection()) {
                            // 아이디 중복 확인
                            String checkSql = "SELECT COUNT(*) FROM user WHERE userid = ?";
                            try (PreparedStatement checkStmt = connection.prepareStatement(checkSql)) {
                                checkStmt.setString(1, id);
                                try (ResultSet rs = checkStmt.executeQuery()) {
                                    if (rs.next() && rs.getInt(1) > 0) {
                                        System.out.println("중복된 아이디입니다.");
                                        joinFailed = true;
                                        revalidate();
                                        repaint();
                                        return; // 중복된 경우 추가 작업 중단
                                    }
                                }
                            }

                            // 중복되지 않은 경우 회원가입 처리
                            String insertSql = "INSERT INTO user (userid, password) VALUES (?, ?)";
                            try (PreparedStatement insertStmt = connection.prepareStatement(insertSql)) {
                                insertStmt.setString(1, id);
                                insertStmt.setString(2, password);
                                insertStmt.executeUpdate();
                                System.out.println("회원가입 성공");
                                inputid = true;
                                joinFailed = false;

                                // 성공 후 화면 갱신
                                revalidate();
                                repaint();
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            System.out.println("회원가입 중 오류 발생");
                        }
                    } else {
                        System.out.println("비밀번호가 일치하지 않습니다.");
                        joinFailed = true;
                        inputid = false;
                    }
                } else {
                    System.out.println("모든 필드를 입력해주세요.");
                    joinFailed = true;
                    inputid = false;
                }

                // 상태 변경 후 화면 갱신
                revalidate();
                repaint();
            }
        });

        // 이전 버튼을 누르면 로그인 화면으로 전환
        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 기존 팝업 컴포넌트 제거
                removeComponents();
                mainFrame.switchTo("LoginScreen");
                System.out.println("Login Button clicked");
            }
        });

        // 패널에 컴포넌트 추가
        add(joinLabel);
        add(idLabel);
        add(idField);
        add(pwLabel);
        add(pwField);
        add(pwokLabel);
        add(pwokField);
        add(nextButton);
        add(previousButton);
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
        if (checkpasswordinputImgae != null) {
            g.drawImage(checkpasswordinputImgae, 603, 619, 384, 77, this);
        }
        if (nextbuttonImage != null) {
            g.drawImage(nextbuttonImage, 1061, 784, 182, 81, this);
        }
        if (previousbuttonImage != null) {
            g.drawImage(previousbuttonImage, 215,784, 182, 81, this);
        }

        // 회원가입 성공, 실패 이미지 표시
        if ((joinFailed||inputid) && (joinfailedImage != null && popupImage != null)) {
            float alpha = 0.5f; // 투명도 설정
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            g2d.drawImage(joinfailedImage, 0, 0, getWidth(), getHeight(), this);

            // 팝업 이미지
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
            JLabel popupLabel = new JLabel(new ImageIcon(popupImage));
            popupLabel.setBounds(468, 350, 505, 200);
            add(popupLabel);
            setComponentZOrder(popupLabel, 1);

            if(joinFailed){
                // 에러 메시지
                errorMessage = createLabel(
                        "<html>비밀번호가<br>올바르지 않습니다!</html>",
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
                        joinFailed = false;

                        revalidate(); // 레이아웃 갱신
                        repaint();    // 화면 다시 그리기

                        // 리스너 제거 (다시 클릭 시 불필요한 동작 방지)
                        removeMouseListener(this);
                    }
                });
            }
            if(inputid) {
                // 회원가입 성공 메시지
                errorMessage = createLabel(
                        "<html>회원가입이<br>완료되었습니다!</html>",
                        528, 412, 35, new Color(41, 105, 195)
                );
                errorMessage.setBounds(530, 380, 400, 150);
                add(errorMessage);
                setComponentZOrder(errorMessage, 0);
                // 화면 클릭 시 로그인 화면으로 전환
                this.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        // 기존 팝업 컴포넌트 제거
                        removeComponents();
                        mainFrame.switchTo("LoginScreen");
                        // 리스너 제거 (다시 클릭 시 불필요한 동작 방지)
                        removeMouseListener(this);
                        repaint(); // 패널 다시 그리기
                    }
                });
            }
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
        textField.setForeground(new Color(52, 71, 200));
        textField.setFont(new Font("SansSerif", Font.BOLD, 25));
        textField.setBorder(null);
        return textField;
    }

    private JPasswordField createPasswordField(int x, int y) {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(x, y, 280, 45);
        passwordField.setOpaque(false);
        passwordField.setForeground(new Color(52, 71, 200));
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

    private boolean validateCredentials(String id, String password, String confirmPassword) {
        // 회원가입 기능 테스트
        return password.equals(confirmPassword) && id.length() > 0 && password.length() > 0;
    }
}