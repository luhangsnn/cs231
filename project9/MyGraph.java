/*
Luhang Sun
CS 231 Project 9
MyGraph.java
*/

public class MyGraph extends Graph {

    public MyGraph () {
        super();
    }

    // implements Dijkstra's algorithm using my own PQ
    public void shortestPath (Vertex v0){
        System.out.println("using MyGraph!");
        for (Vertex v: vertices){
            v.setCost(Integer.MAX_VALUE);
        }

        //start timing
        // long start = System.currentTimeMillis();

        // add all vertices to the my own priority queue
        PQHeap <Vertex> q = new PQHeap<Vertex>(new VertexComparator());
        // add the root to the arraylist
        v0.setCost(0);
        q.add(v0);

        while (q.size() != 0){
            Vertex v = q.remove();
            v.mark();
            // loop through all neighbors of v
            for (Vertex w: v.neighbors()){
                if (w != null && (!w.isMarked()) && v.getCost() + 1 < w.getCost()) {
                    w.setCost(v.getCost() + 1);
                    q.add(w);
                }
            }
        }
        // long end = System.currentTimeMillis();
        // System.out.println("Time taken using my own PQHeap (ms): " + (end - start));
    }
}