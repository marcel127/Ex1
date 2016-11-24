/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mivnematal1;

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
    Nodes[] graph;
    PriorityQueue<Nodes> queue;

    public Graph() {
        this.numOfNodes = 0;
        this.numOfEdges = 0;
        this.queue = new PriorityQueue<>();
    }

    /**
     * the function get the fileName of the graph, reads it from the file and
     * set it in the
     *
     * @param fileName- the name of the file with the graph
     * @return Array of nodes that represent the graph
     */
    public Nodes[] readGraphFromFile(String fileName) {
        String s = "";
        int nodeI = 0, nodeJ = 0;
        double weight = 0;
        FileReader in;
        try {
            in = new FileReader(fileName);
            BufferedReader bf = new BufferedReader(in);
            numOfNodes = Integer.valueOf(bf.readLine());
            graph = new Nodes[numOfNodes];////create an array of node that conatains all the graph( nodes and their neighbors)
            for (int j = 0; j < graph.length; j++) {
                graph[j] = new Nodes(j);
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
                graph[nodeI].neighbors.add(new Edge(graph[nodeJ], weight));//add the edge to the first node list
                graph[nodeJ].neighbors.add(new Edge(graph[nodeI], weight));//add the edge to the second node list
                System.out.println("<" + nodeI + ", " + nodeJ + ", " + weight + " >");
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.graph;
    }

    public Nodes[] getGraph() {
        return this.graph;
    }

    public int getNumOfNodes() {
        return this.numOfNodes;
    }

    public int getNumOfEdges() {
        return this.numOfEdges;
    }

    
}
