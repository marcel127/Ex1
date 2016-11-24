/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mivnematal1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LIAT
 */
public class Graph_algo {

    Graph g;
    Nodes[] graph;
    PriorityQueue<Nodes> queue;
    int numOfNodes;
    int numOfEdges;

    public Graph_algo(Nodes[] g, int numOfNodes, int numOfEdges) {
        graph = g;
        queue = new PriorityQueue<>();
        this.numOfEdges = numOfEdges;
        this.numOfNodes = numOfNodes;
    }

    /**
     * calculates all the shorteset ways from a given root with dijkstra
     * algoritm
     *
     * @param root the start node
     */
    public void dijikstraAlgo(int root) {
       
        graph[root].setMinDistance(0);
        queue.add(graph[root]);
        while (!queue.isEmpty()) {
            Nodes v = queue.poll();  //get the min vertex 
            for (int i = 0; i < v.getNeighbors().size(); i++) {//go throgh all the neigbors
                Nodes u = v.getNeighbors().elementAt(i).getNode();
                if (u.getIsDone() == false) {
                    double Uweight = v.getNeighbors().elementAt(i).getWeight();
                    double VUweight = v.getMinDistance() + Uweight;
                    if (VUweight < u.getMinDistance()) {
                        u.setParent(v);
                        u.setMinDistance(VUweight);
                        queue.remove(u);
                        queue.add(u);
                    }
                }

            }
            v.setIsDone(true);
        }
    }

    /**
     * calculate the distance between two given nodes
     *
     * @param nodeI- the first node
     * @param nodeJ- the second node
     * @return the distance between two nodes
     */
    public double distanceBetweenTwoNodes(int nodeI, int nodeJ) {
        reset();
        dijikstraAlgo(nodeI);
        return graph[nodeJ].getMinDistance();
    }

    /**
     * computes the path between two nodes(with the shortest distance)
     *
     * @param nodeI- the first node
     * @param nodeJ- the second node
     * @return Array List with all the nodes in this path
     */
    public ArrayList<Integer> pathBetweenTwoNodes(int nodeI, int nodeJ) {
        reset();
        dijikstraAlgo(nodeI);
        //int node = nodeJ;
        ArrayList<Integer> list = new ArrayList<>();
        list.add(graph[nodeJ].getName());
        while (graph[nodeJ].getParent() != null) {
            list.add(graph[nodeJ].getParent().getName());
            nodeJ = graph[nodeJ].getParent().getName();
        }

        Collections.reverse(list);
        System.out.println("to vertex " + (nodeI + 1) + ": " + list);
        return list;
    }

    /**
     * calculate the distance between two given nodes, without going through the
     * black list
     *
     * @param nodeI- the first node
     * @param nodeJ - the second node
     * @param blackList- the list of nodes we cant go through
     * @return
     */
    public double distanceWithBlackList(int nodeI, int nodeJ, ArrayList<Integer> blackList) {
        reset();
        for (int i = 0; i < blackList.size(); i++) {
            graph[blackList.get(i)].setIsDone(true);
        }
        dijikstraAlgo(nodeI);
        return graph[nodeJ].getMinDistance();
    }

    /**
     * reset the graph to his original values (before the dijkstra algorithm)
     */
    public void reset() {
        for (int i = 0; i < graph.length; i++) {
            graph[i].setMinDistance(Integer.MAX_VALUE);
            graph[i].setParent(null);
            graph[i].setIsDone(false);
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
                if (graph[j].getMinDistance() > max) {
                    node1 = i;
                    node2 = j;
                    max = graph[j].getMinDistance();
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
                if (graph[j].getMinDistance() > tmpMax) {
                    tmpMax = graph[j].getMinDistance();
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
     * @return true if all the triangles in the graph are triangle Inequality,
     * false otherwise
     */
    public boolean isTriangleInequality() {
        boolean TIE = true;
        for (int i = 0; TIE && i < graph.length; i++) {
            reset();
            dijikstraAlgo(i);
            for (int j = 0; TIE && j < this.graph[i].getNeighbors().size(); j++) {
                if (graph[i].getNeighbors().elementAt(j).getNode().getMinDistance() != graph[i].getNeighbors().elementAt(j).getWeight()) {
                    TIE = false;
                }
            }
        }
        return TIE;
    }

    /**
     * 
     * @return string with the statistics of the graph
     * (number of nodes, number of edges, radius, diameter, is there is a TIE)
     */
    public String getStatistics() {
        double diameter = 0, radius = 0;
        radius = findRadius()[2];
        diameter = findDiameter()[2];
        String isTie= "!TIE";
        if (isTriangleInequality()) {
            isTie = "TIE";
        }
        return "Graph: |V|= " + this.numOfNodes + ", |E| = " + this.numOfEdges + ", " + isTie + ", Radius:" + radius + ", diameter: " + diameter;
    }

    public Vector<Integer>[] readTestFile(String fileName) {
        Vector<Integer>[] queries = null;
        String s = "";
        int numOfQueries, nodeI, nodeJ, BLSize;
        try {
            FileReader in;
            in = new FileReader(fileName);
            BufferedReader bf = new BufferedReader(in);
            numOfQueries = Integer.valueOf(bf.readLine());
            queries = new Vector[numOfQueries];
            for (int i = 0; i < numOfQueries; i++) {
                queries[i] = new Vector<Integer>();
                s = bf.readLine();
                if (s.equals("info")) {
                    queries[i].add(-1);
                } else {
                    StringTokenizer st = new StringTokenizer(s);
                    nodeI = Integer.valueOf(st.nextToken());
                    nodeJ = Integer.valueOf(st.nextToken());
                    BLSize = Integer.valueOf(st.nextToken());
                    queries[i].add(nodeI);
                    queries[i].add(nodeJ);
                    queries[i].add(BLSize);
                    for (int j = 0; j < BLSize; j++) {
                        queries[i].add(Integer.valueOf(st.nextToken()));
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Graph_algo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Graph_algo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return queries;
    }

    public void QandA(String queryFileName, String AnswerFileName) {
        long start = System.currentTimeMillis();
        Vector<Integer>[] queries = readTestFile(queryFileName);
        ArrayList<Integer> blackList;
        int BLPointer = 2;
        double dis;
        String info = "";
        File answerFile = new File(AnswerFileName);
        try {
            FileWriter fw = new FileWriter(answerFile);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < queries.length; i++) {
                if (queries[i].firstElement() == -1) {//if we need the info
                    info = getStatistics();
                } else {
                    for (int k = 0; k < queries[i].size(); k++) {
                        System.out.print(queries[i].get(k) + " ");
                        bw.write(queries[i].get(k)+" ");
                    }
                    blackList = new ArrayList<Integer>();
                    for (int j = 0; j < queries[i].elementAt(BLPointer); j++) {//go throghe the black list
                        blackList.add(queries[i].elementAt(BLPointer + j + 1));
                    }
                    dis = distanceWithBlackList(queries[i].elementAt(0), queries[i].elementAt(1), blackList);
                    bw.write(dis+"\n");
                }
            }
            long end = System.currentTimeMillis();
            info = info + ", Runtime: " + (end - start) + " ms";
           // System.out.println(info);//////////להדפיס לקובץ
            bw.write(info);
            bw.flush();
            bw.close();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    /*
     public static void main(String[] args) {
     Graph g = new Graph();
     Nodes[] tmp = g.readGraphFromFile("B:\\ליאת\\מדמח\\מבנה תוכנה\\G000.txt");
     Graph_algo graph = new Graph_algo(tmp);
     double[] ans = graph.findRadius();
     System.out.println("node1: " + ans[0]);
     System.out.println("node2: " + ans[1]);
     System.out.println("ans: " + ans[2]);
     }
     */
}
