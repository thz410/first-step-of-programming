/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.routefinder.model;

import com.mycompany.routefinder.db.DatabaseManager;
import com.mycompany.routefinder.db.HistoryDataProvider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class GraphRepository {
    private Map<Integer, Location> locationsMap;
    private Map<Integer, List<Route>> adjacencyList;
    private HistoryDataProvider historyProvider;
    
    public GraphRepository(HistoryDataProvider provider){
        this.locationsMap = new HashMap<>();
        this.adjacencyList = new HashMap<>();
        this.historyProvider = provider;
    }
    
    public void clear(){
        locationsMap.clear();
        adjacencyList.clear();
    }
    
    public void addLocation(Location loc){
        locationsMap.put(loc.getId(), loc);
    }
    
    public void addRoute(Route r){
        adjacencyList.putIfAbsent(r.getSourceId(), new ArrayList<>());
            
        adjacencyList.get(r.getSourceId()).add(r);
        
        Route reverseRoute = new Route(r.getDestinationId(), r.getSourceId(), r.getDistance());
        
        adjacencyList.putIfAbsent(reverseRoute.getSourceId(), new ArrayList<>());
        adjacencyList.get(reverseRoute.getSourceId()).add(reverseRoute);
    }
    
    public void saveHistory(String text){
       historyProvider.saveHistory(text);
    }
    
    public List<String> loadHistory(){
        return historyProvider.loadHistory();
    }
    
    public void clearHistory(){
        historyProvider.clearHistory();
    }
    
    public Location getLocation(int id){
        return locationsMap.get(id);
    }
    
    public List<Location> getAllLocations(){
        return new ArrayList<>(locationsMap.values());
    }
    
    public Map<Integer, List<Route>> getAdjacencyList(){
        return this.adjacencyList;
    }
    
    public Map<Integer, Location> getLocationsMap(){
        return locationsMap;
    }   
}
