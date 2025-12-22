/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.routefinder.db;


import com.mycompany.routefinder.model.Route;
import com.mycompany.routefinder.model.Location;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface MapDataProvider {
    Connection connect() throws SQLException;
    List<Location> loadLocations();
    List<Route> loadRoutes();
}
