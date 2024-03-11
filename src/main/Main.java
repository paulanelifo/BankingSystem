package main;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import static main.Accounts.createAccountsHashMap;

public class Main {
    
    public static HashMap<String, HashMap<String, Object>> accounts = createAccountsHashMap();

    
    public static JFrame frame = new JFrame();
    public static void GradientFrame() {
        frame = new JFrame("Banking System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int width = getWidth();
                int height = getHeight();
                Color color1 = new Color(213, 255, 214); // Start color (e.g., coral)
                Color color2 = new Color(218, 224, 255);  // End color (e.g., yellow)
                GradientPaint gradient = new GradientPaint(0, 0, color1, width, height, color2);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, width, height);
            }
        };

        frame.setContentPane(contentPane);
        frame.setSize(400, 500);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }
    
    public static void loginmain(){
        GradientFrame();
        JPanel panel = Login.createLoginPanel(frame);
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    public static void main(String[] args){
        loginmain();
    }
}
