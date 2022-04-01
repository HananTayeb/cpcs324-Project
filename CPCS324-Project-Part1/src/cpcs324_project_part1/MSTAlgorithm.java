/*
CPCS324 - Algorithms and Data Structures 2
Project Part 1
Group Members:
Renad Saud Alsulami - 1905991 - DAR
Rimas Ali Alqahtani - 1905479 - DAR
Hanan Abdullah Tayeb - 1905913 - BAR
 */
package cpcs324_project_part1;

public class MSTAlgorithm {

    Graph graph;

    ////////////////////////////////**Constructor**////////////////////////////////////////////////////////////
    public MSTAlgorithm() {
    }

    ////////////////////////////////**displayResultingMST**////////////////////////////////////////////////////////////
    public void displayResultingMST(ResultSet[] resultSet) {
        int totalWeight = 0, i = 0;

        while (i < graph.veticesNo) {
            totalWeight += resultSet[i++].weight;

        }
        System.out.println("Minimum Spanning Tree cost: " + totalWeight);
    }
}
