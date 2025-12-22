/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.routefinder.db;



/**
 *
 * @author ASUS
 */
public class DBCredentials {
    private final String url;
    private final String user;
    private final String password;
    
    public DBCredentials(String url, String user, String password){
        this.url = url;
        this.user = user;
        this.password = password;
    }
    
    public String getUrl(){
        return this.url;
    }
    
    public String getUser(){
        return this.user;
    }
    
    public String getPassword(){
        return this.password;
    }
}
