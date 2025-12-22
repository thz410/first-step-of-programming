/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.routefinder.db;




/**
 *
 * @author ASUS
 */
public class DBConfig {
    private static final String DB_NAME = "route_finder";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/" + DB_NAME;
    private static final String DB_USER = "root";
    private static final String DB_PASS = "292326";
    
    public static DBCredentials getCredentials(){
        return new DBCredentials(DB_URL, DB_USER, DB_PASS);
    }
}
