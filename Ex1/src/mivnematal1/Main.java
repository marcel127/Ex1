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
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //String graphFileName="B:\\ליאת\\מדמח\\מבנה תוכנה\\G000.txt";
        String graphFileName="B:\\ליאת\\מדמח\\מבנה תוכנה\\tiny0.txt";
        String queryFileName="B:\\ליאת\\מדמח\\מבנה תוכנה\\test0.txt";
        String ansFileName="B:\\ליאת\\מדמח\\מבנה תוכנה\\ans.txt";
         Graph g = new Graph();
        Nodes[] tmp = g.readGraphFromFile(graphFileName);
        Graph_algo graph = new Graph_algo(tmp, g.getNumOfNodes(), g.getNumOfEdges());
        graph.QandA(queryFileName, ansFileName);
        
        
        
//        double[] ans = graph.findRadius();
//        System.out.println("node1: " + ans[0]);
//        System.out.println("node2: " + ans[1]);
//        System.out.println("ans: " + ans[2]);
    }
    
}
