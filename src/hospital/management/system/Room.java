package hospital.management.system;import net.proteanit.sql.DbUtils;import javax.swing.*;import java.awt.*;import java.awt.event.ActionEvent;import java.awt.event.ActionListener;import java.sql.ResultSet;public class Room extends JFrame {    private JTable table;    public Room() {        setTitle("Hospital Management System - Room Information");        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        setSize(900, 600);        setLocationRelativeTo(null); // Center the frame on the screen        setUndecorated(true); // Remove window borders        setLayout(new BorderLayout());        // Main panel with gradient background        GradientPanel mainPanel = new GradientPanel();        mainPanel.setLayout(null);        add(mainPanel);        // Header label        JLabel headerLabel = new JLabel("Room Information");        headerLabel.setFont(new Font("Tahoma", Font.BOLD, 24));        headerLabel.setForeground(Color.WHITE);        headerLabel.setBounds(300, 20, 300, 40);        mainPanel.add(headerLabel);        // Room information table        table = new JTable();        table.setBackground(new Color(90, 156, 163));        table.setForeground(Color.WHITE);        table.setFont(new Font("Tahoma", Font.PLAIN, 14));        table.setBounds(10, 80, 600, 400);        JScrollPane tableScrollPane = new JScrollPane(table);        tableScrollPane.setBounds(10, 80, 600, 400);        mainPanel.add(tableScrollPane);        // Load room data from database        loadRoomData();        // Column labels        addColumnLabel(mainPanel, "Room No", 10, 60);        addColumnLabel(mainPanel, "Availability", 120, 60);        addColumnLabel(mainPanel, "Price", 260, 60);        addColumnLabel(mainPanel, "Bed Type", 370, 60);        // Room image        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/roomm.png"));        Image image = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);        JLabel imageLabel = new JLabel(new ImageIcon(image));        imageLabel.setBounds(630, 200, 200, 200);        mainPanel.add(imageLabel);        // Back button        JButton backButton = createStyledButton("Back");        backButton.setBounds(370, 500, 120, 30);        backButton.addActionListener(e -> setVisible(false));        mainPanel.add(backButton);        setVisible(true);    }    private void loadRoomData() {        try {            conn c = new conn();            String query = "SELECT * FROM room";            ResultSet resultSet = c.statement.executeQuery(query);            table.setModel(DbUtils.resultSetToTableModel(resultSet));        } catch (Exception e) {            e.printStackTrace();        }    }    private void addColumnLabel(JPanel panel, String text, int x, int y) {        JLabel label = new JLabel(text);        label.setFont(new Font("Tahoma", Font.BOLD, 14));        label.setForeground(Color.WHITE);        label.setBounds(x, y, 100, 20);        panel.add(label);    }    private JButton createStyledButton(String text) {        JButton button = new JButton(text);        button.setFont(new Font("Roboto", Font.BOLD, 15));        button.setBackground(new Color(0, 122, 255));        button.setForeground(Color.WHITE);        button.setFocusPainted(false);        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));        return button;    }    public static void main(String[] args) {        SwingUtilities.invokeLater(Room::new);    }    // Custom JPanel with Gradient Background    private static class GradientPanel extends JPanel {        @Override        protected void paintComponent(Graphics g) {            super.paintComponent(g);            Graphics2D g2d = (Graphics2D) g;            int width = getWidth();            int height = getHeight();            Color color1 = new Color(67, 70, 80);            Color color2 = new Color(0, 122, 255);            GradientPaint gp = new GradientPaint(0, 0, color1, 0, height, color2);            g2d.setPaint(gp);            g2d.fillRect(0, 0, width, height);        }    }}