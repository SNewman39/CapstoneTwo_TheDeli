package com.ps;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class HomeScreen extends JFrame {
   public HomeScreen(){
       // set up the frame
       setTitle("Home Screen");
       setSize(300, 200);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setLayout(null);

       // Create button
       JButton newOrderButton = new JButton("New Order");
       JButton exitButton = new JButton("Exit");

       // Set button positions
       newOrderButton.setBounds(100, 50, 100, 30);
       exitButton.setBounds(100, 100, 100, 30);

       // Add action listeners for buttons
       newOrderButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e ){
               dispose(); // Close the home screen
               new OrderScreen().setVisible(true); // Open the order screen
           }
       });

       exitButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
            System.exit(0); // Exit the application
        }
       });
       // Add buttons to the frame
       add(newOrderButton);
       add(exitButton);

       setLocationRelativeTo(null); // Center the frame on the screen
   }
   public static void main(String[] args){
       // Display the home screen
       SwingUtilities.invokeLater(() -> {
           HomeScreen homeScreen = new HomeScreen();
           homeScreen.setVisible(true);
       });
   }
}