package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.util.HashMap;
import static main.Accounts.createAccountsHashMap;
import static main.Main.frame;
import static main.Register.createRegisterPanel;
import static main.Main.GradientFrame;

public class Login {
    public static HashMap<String, HashMap<String, Object>> accounts = createAccountsHashMap();
    
    public static void proceedLogin(JFrame frame){
        frame.dispose();
    }
    
    public static void proceedRegister(){
        frame.getContentPane().removeAll(); // Remove previous contents
        JPanel panel = Register.createRegisterPanel(frame); // Create new panel
        frame.add(panel); // Add the new panel
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true); // Make the frame visible
    }
    
    public static JPanel createLoginPanel(JFrame frame) {
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
        
        GridBagConstraints gbcLoginTitle = new GridBagConstraints() {
        {
            gridwidth = GridBagConstraints.REMAINDER;
            fill = GridBagConstraints.HORIZONTAL;
            insets = new Insets(0, 70, 10, 0); // Add space between title and other components
            anchor = GridBagConstraints.CENTER; // Center horizontally
        }};

        // Login Name Label
        JLabel nameLabel = new JLabel("Login Name:");
        panel.add(nameLabel, gbc);
        // Login Name TextField
        JTextField nameField = new JTextField(20);
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
                    String username = nameField.getText();
                    String password = passwordField.getText();
                    if(accounts.containsKey(username)) {
                        HashMap<String, Object> userData = accounts.get(username);
                        String storedPassword = (String) userData.get("password");
                        if(password.equals(storedPassword)) {
                            // Successful login
                            StringBuilder sb = new StringBuilder();
                            sb.append("Login successful");
                            JOptionPane.showMessageDialog(frame, sb);
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
                        sb.append("Username not found");
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
