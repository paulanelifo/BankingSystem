package main;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import static main.Accounts.createAccountsHashMap;

public class Main {
    
    public static HashMap<Integer, HashMap<String, Object>> accounts = new HashMap<>();
    public static int currentid=0;
    
    public static JFrame frame = new JFrame();
    
    public static void GradientFrame(JFrame frame, Color color1, Color color2) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int width = getWidth();
                int height = getHeight();
                GradientPaint gradient = new GradientPaint(0, 0, color1, width, height, color2);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, width, height);
            }
        };

        frame.setContentPane(contentPane);
        frame.setSize(400, 600);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }
    
    public static void loginmain(){
        GradientFrame(frame, new Color(213, 255, 214), new Color(218, 224, 255));
        JPanel panel = Login.createLoginPanel(frame);
        frame.setTitle("Banking System - Login");
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    
    public static void main(String[] args){        
        accounts = Accounts.createAccountsHashMap();
        loginmain();
        //main.Login.proceedLogin(frame);
    }
}
