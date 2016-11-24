/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mivnematal1;

import java.util.Vector;

    

/**
 * @author LIAT
 * class of the nodes of the graph
 * @param name: the name of the node
 * @param 
 * 
 */

public class Nodes implements Comparable<Nodes> {

    private int name;
    private Vector <Edge> neighbors; //all the edges connected to this node (list of the other node and the weight)
    private double minDistance;//the minimum distance from the given node to this node
    private Nodes parent;//the previus node in the shortest way
    private boolean isDone;

    public Nodes(int name) {
        this.name=name;
        this.minDistance = Integer.MAX_VALUE;
        this.isDone=false;
        this.neighbors = new Vector<>();
    }

    public int getName(){
        return name;
    }
    public double getMinDistance(){
        return minDistance;
    }
    
    public Vector<Edge> getNeighbors(){
        return neighbors;
    }
    
    public Nodes getParent(){
        return parent;
    }
    
    public boolean getIsDone(){
        return isDone;
    }
    
    public void setName(int name){
        this.name=name;
    }
    public void setMinDistance(double dis){
        this.minDistance=dis;
    }
    
    public void setNeighbors(Vector <Edge> neighbors){
        this.neighbors=neighbors;
    }
    
    public void setParent(Nodes parent){
        this.parent=parent;
    }
    
    public void setIsDone(boolean isDone){
        this.isDone=isDone;
    }
    
    
    
    @Override
    public int compareTo(Nodes other) {
        if(this.minDistance>other.minDistance){
            return 1;
        }
        else if(this.minDistance<other.minDistance){
            return -1;
        }
        return 0;
        
    }

}
