/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 *
 * @author LIAT
 */
public class Graph {

    int numOfNodes;
    int numOfEdges;
    Vertex[] graph;
    PriorityQueue<Vertex> queue;

    public Graph() {
        this.numOfNodes = 0;
        this.numOfEdges = 0;
        this.queue = new PriorityQueue<>();
    }

    public Vertex[] readGraph(String fileName) {
        String s = "";
        int nodeI = 0, nodeJ = 0;
        double weight = 0;
        FileReader in;
        try {
            in = new FileReader(fileName);
            BufferedReader bf = new BufferedReader(in);
            numOfNodes = Integer.valueOf(bf.readLine());
            graph = new Vertex[numOfNodes];////create an array of node that conatains all the graph( nodes and their neighbors)
            for (int j = 0; j < graph.length; j++) {
                graph[j] = new Vertex(j);
            }
            numOfEdges = Integer.valueOf(bf.readLine());
            System.out.println("nodes: " + numOfNodes);
            System.out.println("edges: " + numOfEdges);
            for (int i = 0; i < numOfEdges; i++) {
                s = bf.readLine();
                StringTokenizer st = new StringTokenizer(s);
                nodeI = Integer.valueOf(st.nextToken());
                nodeJ = Integer.valueOf(st.nextToken());
                weight = Double.valueOf(st.nextToken());
                graph[nodeI].neighbors.add(new EdgeDij(graph[nodeJ], weight));//add the edge to the first node list
                graph[nodeJ].neighbors.add(new EdgeDij(graph[nodeI], weight));//add the edge to the second node list
                System.out.println("<" + nodeI + ", " + nodeJ + ", " + weight + " >");
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.graph;
    }

    public static void main(String[] args) {
        Graph g = new Graph();
        g.readGraph("B:\\ליאת\\מדמח\\מבנה תוכנה\\G000.txt");
    }
}

class EdgeDij {

    double weight;
    Vertex vertex;

    public EdgeDij(Vertex v, double w) {
        weight = w;
        vertex = v;
    }
}

class Vertex implements Comparable<Vertex> {

    int name;
    Vector<EdgeDij> neighbors;
    double minDistance;
    Vertex parent;
    boolean isDone = false;

    public Vertex(int name) {
        this.name=name;
        minDistance = Integer.MAX_VALUE;
        this.neighbors = new Vector<>();
    }

    @Override
    public int compareTo(Vertex other) {
        if(this.minDistance>other.minDistance){
            return 1;
        }
        else if(this.minDistance<other.minDistance){
            return -1;
        }
        return 0;

    }

}