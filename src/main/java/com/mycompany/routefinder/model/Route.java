/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.routefinder.model;

/**
 *
 * @author ASUS
 */
public class Route {
    private int sourceId;
    private int destinationId;
    private int distance;
    
    public Route(int sourceId, int destinationId, int dist){
        this.sourceId = sourceId;
        this.destinationId = destinationId;
        this.distance = dist;
    }
    
    public int getSourceId(){
        return this.sourceId;
    }
    
    public int getDestinationId(){
        return this.destinationId;
    }
    
    public int getDistance(){
        return this.distance;
    }
    
    @Override
    public String toString(){
        return "Route(" + sourceId + " -> " + destinationId + ": " + distance + "km)";
    }
}
