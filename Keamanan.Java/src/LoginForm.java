import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class LoginForm extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public LoginForm() {
        setTitle("Login - Sistem Keamanan");
        setSize(550, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        Color warnaBackground = new Color(30, 39, 46);
        Color warnaTeks = new Color(255, 255, 255);
        Color warnaTombol = new Color(11, 232, 129);
        
        JPanel panelUtama = new JPanel();
        panelUtama.setBackground(warnaBackground);
        panelUtama.setBounds(0, 0, 550, 550);
        panelUtama.setLayout(null);
        add(panelUtama);

        JLabel lblJudul = new JLabel("SECURITY LOGIN", SwingConstants.CENTER);
        lblJudul.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblJudul.setForeground(warnaTeks);
        lblJudul.setBounds(0, 60, 550, 40);
        panelUtama.add(lblJudul);

        JLabel lblSubJudul = new JLabel("Silakan masuk untuk melanjutkan", SwingConstants.CENTER);
        lblSubJudul.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lblSubJudul.setForeground(new Color(200, 200, 200));
        lblSubJudul.setBounds(0, 100, 550, 30);
        panelUtama.add(lblSubJudul);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblUsername.setForeground(warnaTeks);
        lblUsername.setBounds(75, 160, 400, 30);
        panelUtama.add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtUsername.setBounds(75, 190, 400, 45);
        txtUsername.setBorder(BorderFactory.createEmptyBorder(5, 12, 5, 12));
        panelUtama.add(txtUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblPassword.setForeground(warnaTeks);
        lblPassword.setBounds(75, 260, 400, 30);
        panelUtama.add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtPassword.setBounds(75, 290, 400, 45);
        txtPassword.setBorder(BorderFactory.createEmptyBorder(5, 12, 5, 12));
        panelUtama.add(txtPassword);

        btnLogin = new JButton("LOGIN");
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnLogin.setBackground(warnaTombol);
        btnLogin.setForeground(Color.BLACK);
        btnLogin.setBounds(75, 380, 400, 50);
        btnLogin.setFocusPainted(false);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogin.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelUtama.add(btnLogin);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prosesLogin();
            }
        });
        
        txtPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prosesLogin();
            }
        });
    }

    private void prosesLogin() {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        if (username.equals("admin") && password.equals("admin123")) {
            JOptionPane.showMessageDialog(this, "Selamat datang!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            
            KeamananApp menuUtama = new KeamananApp();
            menuUtama.setVisible(true);
            this.dispose();
            
        } else {
            JOptionPane.showMessageDialog(this, "Username atau Password salah!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }
}
