package com.ps;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
public class OrderScreen extends JFrame {
    // Basic variables to keep track of order items
    private String breadType = "";
    private String toppings = "";
    private String sauces = "";
    private boolean toasted = false;
    private String drinkSize = "";
    private String drinkFlavor = "";
    private String chipType = "";
    private double totalPrice = 0.0;
    public OrderScreen(){
        setTitle("Order Screen");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Button for customization and actions
        JButton addSandwichButton = new JButton("Add Sandwich");
        JButton addDrinkButton = new JButton("Add Drink");
        JButton addChipsButton = new JButton("Add Chips");
        JButton checkoutButton = new JButton("Checkout");
        JButton cancelOrderButton = new JButton("Cancel Order");

        // Set button positions
        addSandwichButton.setBounds(120, 30, 150, 30);
        addDrinkButton.setBounds(120, 80, 150, 30);
        addChipsButton.setBounds(120, 130, 150,30);
        checkoutButton.setBounds(120, 180, 150, 30);
        cancelOrderButton.setBounds(120, 230, 150, 30);

        // Add action listener
        addSandwichButton.addActionListener(e -> configureSandwich());
        addDrinkButton.addActionListener(e-> configureDrink());
        addChipsButton.addActionListener(e-> configureChips());
        checkoutButton.addActionListener(e-> checkout());
        cancelOrderButton.addActionListener(e-> cancelOrder());

        // Add buttons to the frame
        add(addSandwichButton);
        add(addDrinkButton);
        add(addChipsButton);
        add(checkoutButton);
        add(cancelOrderButton);

        setLocationRelativeTo(null); // Center the frame
    }
    private void configureSandwich(){
        // Ask for bread type
        breadType = JOptionPane.showInputDialog(this, "Select bread type (White, Wheat, Rye):");

        // Ask for toppings
        toppings = JOptionPane.showInputDialog(this, "Select toppings (Meat, Cheese, Veggies):");

        // Ask for sauces
        sauces = JOptionPane.showInputDialog(this, "Select sauces (Mayo, Mustard, Ketchup):");

        // Ask if the sandwich should be toasted
        int choice = JOptionPane.showConfirmDialog(this, "Would you like the sandwich toasted?", "Toasted?", JOptionPane.YES_NO_OPTION);
        toasted = (choice == JOptionPane.YES_OPTION);

        totalPrice += 5.0; // Example price for a sandwich
    }
    private void configureDrink() {
        // Ask for drink size
        drinkSize = JOptionPane.showInputDialog(this, "Select drink size (Small, Medium, Large):");

        // Ask for drink flavor
        drinkFlavor = JOptionPane.showInputDialog(this, "Select drink flavor (Cola, Lemonade, Water):");

        totalPrice += 2.0; // Example price for a drink
    }
    private void configureChips(){
        // Ask for chip type
        chipType = JOptionPane.showInputDialog(this, "Select chip type (Original, BBQ, Sour Cream):");

        totalPrice += 1.5; // Example price for chips
    }
    private void checkout(){
        // Display order summary
        String summary = "Order Summary:\n"+
                "Sandwich: " + breadType + "bread, Toppings: " + toppings + ", Sauces: " + sauces +
                (toasted ? " (Toasted)" : "") + "\n" +
                "Drink: " + drinkSize + " " + drinkFlavor + "\n" +
                "Chips: " + chipType + "\n" +
                "Total Price: $" + totalPrice;
        JOptionPane.showMessageDialog(this, summary);

        // Confirm order and create receipt file
        int confirm = JOptionPane.showConfirmDialog(this, "Confirm order?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION){
            createReceipt(summary);
            goBackToHome();
        }
    }
    private void createReceipt(String summary){
        try (FileWriter writer = new FileWriter("receipt.txt")){
            writer.write(summary);
            JOptionPane.showMessageDialog(this, "Receipt saved to receipt.txt");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving receipt.");
        }
    }
    private void cancelOrder(){
        // Reset all variables and go back to home
        breadType = "";
        toppings = "";
        sauces = "";
        toasted = false;
        drinkSize = "";
        drinkFlavor = "";
        chipType = "";
        totalPrice = 0.0;
        JOptionPane.showMessageDialog(this, "Order canceled. Returning to home screen.");
        goBackToHome();
    }
    private void goBackToHome(){
        dispose(); // Close the current window
        new HomeScreen().setVisible(true); // Open the home screen
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            OrderScreen orderScreen = new OrderScreen();
            orderScreen.setVisible(true);
        });
    }
    static class HomeScreen extends JFrame {
        public HomeScreen(){
            setTitle("Home Screen");
            setSize(300, 200);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(null);
            JButton newOrderButton = new JButton("New Order");
            JButton exitButton = new JButton("Exit");

            newOrderButton.setBounds(100, 50, 100, 30);
            exitButton.setBounds(100, 100,100, 30);

            newOrderButton.addActionListener(e -> {
                dispose(); // Close the home screen
                new OrderScreen().setVisible(true); // Open the order screen
            });
            exitButton.addActionListener(e -> System.exit(0));

            add(newOrderButton);
            add(exitButton);
            setLocationRelativeTo(null);
        }
        public static void main(String[] args){
            SwingUtilities.invokeLater(()-> {
                HomeScreen homeScreen = new HomeScreen();
                homeScreen.setVisible(true);

            });
        }
    }

    public void setVisible(boolean b) {
    }

}