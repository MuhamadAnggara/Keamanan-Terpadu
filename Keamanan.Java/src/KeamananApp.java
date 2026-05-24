import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class KeamananApp extends JFrame {

    private JTable tabelPetugas;
    private JTable tabelKejadian;
    private JTable tabelArea;
    private DefaultTableModel modelPetugas;
    private DefaultTableModel modelKejadian;
    private DefaultTableModel modelArea;
    private CardLayout cardLayout;
    private JPanel panelKontenUtama;

    private final String SUPABASE_URL = "https://xpafhvabotsgfrsktpit.supabase.co";
    private final String SUPABASE_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InhwYWZodmFib3RzZ2Zyc2t0cGl0Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzU4NTUzOTksImV4cCI6MjA5MTQzMTM5OX0.Oy_81oJ4nte6gZjpw6b37xSjxsu068rZ_PNw1YzLB68";

    public KeamananApp() {
        setTitle("Sistem Keamanan Terpadu");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel sidebar = new JPanel(new BorderLayout());
        sidebar.setBackground(new Color(30, 39, 46));
        sidebar.setPreferredSize(new Dimension(250, 0));

        JLabel logoLabel = new JLabel("SECURITY DEPT", SwingConstants.CENTER);
        logoLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        logoLabel.setForeground(new Color(255, 255, 255));
        logoLabel.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 10));
        sidebar.add(logoLabel, BorderLayout.NORTH);

        JPanel menuPanel = new JPanel(new GridLayout(6, 1, 0, 15));
        menuPanel.setBackground(new Color(30, 39, 46));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        JButton btnPetugas = buatTombolMenu("Data Petugas");
        JButton btnKejadian = buatTombolMenu("Laporan Kejadian");
        JButton btnArea = buatTombolMenu("Area Patroli");

        menuPanel.add(btnPetugas);
        menuPanel.add(btnKejadian);
        menuPanel.add(btnArea);

        sidebar.add(menuPanel, BorderLayout.CENTER);

        JButton btnRefresh = new JButton("SINKRONISASI DATA");
        btnRefresh.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnRefresh.setBackground(new Color(11, 232, 129));
        btnRefresh.setForeground(Color.BLACK);
        btnRefresh.setFocusPainted(false);
        btnRefresh.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        btnRefresh.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sidebar.add(btnRefresh, BorderLayout.SOUTH);

        cardLayout = new CardLayout();
        panelKontenUtama = new JPanel(cardLayout);
        panelKontenUtama.setBackground(new Color(245, 246, 250));

        modelPetugas = new DefaultTableModel(new String[]{"ID Petugas", "Nama", "Jabatan", "Shift"}, 0);
        tabelPetugas = buatTabelUtama(modelPetugas);
        panelKontenUtama.add(buatPanelTabel("Manajemen Data Petugas", tabelPetugas), "Petugas");

        modelKejadian = new DefaultTableModel(new String[]{"ID Kejadian", "ID Petugas", "Deskripsi", "Tanggal"}, 0);
        tabelKejadian = buatTabelUtama(modelKejadian);
        panelKontenUtama.add(buatPanelTabel("Log Laporan Kejadian", tabelKejadian), "Kejadian");

        modelArea = new DefaultTableModel(new String[]{"ID Area", "Nama Area", "Tingkat Risiko", "Status CCTV"}, 0);
        tabelArea = buatTabelUtama(modelArea);
        panelKontenUtama.add(buatPanelTabel("Pemantauan Area Patroli", tabelArea), "Area");

        btnPetugas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelKontenUtama, "Petugas");
            }
        });
        
        btnKejadian.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelKontenUtama, "Kejadian");
            }
        });
        
        btnArea.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelKontenUtama, "Area");
            }
        });

        btnRefresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tarikDataSupabase("petugas", modelPetugas, new String[]{"id", "nama", "jabatan", "shift"});
                tarikDataSupabase("kejadian", modelKejadian, new String[]{"id", "id_petugas", "deskripsi", "tanggal"});
                tarikDataSupabase("area_patroli", modelArea, new String[]{"id", "nama_area", "tingkat_risiko", "status_cctv"});
            }
        });

        add(sidebar, BorderLayout.WEST);
        add(panelKontenUtama, BorderLayout.CENTER);
    }

    private JButton buatTombolMenu(String teks) {
        JButton btn = new JButton(teks);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(72, 84, 96));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private JPanel buatPanelTabel(String judul, JTable tabel) {
        JPanel panel = new JPanel(new BorderLayout(0, 20));
        panel.setBackground(new Color(245, 246, 250));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel lblJudul = new JLabel(judul);
        lblJudul.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblJudul.setForeground(new Color(47, 54, 64));
        panel.add(lblJudul, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(tabel);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(220, 221, 225), 1));
        scrollPane.getViewport().setBackground(Color.WHITE);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JTable buatTabelUtama(DefaultTableModel model) {
        JTable tabel = new JTable(model);
        tabel.setRowHeight(40);
        tabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        tabel.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15));
        tabel.getTableHeader().setBackground(new Color(241, 242, 246));
        tabel.getTableHeader().setForeground(new Color(47, 54, 64));
        tabel.getTableHeader().setPreferredSize(new Dimension(0, 45));
        tabel.setGridColor(new Color(236, 240, 241));
        tabel.setSelectionBackground(new Color(116, 185, 255));
        tabel.setSelectionForeground(Color.BLACK);
        tabel.setShowVerticalLines(false);
        
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        tabel.setDefaultRenderer(Object.class, renderer);
        return tabel;
    }

    private void tarikDataSupabase(String namaTabel, DefaultTableModel model, String[] kolom) {
        model.setRowCount(0);
        try {
            URL url = new URL(SUPABASE_URL + "/rest/v1/" + namaTabel + "?select=*");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("apikey", SUPABASE_KEY);
            conn.setRequestProperty("Authorization", "Bearer " + SUPABASE_KEY);
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() == 200) {
                BufferedReader pembaca = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
                StringBuilder respon = new StringBuilder();
                String baris;
                while ((baris = pembaca.readLine()) != null) {
                    respon.append(baris.trim());
                }
                pembaca.close();

                List<String[]> dataDaftar = parsingJsonManual(respon.toString(), kolom);
                for (String[] isiBaris : dataDaftar) {
                    model.addRow(isiBaris);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Kesalahan HTTP pada " + namaTabel + ": " + conn.getResponseCode());
            }
            conn.disconnect();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Gagal menarik data " + namaTabel + ": " + ex.getMessage());
        }
    }

    private List<String[]> parsingJsonManual(String jsonTeks, String[] kunci) {
        List<String[]> hasil = new ArrayList<>();
        String jsonBersih = jsonTeks.trim();
        if (jsonBersih.startsWith("[")) {
            jsonBersih = jsonBersih.substring(1);
        }
        if (jsonBersih.endsWith("]")) {
            jsonBersih = jsonBersih.substring(0, jsonBersih.length() - 1);
        }
        if (jsonBersih.isEmpty()) {
            return hasil;
        }

        String[] itemBanyak = jsonBersih.split("\\},\\s*\\{");
        
        for (String itemSatu : itemBanyak) {
            itemSatu = itemSatu.replaceAll("^\\{?", "").replaceAll("\\}?$", "");
            String[] nilaiArray = new String[kunci.length];
            for (int i = 0; i < kunci.length; i++) {
                String polaKunci = "\"" + kunci[i] + "\":";
                int indeks = itemSatu.indexOf(polaKunci);
                if (indeks != -1) {
                    int mulai = indeks + polaKunci.length();
                    char karakterPertama = itemSatu.charAt(mulai);
                    int akhir;
                    if (karakterPertama == '"') {
                        mulai++;
                        akhir = itemSatu.indexOf("\"", mulai);
                    } else {
                        akhir = itemSatu.indexOf(",", mulai);
                        if (akhir == -1) {
                            akhir = itemSatu.length();
                        }
                    }
                    if (akhir == -1) {
                        akhir = itemSatu.length();
                    }
                    nilaiArray[i] = itemSatu.substring(mulai, akhir).trim();
                } else {
                    nilaiArray[i] = "";
                }
            }
            hasil.add(nilaiArray);
        }
        return hasil;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new KeamananApp().setVisible(true);
            }
        });
    }
}
