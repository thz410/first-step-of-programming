/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.routefinder.db.root;

import com.mycompany.routefinder.db.DatabaseManager;
import com.mycompany.routefinder.logic.CityMap;
import com.mycompany.routefinder.logic.DistanceFirstAlgorithm;
import com.mycompany.routefinder.model.GraphRepository;
import com.mycompany.routefinder.ui.SplashScreen;

/**
 *
 * @author ASUS
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("1. Starting Application...");

        // --- 1. SETUP ---
        DatabaseManager db = new DatabaseManager();
        GraphRepository repo = new GraphRepository(db);
        DistanceFirstAlgorithm algo = new DistanceFirstAlgorithm();

        // --- 2. INJECT ---
        CityMap.getInstance(repo, algo);
        System.out.println("2. Backend Initialized.");

        // --- 3. LAUNCH UI ---
        // This is the part that makes the window appear!
        java.awt.EventQueue.invokeLater(() -> {
            
            // OPTION A: If you want to go straight to the Main App
//            System.out.println("3. Opening MainFrame...");
//            new MainFrame().setVisible(true);
            
            // OPTION B: If you want the Splash Screen first
             new SplashScreen().setVisible(true);
        });
    }
}
