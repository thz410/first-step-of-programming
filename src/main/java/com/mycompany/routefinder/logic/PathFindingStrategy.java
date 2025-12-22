/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.routefinder.logic;


import com.mycompany.routefinder.model.Location;
import com.mycompany.routefinder.model.PathResult;
import com.mycompany.routefinder.model.Route;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public interface PathFindingStrategy {
    PathResult findPath(Location start, Location end,
            Map<Integer, List<Route>> adjacencyList, Map<Integer, Location> locations);
}
