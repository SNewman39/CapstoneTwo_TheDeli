package com.ps;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
public class OrderScreen extends JFrame {
        private String breadType = "";
        private String toppings = "";
        private String sauces = "";
        private boolean toasted = false;
        private String drinkSize = "";
        private String drinkFlavor = "";
        private String chipType = "";
        private double totalPrice = 0.0;
        public OrderScreen() {
            setTitle("Order Screen");
            setSize(400, 400);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(null); // Ensure layout is set to null
            // Buttons for actions
            JButton addSandwichButton = new JButton("Add Sandwich");
            JButton addDrinkButton = new JButton("Add Drink");
            JButton addChipsButton = new JButton("Add Chips");
            JButton checkoutButton = new JButton("Checkout");
            JButton cancelOrderButton = new JButton("Cancel Order");
            // Set button positions
            addSandwichButton.setBounds(120, 30, 150, 30);
            addDrinkButton.setBounds(120, 80, 150, 30);
            addChipsButton.setBounds(120, 130, 150, 30);
            checkoutButton.setBounds(120, 180, 150, 30);
            cancelOrderButton.setBounds(120, 230, 150, 30);
            // Add action listeners
            addSandwichButton.addActionListener(e -> configureSandwich());
            addDrinkButton.addActionListener(e -> configureDrink());
            addChipsButton.addActionListener(e -> configureChips());
            checkoutButton.addActionListener(e -> checkout());
            cancelOrderButton.addActionListener(e -> cancelOrder());
            // Add buttons to the frame
            add(addSandwichButton);
            add(addDrinkButton);
            add(addChipsButton);
            add(checkoutButton);
            add(cancelOrderButton);
            setLocationRelativeTo(null); // Center the frame
        }
        private void configureSandwich() {
            breadType = JOptionPane.showInputDialog(this, "Select bread type (White, Wheat, Rye):");
            toppings = JOptionPane.showInputDialog(this, "Select toppings (Meat, Cheese, Veggies):");
            sauces = JOptionPane.showInputDialog(this, "Select sauces (Mayo, Mustard, Ketchup):");
            toasted = JOptionPane.showConfirmDialog(this, "Would you like the sandwich toasted?", "Toasted?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            totalPrice += 5.0; // Example price for sandwich
        }
        private void configureDrink() {
            drinkSize = JOptionPane.showInputDialog(this, "Select drink size (Small, Medium, Large):");
            drinkFlavor = JOptionPane.showInputDialog(this, "Select drink flavor (Cola, Lemonade, Water):");
            totalPrice += 2.0; // Example price for drink
        }
        private void configureChips() {
            chipType = JOptionPane.showInputDialog(this, "Select chip type (Original, BBQ, Sour Cream):");
            totalPrice += 1.5; // Example price for chips
        }
        private void checkout() {
            String summary = "Order Summary:\n" +
                    "Sandwich: " + breadType + " bread, Toppings: " + toppings + ", Sauces: " + sauces +
                    (toasted ? " (Toasted)" : "") + "\n" +
                    "Drink: " + drinkSize + " " + drinkFlavor + "\n" +
                    "Chips: " + chipType + "\n" +
                    "Total Price: $" + totalPrice;
            JOptionPane.showMessageDialog(this, summary);
            if (JOptionPane.showConfirmDialog(this, "Confirm order?", "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                createReceipt(summary);
                goBackToHome();
            }
        }
        private void createReceipt(String summary) {
            try (FileWriter writer = new FileWriter("receipt.txt")) {
                writer.write(summary);
                JOptionPane.showMessageDialog(this, "Receipt saved to receipt.txt");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error saving receipt.");
            }
        }
        private void cancelOrder() {
            resetOrder();
            JOptionPane.showMessageDialog(this, "Order canceled. Returning to home screen.");
            goBackToHome();
        }
        private void resetOrder() {
            breadType = "";
            toppings = "";
            sauces = "";
            toasted = false;
            drinkSize = "";
            drinkFlavor = "";
            chipType = "";
            totalPrice = 0.0;
        }
        private void goBackToHome() {
            dispose(); // Close current screen
            new HomeScreen().setVisible(true); // Open home screen
        }
    }

