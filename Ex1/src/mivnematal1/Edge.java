/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mivnematal1;

/**
 *
 * @author LIAT
 */

public class Edge {

    double weight;
    Nodes node;

    public Edge(Nodes v, double w) {
        weight = w;
        node = v;
    }
    
    public double getWeight(){
        return this.weight;
    }
    
    public Nodes getNode(){
        return this.node;
    }
}
