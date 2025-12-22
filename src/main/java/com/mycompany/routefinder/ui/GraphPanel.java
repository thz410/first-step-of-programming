package com.mycompany.routefinder.ui;

import com.mycompany.routefinder.logic.CityMap;
import com.mycompany.routefinder.model.Location;
import com.mycompany.routefinder.model.Route;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JPanel;

public class GraphPanel extends JPanel {
    private final Map<Integer, Point> nodePositions = new HashMap<>();
    private List<Location> currentPath = null; 
    
    public GraphPanel(){
        this.setPreferredSize(new Dimension(600, 400));
        this.setBackground(Color.WHITE);
    }
    
    public void setHighlightPath(List<Location> path){
        this.currentPath = path;
        repaint(); 
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        //Calculate Positions
        calculateNodePositions();
        
        //Draw ALL Gray Edges
        g2.setColor(Color.LIGHT_GRAY);
        g2.setStroke(new BasicStroke(2));
        drawAllEdges(g2);
        
        //Draw Red Highlight Path
        if(currentPath != null && currentPath.size() > 1){
            g2.setColor(Color.RED);
            g2.setStroke(new BasicStroke(4));
            drawPathEdges(g2);
        }
        
        //Draw Nodes
        drawNodes(g2);
    }
    
    private void calculateNodePositions(){
        List<Location> locs = CityMap.getInstance().getAllLocations();
        if(locs.isEmpty()) return;
        
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int radius = Math.min(centerX, centerY) - 60;
        
        for(int i = 0; i < locs.size(); i++){
            double angle = 2 * Math.PI * i / locs.size();
            int x = (int) (centerX + radius * Math.cos(angle));
            int y = (int) (centerY + radius * Math.sin(angle));
            nodePositions.put(locs.get(i).getId(), new Point(x, y));
        }
    }
    
    private void drawAllEdges(Graphics2D g2){
        Map<Integer, List<Route>> adj = CityMap.getInstance().getAdjacencyList();
        if (adj == null || adj.isEmpty()) return;

        for (Map.Entry<Integer, List<Route>> entry : adj.entrySet()) {
            int sourceId = entry.getKey();
            List<Route> routes = entry.getValue();
            Point p1 = nodePositions.get(sourceId);
            
            for (Route r : routes) {
                int destId = r.getDestinationId();
                Point p2 = nodePositions.get(destId);
                
                if (p1 != null && p2 != null) {
                    g2.drawLine(p1.x, p1.y, p2.x, p2.y);
                }
            }
        }
    }
    
    private void drawPathEdges(Graphics2D g2){
        if (currentPath == null || currentPath.size() < 2) return; 
        
        for(int i = 0; i < currentPath.size() - 1; i++){
            Location start = currentPath.get(i);
            Location end = currentPath.get(i + 1);
            
            Point p1 = nodePositions.get(start.getId());
            Point p2 = nodePositions.get(end.getId()); // <--- FIXED: Now gets END position
            
            if(p1 != null && p2 != null){
                g2.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
        }
    }
    
    private void drawNodes(Graphics2D g2) {
        for (Location loc : CityMap.getInstance().getAllLocations()) {
            Point p = nodePositions.get(loc.getId());
            if (p != null) {
                if (currentPath != null && (loc.equals(currentPath.get(0)) || loc.equals(currentPath.get(currentPath.size()-1)))) {
                    g2.setColor(Color.ORANGE);
                } else {
                    g2.setColor(Color.BLUE);
                }
                int r = 30; 
                g2.fillOval(p.x - r/2, p.y - r/2, r, r);
                g2.setColor(Color.BLACK);
                g2.drawString(loc.getName(), p.x - 10, p.y - 20);
            }
        }
    }
}