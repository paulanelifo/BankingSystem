package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.util.HashMap;
import static main.Main.frame;
import static main.Dashboard.createDashboardPanel;

public class Login {
    private static HashMap<Integer, HashMap<String, Object>> accounts = Main.accounts;
    
    public static JFrame newFrame = new JFrame("Dashboard");
    public static void proceedLogin(JFrame frame) {
        frame.getContentPane().removeAll(); // Remove the previous contents
        // Create a new frame with the dashboard panel
        Main.GradientFrame(newFrame, new Color(218, 224, 255), new Color(90,133,255)); // Apply gradient
        newFrame.setTitle("Banking System - Dashboard");
        newFrame.add(createDashboardPanel(frame)); // Add the dashboard panel to the new frame
        newFrame.setLocationRelativeTo(null); // Center the new frame
        newFrame.setVisible(true); // Make the new frame visible
        frame.dispose(); // Dispose the old frame
    }
    
    public static void proceedRegister(){
        frame.getContentPane().removeAll(); // Remove previous contents
        JPanel panel = Register.createRegisterPanel(frame); // Create new panel
        frame.setTitle("Banking System - Register");
        frame.add(panel); // Add the new panel
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true); // Make the frame visible
    }
    
    public static JPanel createLoginPanel(JFrame frame) {
        Main.currentid=0;

        JPanel panel = new JPanel(new GridBagLayout()) {
            {
                setOpaque(false); // Make panel transparent
                setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            }

            // Transparency and rounded corners
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                // Set transparency level (0.0f - fully transparent, 1.0f - fully opaque)
                float transparency = 0.25f; // Example: 20% transparent
                g2d.setComposite(AlphaComposite.SrcOver.derive(transparency));

                int radius = 20; // Set your desired corner radius here
                Shape borderShape = new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
                g2d.setClip(borderShape);
                g2d.setColor(Color.LIGHT_GRAY);
                g2d.fillRect(0, 0, getWidth(), getHeight());
                g2d.dispose();
            }
        };
        // GridBagConstraints for panel components
        GridBagConstraints gbc = new GridBagConstraints(){{
            gridwidth = GridBagConstraints.REMAINDER;
            fill = GridBagConstraints.HORIZONTAL;
            insets = new Insets(5, 5, 5, 5);
        }};   
        
        GridBagConstraints gbct = new GridBagConstraints() {
        {
            gridwidth = GridBagConstraints.REMAINDER;
            fill = GridBagConstraints.HORIZONTAL;
            insets = new Insets(0, 70, 10, 0); // Add space between title and other components
            anchor = GridBagConstraints.CENTER; // Center horizontally
        }};

        // Login Name Label
        JLabel titleLabel = new JLabel("Login"){{
            setFont(new Font("null", Font.PLAIN, 28));
        }};        
        panel.add(titleLabel, gbct);
        
        // Login Name Label
        JLabel nameLabel = new JLabel("Account ID:");
        panel.add(nameLabel, gbc);
        // Login Name TextField
        JTextField nameField = new JTextField(5){{
            setHorizontalAlignment(CENTER);            
            setFont(new Font("null", Font.BOLD, 20));
        }};
        panel.add(nameField, gbc);

        // Password Label
        JLabel passwordLabel = new JLabel("Password:");
        panel.add(passwordLabel, gbc);
        // Password TextField
        JTextField passwordField = new JPasswordField(20);
        panel.add(passwordField, gbc);

        // Login Button
        JButton loginButton = new JButton("Login"){{
            setFocusable(false);
            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String struserid = nameField.getText();
                    int userid = Integer.parseInt(struserid);
                    String password = passwordField.getText();
                    if(accounts.containsKey(userid)) {
                        HashMap<String, Object> userData = accounts.get(userid);
                        String storedPassword = (String) userData.get("password");
                        if(password.equals(storedPassword)) {
                            // Successful login
                            StringBuilder sb = new StringBuilder();
                            sb.append("Login successful");
                            JOptionPane.showMessageDialog(frame, sb);
                            Main.currentid=userid;
                            proceedLogin(frame);
                        } else {
                            // Incorrect password
                            StringBuilder sb = new StringBuilder();
                            sb.append("Incorrect password");
                            JOptionPane.showMessageDialog(frame, sb);
                        }
                    } else {
                        // Username not found
                        StringBuilder sb = new StringBuilder();
                        sb.append("Account ID not found");
                        JOptionPane.showMessageDialog(frame, sb);
                        
                    }
                }
            });
        }};
        panel.add(loginButton, gbc);


        // Register Button
        JButton registerButton = new JButton("Register") {{
            setFocusable(false);

            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    proceedRegister();
                }
            });
        }};
        panel.add(registerButton, gbc);


        return panel;
    }
}
