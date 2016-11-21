/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 *
 * @author LIAT
 */
public class Graph_algo {

    PriorityQueue<Vertex> queue;
    Vertex[] graph;

    public Graph_algo(Vertex[] g) {
        graph = g;
        queue = new PriorityQueue<>();
    }

    public void dijikstraAlgo(int root) {
        graph[root].minDistance = 0;
        queue.add(graph[root]);

        while (!queue.isEmpty()) {
            Vertex v = queue.poll();  //get the min vertex 
            for (int i = 0; i < v.neighbors.size(); i++) {//go throgh all the neigbors
                Vertex u = v.neighbors.elementAt(i).vertex;
                double Uweight = v.neighbors.elementAt(i).weight;
                double VUweight = v.minDistance + Uweight;
                if (VUweight < u.minDistance) {
                    u.parent = v;
                    u.minDistance = VUweight;
                    queue.remove(u);
                    queue.add(u);
                }
            }
            v.isDone = true;
        }
    }

    public double distanceBetweenTwoNodes(int nodeI, int nodeJ) {
        reset();
        dijikstraAlgo(nodeI);
        return graph[nodeJ].minDistance;
    }

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

    public double distanceWithBlackList(int nodeI, int nodeJ, ArrayList<Integer> blackList){////////////????????????????
        reset();
        for(int i=0; i<blackList.size(); i++){
            graph[blackList.get(i)].isDone=true;
        }
        dijikstraAlgo(nodeI);
        return graph[nodeJ].minDistance;
    }
    /*
     reset the graph to his original values
     */
    public void reset() {
        for (int i = 0; i < graph.length; i++) {
            graph[i].minDistance = Integer.MAX_VALUE;
            graph[i].parent = null;
            graph[i].isDone = false;
        }
    }
    public void ss(){
        int a=0;
    }
}
