
package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
import java.text.DecimalFormat;


public class Dashboard {
    public static int userID = Main.currentid;
    public static String name = Accounts.getName(userID);
    public static double money = Accounts.getBalance(userID);
    public static void refreshVars(){
        userID = Main.currentid;
        name = Accounts.getName(userID);
        money = Accounts.getBalance(userID);
    }
    // Method to deposit money into the current user's account
    public static void deposit(double amount) {
        Accounts.deposit(userID, amount);
        // Update the displayed balance
        money = Accounts.getBalance(userID);
        reloadFrame();
    }
    // Method to deposit money into the current user's account
    public static void withdraw(double amount) {
        Accounts.withdraw(userID, amount);
        // Update the displayed balance
        money = Accounts.getBalance(userID);
        reloadFrame();
    }
    // Method to transfer money from the current user's account to another account
    public static void transfer(int receiverID, double amount) {
        Accounts.transfer(userID, receiverID, amount);
        // Update the displayed balance
        money = Accounts.getBalance(userID);
        reloadFrame();
    }
    public static void changePass(String currentPassword, String newPassword){
        Accounts.changePassword(userID, currentPassword, newPassword);
        reloadFrame();
    }
    
    // Method to reload the frame
    public static void reloadFrame() {
        Main.frame.dispose(); // Dispose of the current frame
        main.Login.proceedLogin(Main.frame);
    }
    
    private static JLabel createLine(Color color, int horizontalLength, int pixelWidth) {
        return new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(color);
                int y = getHeight() / 2;
                g2d.drawLine(0, y, horizontalLength, y);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(horizontalLength, pixelWidth);
            }
        };
    }
    
    public static JPanel createDashboardPanel(JFrame frame) {
        refreshVars();
        
        
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
                int radius = 20; // Set your desired corner radius here
                
                Shape borderShape = new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
                g2d.setClip(borderShape);
                g2d.setColor(Color.white);
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
        JLabel welcomeLabel = new JLabel("<html><span style='font-weight: normal;'>Welcome,</span> <span style='font-weight: bold; font-size:25;'>" + name + "<span style='font-weight: normal;'>!</span></span></html>");
        panel.add(welcomeLabel, gbc);

        panel.add(createLine(Color.black, 300, 1), gbc);
        
        JLabel avBal = new JLabel("Available Balance"){{
            setFont(new Font("null", Font.PLAIN, 13));
        }};
        panel.add(avBal,gbc);
        JPanel panelaa = new JPanel();
        currencyPaneler(panelaa);
        panel.add(panelaa,gbc);
        
        panel.add(createLine(Color.black, 300,1),gbc);
        
        // Reset GridBagConstraints for panelButtons
        gbc = new GridBagConstraints() {{
            gridwidth = GridBagConstraints.REMAINDER;
            fill = GridBagConstraints.HORIZONTAL;
            insets = new Insets(5, 5, 5, 5);
        }};   
                
        JPanel panelButtons = new JPanel();
        panelbut(panelButtons);
        panel.add(panelButtons, gbc);
        
        JButton btnLogout = new JButton("Log Out"){{
            setFocusable(false);
            addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    JOptionPane.showMessageDialog(frame, "Session Time Out!", "Logged Out", JOptionPane.WARNING_MESSAGE);
                    main.Register.backToLogin();
                    main.Login.newFrame.dispose();
                    Main.currentid=0;
                }
            });
        }};
        panel.add(btnLogout,gbc);
                
        return panel;
    }
    
public static JPanel panelbut(JPanel panelb) {
    panelb.setLayout(null); // Set layout to null for absolute positioning
    //panelb.setBackground(Color.red);
    panelb.setOpaque(false);
    panelb.setPreferredSize(new Dimension(300, 200));

    //x y w h

    JButton button1 = new JButton("Withdraw Money") {{
        setFocusable(false);
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show input dialog to get the amount to withdraw
                String amountInput = JOptionPane.showInputDialog(Main.frame, "Enter amount to withdraw:");
                if (amountInput != null && !amountInput.isEmpty()) {
                    try {
                        // Parse the input to a double
                        double amount = Double.parseDouble(amountInput);
                        if (amount >= 1) {
                            // Check if the amount to withdraw is greater than the available balance
                            if (amount > Accounts.getBalance(userID)) {
                                JOptionPane.showMessageDialog(Main.frame, "Insufficient funds!");
                            } else {
                                // Call the withdraw method from Accounts class
                                withdraw(amount);
                                JOptionPane.showMessageDialog(Main.frame, "You have successfully withdrawn \u20B1" + amountInput + "!");
                            }
                        } else {
                            JOptionPane.showMessageDialog(Main.frame, "Please enter a valid amount (must be greater than or equal to 1).");
                        }
                    } catch (NumberFormatException ex) {
                        // Handle invalid input
                        JOptionPane.showMessageDialog(Main.frame, "Invalid amount entered. Please enter a valid number.");
                    }
                } else {
                    // Handle cancel or empty input
                    JOptionPane.showMessageDialog(Main.frame, "Withdrawal cancelled or no amount entered.");
                }
            }
        });
    }   };
    button1.setBounds(10, 10, 135, 35); // Set bounds for button 1
    panelb.add(button1);


    JButton button2 = new JButton("Deposit Money") {{
        setFocusable(false);
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amountInput = JOptionPane.showInputDialog(Main.frame, "Enter amount to deposit:");
                if (amountInput != null && !amountInput.isEmpty()) {
                    try {
                        double amount = Double.parseDouble(amountInput);
                        if (amount >= 1) {
                            deposit(amount);
                            JOptionPane.showMessageDialog(Main.frame, "You have successfully deposited \u20B1" + amountInput + "!");
                        } else {
                            JOptionPane.showMessageDialog(Main.frame, "Please enter a valid amount (must be greater than or equal to 1).");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(Main.frame, "Invalid amount entered. Please enter a valid number.");
                    }
                } else {
                    JOptionPane.showMessageDialog(Main.frame, "Deposit cancelled or no amount entered.");
                }
            }
        });
    }};
    button2.setBounds(155, 10, 135, 35); // Set bounds for button 2
    panelb.add(button2);

    JButton button3 = new JButton("Transfer Money") {{
        setFocusable(false);
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String receiverID = JOptionPane.showInputDialog(Main.frame, "Enter receiver's Account ID:");
                int intreceiverID = Integer.parseInt(receiverID);
                if (receiverID != null && !receiverID.isEmpty()) {
                    String amountInput = JOptionPane.showInputDialog(Main.frame, "Enter amount to transfer:");
                    if (amountInput != null && !amountInput.isEmpty()) {
                        try {
                            double amount = Double.parseDouble(amountInput);
                            if (amount > 0) {
                                // Call the transfer method from Accounts class
                                transfer(intreceiverID, amount);
                                JOptionPane.showMessageDialog(Main.frame, "Successful transfer of \u20b1" + amountInput + " to " + Accounts.getName(intreceiverID) + ".");
                            } else {
                                JOptionPane.showMessageDialog(Main.frame, "Please enter a valid amount.");
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(Main.frame, "Invalid amount entered. Please enter a valid number.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(Main.frame, "Transfer cancelled or no amount entered.");
                    }
                } else {
                    JOptionPane.showMessageDialog(Main.frame, "Transfer cancelled or no receiver username entered.");
                }
            }
        });
    }};
    button3.setBounds(10, 55, 135, 35); // Set bounds for button 3
    panelb.add(button3);

    return panelb;
}

public static JPanel currencyPaneler(JPanel currencyPanel){
// Create panel to contain peso sign and amount label
currencyPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
currencyPanel.setOpaque(false); // Make panel transparent

JLabel pesosign = new JLabel("\u20B1"){{
    setFont(new Font("null", Font.BOLD, 28));
}};
// Add peso sign to currencyPanel
currencyPanel.add(pesosign);

        
        
// Add amount label to currencyPanel
String moneyString = String.format("%.2f", money);
        DecimalFormat formatter = new DecimalFormat("#,###.00");
        String formattedNumber = formatter.format(money);
        if (formattedNumber.equals(".00")){
            formattedNumber="0.00";
        }
JLabel amountLabel = new JLabel(formattedNumber); // Example amount, replace with actual value
amountLabel.setFont(new Font("null", Font.BOLD, 28)); // Set font to match peso sign
currencyPanel.add(amountLabel);

return currencyPanel;
}


}
