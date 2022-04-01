/*
CPCS324 - Algorithms and Data Structures 2
Project Part 1
Group Members:
Renad Saud Alsulami - 1905991 - DAR
Rimas Ali Alqahtani - 1905479 - DAR
Hanan Abdullah Tayeb - 1905913 - BAR
 */
package cpcs324_project_part1;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class KruskalAlg extends MSTAlgorithm {

    //start timing
    double startTime = System.currentTimeMillis();
    String treeVetices = "";
    LinkedList<Edge>[] Edges = Vertex.adjList.clone();
    PriorityQueue<Edge> pq = new PriorityQueue<>(Graph.edgeNo, Comparator.comparingInt(o -> o.getWeight()));

////////////////////////////////**Constructor**////////////////////////////////////////////////////////////
    public KruskalAlg(Graph graph) {
        for (LinkedList<Edge> allEdge : Edges) {
            for (int j = 0; j < allEdge.size(); j++) {
                pq.add(allEdge.get(j));
            }
        }

        //create a parent []
        Edge.parent = new int[graph.veticesNo];

        //makeset
        Edge.makeSet(Edge.parent);

        LinkedList<Edge> MST = new LinkedList<>();

        //process vertices - 1 edges
        int index = 0;
        while (index < graph.veticesNo - 1 && !pq.isEmpty()) {
            Edge edge = pq.remove();
            //check if adding this edge creates a cycle
            int x_set = findParent(Edge.parent, edge.getSource());
            int y_set = findParent(Edge.parent, edge.getTarget());

            if (x_set == y_set) {
                //ignore, will create cycle
            } else {
                //add it to our final result
                MST.add(edge);
                treeVetices += edge.toString() + "\n";
                index++;
                union(Edge.parent, x_set, y_set);
            }

        }

        //finish time of the algorithm
        double ftime = System.currentTimeMillis();
        //print the total time consumed by the algorithm
        System.out.println("Total runtime of Kruskal's Algorithm: " + (ftime - startTime) + " ms.");
        //print MST
        printGraph(MST);
    }
    
////////////////////////////////**union**////////////////////////////////////////////////////////////

    private void union(int[] parent, int x, int y) {
        int setXparent = findParent(parent, x);
        int setYparent = findParent(parent, y);
        //make x as parent of y
        parent[setYparent] = setXparent;
    }
    
////////////////////////////////**findParent**////////////////////////////////////////////////////////////

    private int findParent(int[] parent, int vertex) {
        //chain of parent pointers from x upwards through the tree
        // until an element is reached whose parent is itself (ROOT)
        if (parent[vertex] == vertex) {
            return vertex;
        }
        return findParent(parent, parent[vertex]);
    }
    
////////////////////////////////**printGraph**////////////////////////////////////////////////////////////

    private void printGraph(LinkedList<Edge> edgeList) {
        int cost = 0;
        for (int i = 0; i < edgeList.size(); i++) {
            Edge edge = edgeList.get(i);
            cost += edge.getWeight();
        }
        System.out.println("Minimum Spanning Tree Cost = " + cost);
    }
}
