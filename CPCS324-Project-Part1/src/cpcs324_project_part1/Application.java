/*
CPCS324 - Algorithms and Data Structures 2
Project Part 1
Group Members:
Renad Saud Alsulami - 1905991 - DAR
Rimas Ali Alqahtani - 1905479 - DAR
Hanan Abdullah Tayeb - 1905913 - BAR
 */
package cpcs324_project_part1;

import java.util.Scanner;

public class Application {
    
////////////////////////////////**main**////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        // Create a scanner object
        Scanner input = new Scanner(System.in);

        // Display menu
        System.out.println("\t\t*** Runtime of Different Minimum Spanning Tree Algorithms ***");
        System.out.println("Choice 1 to compare Different between Kruskal's Algorithm& Prim's Algorithm (based on Priority Queue)");
        System.out.println("Choice 2 to compare Different between Prim's Algorithm (based on Min Heap)& Prim's Algorithm(based on Priority Queue)");
        System.out.print(">> Enter your choice: ");
        // UserChoice to choice 1 or 2 by user
        int UserChoice = input.nextInt();
        // if user choice 1 or 2
        if (UserChoice == 1 || UserChoice == 2) {
            System.out.println(">>Test  cases : (where n is the number of vertices "
                    + "and m is the number of edges: ");
            System.out.println(" 1:  n=1,000 ,  m=10,000");
            System.out.println(" 2:  n=1,000 ,  m=15,000");
            System.out.println(" 3:  n=1,000 ,  m=25,000");
            System.out.println(" 4:  n=5,000 ,  m=15,000");
            System.out.println(" 5:  n=5,000 ,  m=25,000");
            System.out.println(" 6:  n=10,000 , m=15,000");
            System.out.println(" 7:  n=10,000 , m=25,000");
            System.out.println(" 8:  n=20,000 , m=200,000");
            System.out.println(" 9:  n=20,000 , m=300,000");
            System.out.println("10:  n=50,000 , m=1,000,000");
            System.out.print(">> Enter a case to test: ");
            //UserChoiceCase to choice case number from 1 to 10 by user
            int UserChoiceCase = input.nextInt();
            int n = 0, m = 0;
            // switch for all avaliable cases of the test 
            while (UserChoiceCase < 1 || UserChoiceCase > 10) {
                System.out.println("Invalid input!");
                System.out.print("> Enter a case to test: ");
                UserChoiceCase = input.nextInt();
            }
            switch (UserChoiceCase) {
                case 1: {
                    n = 1000;
                    m = 10000;
                }
                break;
                case 2: {
                    n = 1000;
                    m = 15000;
                }
                break;
                case 3: {
                    n = 1000;
                    m = 25000;
                }
                break;
                case 4: {
                    n = 5000;
                    m = 15000;
                }
                break;
                case 5: {
                    n = 5000;
                    m = 25000;
                }
                break;
                case 6: {
                    n = 10000;
                    m = 15000;
                }
                break;
                case 7: {
                    n = 10000;
                    m = 25000;
                }
                break;
                case 8: {
                    n = 20000;
                    m = 200000;
                }
                break;
                case 9: {
                    n = 20000;
                    m = 300000;
                    break;

                }
                case 10: {
                    n = 50000;
                    m = 1000000;
                    break;
                }
                default: {
                    System.out.println("Invalid input!");
                    break;
                }
            }

            Graph graph = new Graph(n, m);
            System.out.print("Is the graph directed?\n>> enter T for true or F for false:");
            String isDigraph = input.next();
            if (isDigraph.equalsIgnoreCase("T")) {
                System.out.println(" Prim’s and Kruskal’s  MST Algorithms don't work on directed graphs");
                System.out.println(" Would you like to quit(enter quit) ,or continue considering the graph is undirected (enter cont) ");
                String contOrQuit = input.next();
                if (contOrQuit.equalsIgnoreCase("quit")) {
                    System.out.println("Thank you for comming by!");
                    System.exit(0);
                }

            } else {
                graph.makeGraph(graph);
            }

            switch (UserChoice) {
                // to perform kruskal and prim priority queue
                case 1:
                    KruskalAlg gKruskal = new KruskalAlg(graph);
                    PQPrimAlg gPQPrim = new PQPrimAlg(graph);
                    break;
                case 2:
                    // to perform prim min heap and prim priority queue
                    MHPrimAlg gMHPrim = new MHPrimAlg(graph);
                    gPQPrim = new PQPrimAlg(graph);
                    break;
                default:
                    System.out.println("Invalid input!");
                    System.out.println("Thank you for comming by!");
                    break;
            }

        } else {//if user choice neither 1 nor 2
            System.out.println("****Invalid input!****");
        }
    }
}
