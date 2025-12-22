/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.routefinder.logic;

import com.mycompany.routefinder.db.MapDataProvider;
import com.mycompany.routefinder.model.GraphRepository;
import com.mycompany.routefinder.model.PathResult;
import com.mycompany.routefinder.model.Route;
import com.mycompany.routefinder.model.Location;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class CityMap {
    
    //Singleton CityMap
    private static CityMap instance;
    private PathFindingStrategy pathFinder;
    private final GraphRepository repository;
    
    public CityMap(GraphRepository repository, PathFindingStrategy pathFinder){
        this.repository =  repository;                         
        this.pathFinder =  pathFinder;
    }
    
    public void setAlgorithm(PathFindingStrategy newAlgorithm){
        this.pathFinder = newAlgorithm;
    }
    
    //Get the single instance
    public static CityMap getInstance(GraphRepository repo, PathFindingStrategy strategy){
        if(instance == null){
            instance = new CityMap(repo, strategy);
        }
        return instance;
    }
    
    public static CityMap getInstance(){
        if(instance == null){
           throw new IllegalStateException("CityMap hase not been initialized! Check Main.java.");
        }
        return instance;
    }
    
    public void loadData(MapDataProvider provider){
        System.out.println("CityMap: Requesting data from DB...");
        
        repository.clear();
        
        //Get Raw Lists Data
        List<Location> locs = provider.loadLocations();
        List<Route> routes = provider.loadRoutes();
        
        for(Location loc : locs){
            repository.addLocation(loc);
        }
        
        for (Route r : routes) {
           repository.addRoute(r);    
        }
        
        System.out.println("CityMap: Graph built!");
    }
    
    public void recordSearch(Location start, Location end, PathResult result){
        //Current Time
        String time = java.time.LocalTime.now().withNano(0).toString();
        
        //Crate Route String
        String routeInfo = " " + start.getName() + " -> " + end.getName();
        
        String distanceInfo = "";
        
        if(result != null){
            distanceInfo = result.getTotalDistance() + "km";
        }
        else{
            distanceInfo = "No Path Found";
        }
        
        String finalLog = "[" + time + "]" + routeInfo + ": " + distanceInfo;
        
        repository.saveHistory(finalLog);
        
        System.out.println("Saved: " + finalLog);
    }
        
    public List<Location> getAllLocations(){
        return repository.getAllLocations();
    }
    
    public PathResult findShortestPath(Location start, Location end){
        return pathFinder.findPath(start, end, repository.getAdjacencyList(), repository.getLocationsMap());
    }
    
    public Map<Integer, List<Route>> getAdjacencyList(){
        return repository.getAdjacencyList();
    }
    
    public List<String> getSearchHistory(){
        return repository.loadHistory();
    }
    
    public void clearAllHistory(){
        repository.clearHistory();
    }
}
