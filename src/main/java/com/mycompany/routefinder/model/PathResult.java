/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.routefinder.model;

import java.util.List;

/**
 *
 * @author ASUS
 */
public class PathResult {
    private List<Location> path;
    private int totalDistance;
    
    public PathResult(List<Location> path, int totalDistance){
        this.path = path;
        this.totalDistance = totalDistance;
    }
    
    public List<Location> getPath(){
        return this.path;
    }
    
    public int getTotalDistance(){
        return this.totalDistance;
    }
    
    //FormatPath for TextArea
    public String getFormattedPath(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<path.size(); i++){
            sb.append(path.get(i).getName());
            if(i < path.size() - 1){
                sb.append("->");
            }
        }
        return sb.toString();
    }  
}
