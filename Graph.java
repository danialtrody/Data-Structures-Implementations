/**
 * Created by Danial Trody
 * Custom Graph Implementation
 * This class represents a generic graph using a  adjacency lists.
 */
import java.util.*;

public class Graph<T> {

    private Map<T, List<T>> adjacencyList;

    // Constructor to initialize the graph
    public Graph(){
        adjacencyList =  new HashMap<>();
    }

    // Add a vertex to the graph
    public void addVertex(T vertex) {
        adjacencyList.put(vertex, new LinkedList<>());
    }

    // Add an edge between two vertices
    public void addEdge(T source, T destination) {
        adjacencyList.get(source).add(destination);
        adjacencyList.get(destination).add(source);
    }

    // Print the graph
    public void printGraph() {
        for (Map.Entry<T, List<T>> entry : adjacencyList.entrySet()) {
            System.out.print("Vertex " + entry.getKey() + " is connected to: ");
            for (T neighbor : entry.getValue()) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }

}
