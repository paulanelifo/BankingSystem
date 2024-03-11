/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.util.HashMap;
import javax.swing.*;
import static main.Login.accounts;
import static main.Login.proceedLogin;
import static main.Main.frame;
import static main.Accounts.addAccount;

/**
 *
 * @author Pololoers
 */
public class Register {
    
    public static void backToLogin(){
        frame.getContentPane().removeAll(); // Remove previous contents
        JPanel panel = Login.createLoginPanel(frame); // Create new panel
        frame.add(panel); // Add the new panel
        frame.getContentPane().revalidate(); // Revalidate the content pane
        frame.getContentPane().repaint(); // Repaint the content pane
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true); // Make the frame visible
    }
    
    public static JPanel createRegisterPanel(JFrame frame) {
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
        
        GridBagConstraints gbcRegisterTitle = new GridBagConstraints() {
        {
            gridwidth = GridBagConstraints.REMAINDER;
            fill = GridBagConstraints.HORIZONTAL;
            insets = new Insets(0, 70, 10, 0); // Add space between title and other components
            anchor = GridBagConstraints.CENTER; // Center horizontally
        }};

        // Login Name Label
        JLabel nameLabel = new JLabel("Username:");
        panel.add(nameLabel, gbc);
        // Login Name TextField
        JTextField nameField = new JTextField(20);
        panel.add(nameField, gbc);
        
        //  Name Label
        JLabel nameLabelname = new JLabel("Account Name:");
        panel.add(nameLabelname, gbc);
        //  Name TextField
        JTextField nameFieldname = new JTextField(20);
        panel.add(nameFieldname, gbc);

        // Password Label
        JLabel passwordLabel = new JLabel("Password:");
        panel.add(passwordLabel, gbc);
        // Password TextField
        JTextField passwordField = new JPasswordField(20);
        panel.add(passwordField, gbc);
        
        // Password Label
        JLabel passwordLabelC = new JLabel("Confirm Password:");
        panel.add(passwordLabelC, gbc);
        // Password TextField
        JTextField passwordFieldC = new JPasswordField(20);
        panel.add(passwordFieldC, gbc);
        
        //  initial deposit
        JLabel nameLabeldepot = new JLabel("Initial Deposit:");
        panel.add(nameLabeldepot, gbc);
        //  Name TextField
        JTextField nameFielddepot = new JTextField(20){{
            setText("500");
        }};
        panel.add(nameFielddepot, gbc);


        JButton registerButton = new JButton("Register") {{
            setFocusable(false);
            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Check if the username is filled
                    String username = nameField.getText().trim();
                    if (username.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Please enter a username.");
                        return;
                    }

                    // Check if the username is already taken
                    if (accounts.containsKey(username)) {
                        JOptionPane.showMessageDialog(frame, "Username already taken. Please choose another one.");
                        return;
                    }

                    // Check if the name is filled
                    String name = nameFieldname.getText().trim();
                    if (name.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Please enter your name.");
                        return;
                    }

                    // Check if the password is at least 4 characters long
                    String password = passwordField.getText();
                    if (password.length() < 4) {
                        JOptionPane.showMessageDialog(frame, "Password must be at least 4 characters long.");
                        return;
                    }

                    // Check if the password matches the confirm password
                    String confirmPassword = passwordFieldC.getText();
                    if (!password.equals(confirmPassword)) {
                        JOptionPane.showMessageDialog(frame, "Passwords do not match. Please re-enter.");
                        return;
                    }
                    
                    Double depot = Double.parseDouble(nameFielddepot.getText());
                    if(depot<500){
                        JOptionPane.showMessageDialog(frame, "Initial deposit must be at least 500.00.");
                        return;
                    }

                    // If all checks pass, proceed with registration
                    // You can add the registration logic here
                    // For demonstration, let's just print the registration details
                    /*
                    variables
                    username
                    name
                    password
                    depot
                    */
                    addAccount(username, name, password, depot);
                    
                    // Optionally, show a message dialog indicating successful registration
                    JOptionPane.showMessageDialog(frame, "Registration successful.");
                    backToLogin();
                }
            });
        }};
        panel.add(registerButton, gbc);

        
        // back Button
        JButton backButton = new JButton("Back To Log In") {{
            setFocusable(false);
            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    backToLogin();
                }
            });
        }};
        panel.add(backButton, gbc);


        return panel;
    }
}
