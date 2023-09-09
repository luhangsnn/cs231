/*
Luhang Sun
CS 231 Project 9
Graph.java
*/
import java.util.*;

public class Graph {
    protected ArrayList <Vertex> vertices;

    public Graph () {
        this.vertices = new ArrayList<Vertex>();
    }

    public ArrayList <Vertex> getVertex(){
        return this.vertices;
    }

    public void addVertex(Vertex v){
        this.vertices.add(v);
    }

    public int vertexCount (){
        return this.vertices.size();
    }

    // creates a bi-directional link between v1 and v2
    public void addEdge (Vertex v1, Direction dir, Vertex v2) {
        if (!vertices.contains(v1)) {
            vertices.add(v1);
        }
        if (!vertices.contains(v2)){
            vertices.add(v2);
        }

        v1.connect(v2, dir);
        v2.connect(v1, Vertex.opposite(dir));
    }

    public boolean isConnected (Vertex v1, Direction dir, Vertex v2){
        if (v1.getNeighbor(dir) == null) return false;

        if (v1.getNeighbor(dir).equals(v2)) return true;
        return false;
    }

    // implements Dijkstra's algorithm to find a single-source shortest-path
    public void shortestPath (Vertex v0){
        for (Vertex v: vertices){
            v.setCost(Integer.MAX_VALUE);
        }

        // start timing
        // long start = System.currentTimeMillis(); 

        PriorityQueue <Vertex> q = new PriorityQueue <Vertex> ();
        // add the root to the PQ
        v0.setCost(0);
        q.add(v0);

        while (!q.isEmpty()){
            Vertex v = q.remove();
            v.mark();
            // loop through all neighbors of v
            for (Vertex w: v.neighbors()){
                if (w != null && (!w.isMarked()) && v.getCost() + 1 < w.getCost()) {
                    w.setCost(v.getCost() + 1);
                    // avoid duplicates
                    q.remove(w); 
                    q.add(w);
                }
            }
        }
        // long end = System.currentTimeMillis();
        // System.out.println("Time taken using built-in PriorityQueue: " + (end - start));
    }

    public void printCost(){
        for (Vertex v: this.vertices){
            System.out.println(v.getCost());
        }
    }
}