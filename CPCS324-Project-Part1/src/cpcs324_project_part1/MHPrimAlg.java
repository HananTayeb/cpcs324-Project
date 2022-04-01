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

public class MHPrimAlg extends MSTAlgorithm {

    //start timing
    double startTime = System.currentTimeMillis();
    boolean[] Heap = new boolean[Graph.veticesNo];
    ResultSet[] resultSet = new ResultSet[Graph.veticesNo];
    //keys[] used to check if min heap update is required
    int[] key = new int[Graph.veticesNo];
    //create heapNode for all the vertices
    heapNode[] heapNodes = new heapNode[Graph.veticesNo];

    ////////////////////////////////**Constructor**////////////////////////////////////////////////////////////
    public MHPrimAlg(Graph graph) {
        for (int i = 0; i < graph.veticesNo; i++) {
            heapNodes[i] = new heapNode();
            heapNodes[i].node = i;
            heapNodes[i].key = Integer.MAX_VALUE;
            resultSet[i] = new ResultSet();
            resultSet[i].parent = -1;
            Heap[i] = true;
            key[i] = Integer.MAX_VALUE;
        }

        //decrease the key for the first index
        heapNodes[0].key = 0;

        //add all the vertices to the MinHeap
        MinHeap minHeap = new MinHeap(graph.veticesNo);
        //add all the vertices to priority queue
        for (int i = 0; i < graph.veticesNo; i++) {
            minHeap.insert(heapNodes[i]);
        }

        do {
            //extract the min
            heapNode extractedNode = minHeap.extractMin();

            //extracted vertex
            int extractedVertex = extractedNode.node;
            Heap[extractedVertex] = false;

            //iterate through all the adjacent vertices
            LinkedList<Edge> list = Vertex.adjList[extractedVertex];
            for (int i = 0; i < list.size(); i++) {
                Edge edge = list.get(i);
                //only if edge destination is present in heap
                if (Heap[edge.getTarget()]) {
                    int target = edge.getTarget();
                    int newKey = edge.getWeight();
                    //check if updated key < existing key, if yes, update if
                    if (key[target] > newKey) {
                        toDecreaseKey(minHeap, newKey, target);
                        //update the parent node for destination
                        resultSet[target].parent = extractedVertex;
                        resultSet[target].weight = newKey;
                        key[target] = newKey;
                    }
                }
            }
        } while (!minHeap.isEmpty());
        //finish time of the algorithm
        double ftime = System.currentTimeMillis();
        //print the total time consumed by the algorithm
        System.out.println("Total runtime of Prim's Algorithm (Usin Min Heap): " + (ftime - startTime) + " ms.");
        //print mst
        super.displayResultingMST(resultSet);
    }

////////////////////////////////**toDecreaseKey**////////////////////////////////////////////////////////////
    private void toDecreaseKey(MinHeap mH, int newKey, int vertex) {

        //get the index which key's needs a decrease;
        int index = mH.decreaseKey[vertex];

        //get the node and update its value
        heapNode node = mH.minHeap[index];
        node.key = newKey;
        mH.bubbleUp(index);
    }

}

class heapNode {

    int node;

    int key;
}

class MinHeap {

    int capacity;

    int Size; //The current size

    heapNode[] minHeap;

    int[] decreaseKey; //to decrease the key

////////////////////////////////**Constructor**////////////////////////////////////////////////////////////
    public MinHeap(int capacity) {
        this.capacity = capacity;
        minHeap = new heapNode[capacity + 1];
        decreaseKey = new int[capacity];
        minHeap[0] = new heapNode();
        minHeap[0].key = Integer.MIN_VALUE;
        minHeap[0].node = -1;
        Size = 0;
    }

////////////////////////////////**insert**////////////////////////////////////////////////////////////
    public void insert(heapNode Node) {
        Size++;
        int idx = Size;
        minHeap[idx] = Node;
        decreaseKey[Node.node] = idx;
        bubbleUp(idx);
    }

////////////////////////////////**bubbleUp**////////////////////////////////////////////////////////////
    public void bubbleUp(int Position) {
        int parentIdx = Position / 2;
        int currentIdx = Position;
        while (currentIdx > 0 && minHeap[parentIdx].key > minHeap[currentIdx].key) {
            heapNode currentNode = minHeap[currentIdx];
            heapNode parentNode = minHeap[parentIdx];
            //to swap positions 
            decreaseKey[currentNode.node] = parentIdx;
            decreaseKey[parentNode.node] = currentIdx;
            swap(currentIdx, parentIdx);
            currentIdx = parentIdx;
            parentIdx = parentIdx / 2;
        }
    }

    ////////////////////////////////**extractMin**////////////////////////////////////////////////////////////
    public heapNode extractMin() {
        heapNode min = minHeap[1];
        heapNode lastNode = minHeap[Size];
        //to put the last node at the top and update the decreaseKey[]
        decreaseKey[lastNode.node] = 1;
        minHeap[1] = lastNode;
        minHeap[Size] = null;
        sinkDown(1);
        Size--;
        return min;
    }

////////////////////////////////**sinkDown**////////////////////////////////////////////////////////////
    public void sinkDown(int T) {
        int theSmallest = T;
        int leftChild = 2 * T;
        int rightChild = 2 * T + 1;
        if (leftChild < heapSize() && minHeap[theSmallest].key
                > minHeap[leftChild].key) {
            theSmallest = leftChild;
        }
        if (rightChild < heapSize() && minHeap[theSmallest].key
                > minHeap[rightChild].key) {
            theSmallest = rightChild;
        }
        if (theSmallest != T) {

            heapNode smallestNode = minHeap[theSmallest];
            heapNode TNode = minHeap[T];

            //swap the positions
            decreaseKey[smallestNode.node] = T;
            decreaseKey[TNode.node] = theSmallest;
            swap(T, theSmallest);
            sinkDown(theSmallest);
        }
    }

////////////////////////////////**isEmpty**////////////////////////////////////////////////////////////
    public boolean isEmpty() {
        return Size == 0;
    }

////////////////////////////////**heapSize**////////////////////////////////////////////////////////////
    public int heapSize() {
        return Size;
    }

////////////////////////////////**swap**////////////////////////////////////////////////////////////
    public void swap(int x, int y) {
        heapNode temp = minHeap[x];
        minHeap[x] = minHeap[y];
        minHeap[y] = temp;
    }
}
