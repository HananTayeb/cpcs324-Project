/*
CPCS324 - Algorithms and Data Structures 2
Project Part 1
Group Members:
Renad Saud Alsulami - 1905991 - DAR
Rimas Ali Alqahtani - 1905479 - DAR
Hanan Abdullah Tayeb - 1905913 - BAR
 */
package cpcs324_project_part1;

import java.util.LinkedList;
import java.util.Random;

public class Graph {

    /**
     * Vertices number
     */
    static int veticesNo;

    /**
     * Edges number
     */
    static int edgeNo;

    /**
     * Directed graph
     */
    boolean isDigraph;

    ////////////////////////////////**Constructor**////////////////////////////////////////////////////////////
    Graph(int veticesNo, int edgeNo) {
        this.veticesNo = veticesNo;
        this.edgeNo = edgeNo;
        Vertex.adjList = new LinkedList[veticesNo];
        //initialize adjacency lists for all the verts
        for (int i = 0; i < veticesNo; i++) {
            Vertex.adjList[i] = new LinkedList<>();
        }
    }

////////////////////////////////**makeGraph**////////////////////////////////////////////////////////////
    public void makeGraph(Graph graph) {
        // object of Random class
        Random randm = new Random();
        // ensure that all verts are connected
        for (int i = 0; i < veticesNo - 1; i++) {
            int weight = randm.nextInt(20) + 1;//generate random edge weights between 0-20
            addEdge(i, i + 1, weight); //connect verts
            if (!graph.isDigraph) {
                addEdge(i + 1, i, weight);
            }
        }

        // generate edges bewteen verts with the remaining edges
        int remEdges = edgeNo - (veticesNo - 1);

        for (int i = 0; i < remEdges; i++) {
            int source = randm.nextInt(graph.veticesNo);
            int target = randm.nextInt(graph.veticesNo);
            if (target == source || Vertex.isVisited(source, target, Vertex.adjList)) { // to avoid self loops and duplicate edges
                i--;
                continue;
            }
            // generate random weights in range 0 to 20
            int weight = randm.nextInt(20) + 1;
            // add edge to the graph
            addEdge(source, target, weight);

        }

    }

////////////////////////////////**addEdge**////////////////////////////////////////////////////////////
    private void addEdge(int source, int target, int weight) {
        Edge edge = new Edge(source, target, weight);
        Vertex.adjList[source].addFirst(edge);

        edge = new Edge(target, source, weight);
        Vertex.adjList[target].addFirst(edge);//for undirected graph

    }

////////////////////////////////**setDigraph**////////////////////////////////////////////////////////////
    public void setDigraph(boolean isDigraph) {
        this.isDigraph = isDigraph;
    }
}

////////////////////////////////**ResultSet**////////////////////////////////////////////////////////////
class ResultSet {

    int parent;

    int weight;
}
