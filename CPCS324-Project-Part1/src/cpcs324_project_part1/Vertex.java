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

public class Vertex {

    public static boolean isVisited;
    public static LinkedList<Edge>[] adjList;
    
 ////////////////////////////////**Constructor**////////////////////////////////////////////////////////////
    public Vertex() {
    }

    ////////////////////////////////**getEdges**////////////////////////////////////////////////////////////
    public LinkedList<Edge>[] getEdges() {
        return this.adjList;
    }

    ////////////////////////////////**isVisited**////////////////////////////////////////////////////////////
    public static boolean isVisited(int source, int target, LinkedList<Edge>[] TotalEdges) {
        for (LinkedList<Edge> i : TotalEdges) {
            if (i.stream().anyMatch((edge) -> {
                return (edge.getSource() == source && edge.getTarget() == target)
                        || (edge.getSource() == target && edge.getTarget() == source);
            })) {
                isVisited = true;
                return isVisited;
            } // for each loop to check the edges
        }
        isVisited = false;
        return isVisited;
    }
}
