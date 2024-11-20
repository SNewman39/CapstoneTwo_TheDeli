package com.ps;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
       // Launch the HomeScreen as the main window
        SwingUtilities.invokeLater(()->{
            HomeScreen homescreen = new HomeScreen();
            homescreen.setVisible(true);
        });
    }
}