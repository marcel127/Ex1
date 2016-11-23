/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mivnematal1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 * @author LIAT
 */

public class Graph_algo {

    Nodes[] graph;
    PriorityQueue<Nodes> queue;

    public Graph_algo(Nodes[] g) {
        graph = g;
        queue = new PriorityQueue<>();
    }

    /**
     * calculates all the shorteset ways from a given root with dijkstra algoritm
     * @param root the start node
     */
    public void dijikstraAlgo(int root) {
        graph[root].minDistance = 0;
        queue.add(graph[root]);
        while (!queue.isEmpty()) {
            Nodes v = queue.poll();  //get the min vertex 
            for (int i = 0; i < v.neighbors.size(); i++) {//go throgh all the neigbors
                Nodes u = v.neighbors.elementAt(i).node;
                if (u.isDone == false) {
                    double Uweight = v.neighbors.elementAt(i).weight;
                    double VUweight = v.minDistance + Uweight;
                    if (VUweight < u.minDistance) {
                        u.parent = v;
                        u.minDistance = VUweight;
                        queue.remove(u);
                        queue.add(u);
                    }
                }

            }
            v.isDone = true;
        }
    }

    /**
     * calculate the distance between two given nodes
     * @param nodeI- the first node
     * @param nodeJ- the second node
     * @return  the distance between two nodes
     */
    public double distanceBetweenTwoNodes(int nodeI, int nodeJ) {
        reset();
        dijikstraAlgo(nodeI);
        return graph[nodeJ].minDistance;
    }

    /**
     * computes the path between two nodes(with the shortest distance)
     * @param nodeI- the first node
     * @param nodeJ- the second node
     * @return Array List with all the nodes in this path
     */
    public ArrayList<Integer> pathBetweenTwoNodes(int nodeI, int nodeJ) {
        reset();
        dijikstraAlgo(nodeI);
        //int node = nodeJ;
        ArrayList<Integer> list = new ArrayList<>();
        list.add(graph[nodeJ].name);
        while (graph[nodeJ].parent != null) {
            list.add(graph[nodeJ].parent.name);
            nodeJ = graph[nodeJ].parent.name;
        }

        Collections.reverse(list);
        System.out.println("to vertex " + (nodeI + 1) + ": " + list);
        return list;
    }

    /**
     * calculate the distance between two given nodes, without going through the black list
     * @param nodeI- the first node
     * @param nodeJ - the second node
     * @param blackList- the list of nodes we cant go through
     * @return 
     */
    public double distanceWithBlackList(int nodeI, int nodeJ, ArrayList<Integer> blackList) {
        reset();
        for (int i = 0; i < blackList.size(); i++) {
            graph[blackList.get(i)].isDone = true;
        }
        dijikstraAlgo(nodeI);
        return graph[nodeJ].minDistance;
    }
    
    /**
     * reset the graph to his original values (before the dijkstra algorithm)
     */
    public void reset() {
        for (int i = 0; i < graph.length; i++) {
            graph[i].minDistance = Integer.MAX_VALUE;
            graph[i].parent = null;
            graph[i].isDone = false;
        }
    }

    /**
     * 
     * @return the diameter of the graph
     */
    public double[] findDiameter() {
        double max = 0, min = Integer.MAX_VALUE;
        int node1 = 0, node2 = 0;
        for (int i = 0; i < graph.length; i++) {
            reset();
            dijikstraAlgo(i);
            for (int j = 0; j < graph.length; j++) {
                if (graph[j].minDistance > max) {
                    node1 = i;
                    node2 = j;
                    max = graph[j].minDistance;
                }
            }
        }
        return new double[]{node1, node2, max};
    }

    /**
     * 
     * @return the radius of the graph 
     */
    public double[] findRadius() {
        double min = Integer.MAX_VALUE;
        int node1 = 0, node2 = 0;
        for (int i = 0; i < graph.length; i++) {
            reset();
            dijikstraAlgo(i);
            double tmpMax = 0;
            int tmpNode1 = 0, tmpNode2 = 0;
            for (int j = 0; j < graph.length; j++) {
                if (graph[j].minDistance > tmpMax) {
                    tmpMax = graph[j].minDistance;
                    tmpNode1 = i;
                    tmpNode2 = j;
                }
            }
            if (tmpMax < min) {
                node1 = tmpNode1;
                node2 = tmpNode2;
                min = tmpMax;
            }
        }
        return new double[]{node1, node2, min};
    }

    /**
     * 
     * @return true if all the triangles in the graph are triangle Inequality, false otherwise
     */
    public boolean isTriangleInequality() {
        boolean TIE= true;
        for(int i=0; TIE&& i<graph.length; i++){
            reset();
            dijikstraAlgo(i);
            for(int j=0; TIE && j<this.graph[i].neighbors.size(); j++){
                if(graph[i].getNeighbors().elementAt(j).node.getMinDistance() != graph[i].getNeighbors().elementAt(j).weight){
                    TIE=false;
                }
            }
        }
        return TIE;
    }
   
/*
    public String getStatistics() {
        double diameter = 0, radius = 0, time = 0;
        long start = System.currentTimeMillis();
        diameter= findRadius()[2];
        radius=findRadius()[2];
        String isTie;
        if(isTriangleInequality()){
            isTie="TIE";
        }
        else{
            isTie="!TIE";
        }
        long end = System.currentTimeMillis();
        return "Graph: |V|= " +""+ ", |E| = " +""+ ", " +isTie+ ", Radius:" + radius+",diamter: "+diameter+ ", Runtime: " + (end-start)+ " ms";
    }
*/
    
    /*
    public void readTestFile(String fileName){
        String s = "";
        int numOfQueries;
        FileReader in;
         in = new FileReader(fileName);
            BufferedReader bf = new BufferedReader(in);
        numOfQueries = Integer.valueOf(bf.readLine());
       // int 2nodes[][];//= new int[][];
        
        
        int nodeI = 0, nodeJ = 0;
        double weight = 0;
       // FileReader in;
        try {
//            in = new FileReader(fileName);
//            BufferedReader bf = new BufferedReader(in);
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
    }
    */
    public static void main(String[] args) {
        Graph g = new Graph();
        Nodes[] tmp = g.readGraphFromFile("B:\\ליאת\\מדמח\\מבנה תוכנה\\G000.txt");
        Graph_algo graph = new Graph_algo(tmp);
        double[] ans = graph.findRadius();
        System.out.println("node1: " + ans[0]);
        System.out.println("node2: " + ans[1]);
        System.out.println("ans: " + ans[2]);
    }
}
