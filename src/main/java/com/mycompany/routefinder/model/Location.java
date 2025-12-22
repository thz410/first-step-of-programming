/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.routefinder.model;

import java.util.Objects;

/**
 *
 * @author ASUS
 */
public class Location {
    private int id;
    private String name;
    
    public Location(int id, String name){
        this.id = id;
        this.name = name;
    }
    
    public int getId(){
        return this.id;
    }
    
    public String getName(){
        return this.name;
    }

    @Override
    public String toString() {
       // return "Location{" + "id=" + id + ", name=" + name + '}';
       return "Station: " + this.name;
    }
    
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return id == location.id;
    }
    
    @Override
    public int hashCode(){
        return Objects.hash(id);
    }
    
}
