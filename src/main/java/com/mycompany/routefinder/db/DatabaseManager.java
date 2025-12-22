/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.routefinder.db;


import com.mycompany.routefinder.model.Route;
import com.mycompany.routefinder.model.Location;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class DatabaseManager implements MapDataProvider, HistoryDataProvider{
    @Override
    public Connection connect() throws SQLException{
        DBCredentials creds = DBConfig.getCredentials();
        return DriverManager.getConnection(creds.getUrl(), creds.getUser(), creds.getPassword());
    }
    
    //Load All Locations (Nodes)
    public List<Location> loadLocations(){
        List<Location> list = new ArrayList<>();
        String query = "SELECT location_id, name FROM locations";
        
        //Try-with-resources (Auto-close the connection)
        try(Connection conn = connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)){
            while(rs.next()){
                int id = rs.getInt("location_id");
                String name = rs.getString("name");
                list.add(new Location(id, name));
            }
            System.out.println("Loaded " + list.size() + "locations.");
        }catch(SQLException e){
            System.err.println("Error loading locations: " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }
    
    //Load All Routes (Edges)
    public List<Route> loadRoutes(){
        List<Route> list = new ArrayList<>();
        String query = "SELECT source_id, destination_id, distance FROM routes";
        
        try(Connection conn = connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)){
            
            while(rs.next()){
                int source = rs.getInt("source_id");
                int dest = rs.getInt("destination_id");
                int dist = rs.getInt("distance");
                list.add(new Route(source, dest, dist));
            }
            System.out.println("Loaded " + list.size() + "routes.");
        }catch(SQLException e){
            System.err.println("Error loading routes: "+ e.getMessage());
            e.printStackTrace();
        }
        return list;
    }
    
    @Override
    public void saveHistory(String text){
        String sql = "INSERT INTO search_history (log_entry) VALUES (?)";
        
        try(Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            
            stmt.setString(1, text);
            stmt.executeUpdate();
        }
        catch(SQLException e){
            System.err.println("Error saving history: " + e.getMessage());
        }
    }
    
    @Override
    public List<String> loadHistory() {
        List<String> logs = new ArrayList<>();
        String sql = "SELECT log_entry FROM search_history ORDER BY id ASC";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                logs.add(rs.getString("log_entry"));
            }
        } catch (SQLException e) {
            System.err.println("Error loading history: " + e.getMessage());
        }
        return logs;
    }
    
    @Override
    public void clearHistory(){
        String sql = "DELETE FROM search_history";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.err.println("Error clearing history: " + e.getMessage());
        }
    }
}


