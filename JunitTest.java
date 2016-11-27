/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ex1.Graph;
import ex1.Graph_algo;
import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marcel
 */
public class JunitTest {
    @Test
       public void test1(){
          String graphFileName="G0.txt";
          Graph g = new Graph();
          Graph_algo test= new Graph_algo(g.readGraphFromFile(graphFileName), g.getNumOfNodes(), g.getNumOfEdges());
           boolean tie=test.isTriangleInequality();
            Object o=test.distanceBetweenTwoNodes(1,3);
           assertEquals(g.getNumOfEdges(),9 );
           assertEquals(g.getNumOfNodes(),6);
           assertEquals(tie,false);
           assertEquals(6.7,o);
      }
       
          @Test
       public void test2(){
          String graphFileName="mediumEWD.txt";
          Graph g = new Graph();
          Graph_algo test= new Graph_algo(g.readGraphFromFile(graphFileName), g.getNumOfNodes(), g.getNumOfEdges());
           assertEquals(g.getNumOfEdges(),2546 );
           assertEquals(g.getNumOfNodes(),250);
           boolean tie=test.isTriangleInequality();
           assertEquals(tie,false);
           double [] a=test.findDiameter();
           Object b=a[2];
              assertEquals(1.37466, b);
          }
       
          @Test
       public void test3(){
          String graphFileName="test33.txt";
          Graph g = new Graph();
          Graph_algo test= new Graph_algo(g.readGraphFromFile(graphFileName), g.getNumOfNodes(), g.getNumOfEdges());
           assertEquals(g.getNumOfEdges(),8);
           boolean tie=test.isTriangleInequality();
           assertEquals(tie,false);
           Object o=test.distanceBetweenTwoNodes(3,1);                                  
           assertEquals(8.0,o);
           ArrayList<Integer> a=new ArrayList<Integer>();
           a.add(3);
           Object b=test.distanceWithBlackList(2,0,a);
              assertEquals(15.0, b);


           
           
        }
        
       @Test
       public void test4(){
          String graphFileName="G0.txt";
          Graph g = new Graph();
          Graph_algo test= new Graph_algo(g.readGraphFromFile(graphFileName), g.getNumOfNodes(), g.getNumOfEdges());
           Object s="Graph: |V|= 6, |E| = 9, !TIE, Radius:4.2, diameter: 6.7";
           assertEquals(s,test.getStatistics());
           Object o=test.findDiameter()[2];
           assertEquals(o, 6.7);
        }
       
          @Test
       public void test5(){
          String graphFileName="test44.txt";
           Graph g = new Graph();
          Graph_algo test= new Graph_algo(g.readGraphFromFile(graphFileName), g.getNumOfNodes(), g.getNumOfEdges());
           assertEquals(g.getNumOfEdges(),8 );
           assertEquals(g.getNumOfNodes(),7);
           boolean tie=test.isTriangleInequality();
           assertEquals(tie,false);
           double [] a=test.findDiameter();
           Object b=a[2];
              assertEquals(20.5, b);
          
        }
     
       
       

   
    
  
}
