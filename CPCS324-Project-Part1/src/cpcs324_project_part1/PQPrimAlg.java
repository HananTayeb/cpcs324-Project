/*
CPCS324 - Algorithms and Data Structures 2
Project Part 1
Group Members:
Renad Saud Alsulami - 1905991 - DAR
Rimas Ali Alqahtani - 1905479 - DAR
Hanan Abdullah Tayeb - 1905913 - BAR
 */
package cpcs324_project_part1;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;

public class PQPrimAlg extends MSTAlgorithm {

    //start timing
    double startTime = System.currentTimeMillis();
    boolean[] MST = new boolean[Graph.veticesNo];
    ResultSet[] results = new ResultSet[Graph.veticesNo];
    int[] key = new int[Graph.veticesNo];

    ////////////////////////////////**Constructor**////////////////////////////////////////////////////////////
    public PQPrimAlg(Graph graph) {
        // 1- initialize all keys to infinity
        // 2- initialize ResultSet for all the vertices
        for (int i = 0; i < graph.veticesNo; i++) {
            key[i] = Integer.MAX_VALUE;
            results[i] = new ResultSet();
        }

        // 3- initialize priority queue to implement prim's algorithm
        // 4-override the comparator to do the sorting based keys
        PriorityQueue<Map.Entry<Integer, Integer>> PQ = new PriorityQueue<>(Graph.veticesNo,
                (Map.Entry<Integer, Integer> p1, Map.Entry<Integer, Integer> p2) -> {
                    //sort using key values
                    int key1 = p1.getKey();
                    int key2 = p2.getKey();
                    return key1 - key2;
                });

        //create the pair for the first index, 0 key 0 index
        key[0] = 0;
        Map.Entry<Integer, Integer> p0 = new HashMap.SimpleImmutableEntry<>(key[0], 0);
        //add it to priority queue
        PQ.offer(p0);
        results[0] = new ResultSet();
        results[0].parent = -1;

        //while priority queue not empty
        while (!PQ.isEmpty()) {
            //extract the minimum value
            Map.Entry<Integer, Integer> Map_extract = PQ.poll();

            //extract vertex
            int exVert = Map_extract.getValue();
            MST[exVert] = true;

            //iterate through all adjacent verices
            //update keys
            LinkedList<Edge> list = Vertex.adjList[exVert];
            for (int i = 0; i < list.size(); i++) {
                Edge edge = list.get(i);
                //if edge destination is not currently present in mst
                if (MST[edge.getTarget()] == false) {
                    int target = edge.getTarget();
                    int newKey = edge.getWeight();
                    //check if updated key < current key
                    //if true-> update
                    if (key[target] > newKey) {
                        //add it to priority queue
                        Map.Entry<Integer, Integer> pq_updated = new HashMap.SimpleImmutableEntry<>(newKey, target);
                        PQ.offer(pq_updated);

                        //update the ResultSet for destination vertex
                        results[target].parent = exVert;
                        results[target].weight = newKey;
                        //update key array
                        key[target] = newKey;

                    }

                }
            }
        }
        //calculate finish time for the algorithm
        double finTime = System.currentTimeMillis();

        //print total time taken by the algorithm
        System.out.println("Total runtime of Prim's Algorithm (Usin Priority Queue): " + (finTime - startTime) + " ms.");
        //print mst
        super.displayResultingMST(results);

    }

}
