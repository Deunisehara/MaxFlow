/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algocw;

/**
 *
 * @author Deuni Chandraratne
 * w1790190
 * 2019740
 * Group H
 */





import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.LinkedList;

public class AlgoCw {


 
    static int P; // Number of nodes in graph
 
    boolean bfs(int rGraph[][], int s, int t, int parent[]) 
    {
        // array is made when visited 
        
        boolean visited[] = new boolean[P];
        for (int i = 0; i < P; ++i)
            visited[i] = false;
 

        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        visited[s] = true;
        parent[s] = -1;
 
        
        while (queue.size() != 0) {
            int u = queue.poll();
 
            for (int v = 0; v < P; v++) {
                if (visited[v] == false
                    && rGraph[u][v] > 0) {
                    // If there's a connection to the sink node return true
                 
                    if (v == t) {
                        parent[v] = u;
                        return true;
                    }
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }
 
        // If there's no connection to the sink node return false
    
        return false;
    }
 
 
    int fordFulkerson(int graph[][], int s, int t)
    {
        int m, n;

        // residual capacity of edge from m to n 
      
        int rGraph[][] = new int[P][P];
 
        for (m = 0; m < P; m++)
            for (n = 0; n < P; n++)
                rGraph[m][n] = graph[m][n];
  
 
        // Use to store path
        int[] root = new int[P];
 
        int max_flow = 0; // initialize to zero
 
    
        while (bfs(rGraph, s, t, root)) {
           
            //  maximum flow is calculated through the path found.
            int path_flow = Integer.MAX_VALUE;
            for (n = t; n != s; n = root[n]) {
                m = root[n];
                path_flow
                    = Math.min(path_flow, rGraph[m][n]);
            }
 
            // new residual capacities 
            for (n = t; n != s; n = root[n]) {
                m = root[n];
                rGraph[m][n] -= path_flow;
                rGraph[n][m] += path_flow;
            }
 
            // maimum flow=  path flow + overall flow
            max_flow += path_flow;
        }
 
        // maximum flow is returned
        return max_flow;
    }
 
 
    public static void main(String[] args)
        throws java.lang.Exception
    {
        Scanner formatInputFile = null;
        
	try {
            formatInputFile = new Scanner(new File(args[0]));
        } 
        catch (FileNotFoundException ex) {
            System.out.println("Error!, File not Found...Please Try again !");
            
        }
        
        ArrayList<Integer> mainArray = new ArrayList();
        ArrayList<Integer> node1Array = new ArrayList();
        ArrayList<Integer> node2Array = new ArrayList();
        ArrayList<Integer> node3Array = new ArrayList();
        
        while (formatInputFile.hasNextInt()) {
            int data = formatInputFile.nextInt();
            mainArray.add(data);
        }
        
        
        
        System.out.println("No of nodes + List" + mainArray);
        
        
        P = mainArray.get(0);
        mainArray.remove(0);
        
        System.out.println("List" + mainArray);
        
        // printing the list into 3 sections
        for(int i=0; i<mainArray.size(); i= i+3) {
            node1Array.add(mainArray.get(i));
            node2Array.add(mainArray.get(i+1));
            node3Array.add(mainArray.get(i+2));
        
        }
      
        
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(node1Array);
        
        System.out.println(node2Array);
       
        System.out.println(node3Array);
        
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
        
        System.out.println("Total number of nodes: "+P);
        
        System.out.println("****************************Graph****************************");
        System.out.println("");
        
        
        int[][] ReGraph = new int[P][P];
 
        for (int i = 0; i < node1Array.size(); i++) {
            ReGraph[node1Array.get(i)][node2Array.get(i)] = node3Array.get(i);
        }
        
        for (int[] row : ReGraph){
                   System.out.println(Arrays.toString(row)); 
                 }  
        AlgoCw max = new AlgoCw();
 
        long start = System.currentTimeMillis();
        System.out.println("The maximum possible flow is " + max.fordFulkerson(ReGraph, 0, P-1));    
        long now = System.currentTimeMillis();
        double elapsed = (now - start) / 1000.0;
        
        System.out.println("Elapsed time : " + elapsed);
       
        
    
    }
}