/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.routefinder.logic;

import com.mycompany.routefinder.model.PathResult;
import com.mycompany.routefinder.model.Route;
import com.mycompany.routefinder.model.Location;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;


/**
 *
 * @author ASUS
 */
public class DistanceFirstAlgorithm implements PathFindingStrategy{
    private class RouteNode implements Comparable<RouteNode>{
        int id;
        int distanceSoFar;
        int stopSoFar;
        
        public RouteNode(int id, int distance, int stops){
            this.id = id;
            this.distanceSoFar = distance;
            this.stopSoFar = stops;
        }
        
        @Override
        public int compareTo(RouteNode other){
            if(this.distanceSoFar != other.distanceSoFar){
                return Integer.compare(this.distanceSoFar, other.distanceSoFar);
            }else{
                return Integer.compare(this.stopSoFar, other.stopSoFar);
            }
        }
    } 
   
    //The Distance-First Algorithm
    @Override
    public PathResult findPath(Location startNode, Location endNode,
            Map<Integer, List<Route>> adjacencyList, Map<Integer, Location> allLocations) {
        
        PriorityQueue<RouteNode> pq = new PriorityQueue<>();
        
        Map<Integer, Integer> bestDist = new HashMap<>();
        Map<Integer, Integer> bestStops = new HashMap<>();
        Map<Integer, Integer> parentMap = new HashMap<>();
        
        
        pq.add(new RouteNode(startNode.getId(), 0, 0));
        bestDist.put(startNode.getId(), 0);
        bestStops.put(startNode.getId(), 0);
        parentMap.put(startNode.getId(), null);
        
        boolean found = false;
        
        //keep run until there are unexplored paths
        while(!pq.isEmpty()){
            RouteNode current = pq.poll();
            
           //If slow way, continue to next loop
            if(current.distanceSoFar > bestDist.getOrDefault(current.id, Integer.MAX_VALUE)){
                continue;
            }
            
            //Target Node
            if(current.id == endNode.getId()){
                found = true;
                break;
            }
            
            //Get neighbors
            List<Route> neighbors = adjacencyList.getOrDefault(current.id, new ArrayList<>());
            
            for(Route route : neighbors){
                int neighborId = route.getDestinationId();
                int newDist = current.distanceSoFar + route.getDistance();
                int newStops = current.stopSoFar + 1;
                
                //Is this new path better?
                boolean isShorter = newDist < bestDist.getOrDefault(neighborId, Integer.MAX_VALUE);
                boolean isSameDistButFewerStops = (newDist == bestDist.getOrDefault(neighborId, Integer.MAX_VALUE))
                        && (newStops < bestStops.getOrDefault(neighborId, Integer.MAX_VALUE));
                
                if(isShorter || isSameDistButFewerStops){
                    bestDist.put(neighborId, newDist);
                    bestStops.put(neighborId, newStops);
                    parentMap.put(neighborId, current.id);
                    
                    pq.add(new RouteNode(neighborId, newDist, newStops));
                }
            }
        }
        
        if(found){
            return buildPath(endNode.getId(), parentMap, allLocations, adjacencyList);
        }
        else{
            return null;
        }
    }

    private PathResult buildPath(int endId, Map<Integer, Integer> parentMap,
                        Map<Integer, Location> allLocations,
                        Map<Integer, List<Route>> adjacencyList){
        LinkedList<Location> path = new LinkedList<>();
        int distance = 0;
        Integer current = endId;
        
        //from End -> Start
        while(current != null){
            path.addFirst(allLocations.get(current));
            
            Integer parent = parentMap.get(current);
            if(parent != null){
                // Find the distance between Parent and Current
                distance += getDistanceBetween(parent, current, adjacencyList);
            }
            current = parent;
        }
        return new PathResult(path, distance);
    }
   
    private int getDistanceBetween(int sourceId, int targetId, Map<Integer, List<Route>> adjacencyList){
        for(Route r : adjacencyList.get(sourceId)){
            if(r.getDestinationId() == targetId){
                return r.getDistance();
            }
        }
        return 0;
    }
}
