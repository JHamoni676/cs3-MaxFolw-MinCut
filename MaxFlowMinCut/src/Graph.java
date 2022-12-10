import javax.print.attribute.standard.MediaSize;
import java.net.Inet4Address;
import java.util.*;

public class Graph {
    private final GraphNode[] vertices;  // Adjacency list for graph.
    private final String name;  //The file from which the graph was created.
    private final int vertexCount;

    public Graph(String name, int vertexCount) {
        this.name = name;
        this.vertexCount = vertexCount;

        vertices = new GraphNode[vertexCount];
        for (int vertex = 0; vertex < vertexCount; vertex++) {
            vertices[vertex] = new GraphNode(vertex);
        }
    }

    public boolean addEdge(int source, int destination, int capacity) {
        // A little bit of validation
        if (source < 0 || source >= vertices.length) return false;
        if (destination < 0 || destination >= vertices.length) return false;

        // This adds the actual requested edge, along with its capacity
        vertices[source].addEdge(source, destination, capacity);
        vertices[source].addEdge(destination, source, capacity);

        return true;
    }

    /**
     * Algorithm to find max-flow in a network
     */
    public int findMaxFlow(int s, int t, boolean report) {
        // TODO:

        int residualGraph[][] = new int[this.vertexCount][this.vertexCount];

        for (int i = 0; i < this.vertexCount; i++) {
            for (int j = 0; j < this.vertices[i].successor.size(); j += 2) {
                residualGraph[i][j] = this.vertices[i].successor.get(j).from;
                residualGraph[i][j + 1] = this.vertices[i].successor.get(j).to;
            }
        }


        int maxFlow = 0;
        while (hasAugmentingPath(residualGraph, s, t)) {

            int availableFlow = Integer.MAX_VALUE;

            for (int i = 0; i < this.vertices.length; i++) {
                if (this.vertices[i].successor.get(i).capacity < availableFlow) {
                    availableFlow = this.vertices[i].successor.get(i).capacity;
                }
            }

        }


        System.out.println("-- Max Flow: " + this.name + " --");

        return 0;
    }


    /**
     * Algorithm to find an augmenting path in a network
     */
    private boolean hasAugmentingPath(int[][] residualGraph, int s, int t) {
        // TODO:
        Queue<GraphNode> queue = new LinkedList<>();

        for (var vertex : vertices) {
            vertex.parent = -1;
        }
        vertices[s].visited = true;
        queue.add(vertices[s]);
        while (!queue.isEmpty() && vertices[t].parent == -1) {

            GraphNode v = queue.remove();
            for (var successor : v.successor) {
                GraphNode w = vertices[successor.to];
                if (!w.visited && w.successor.get(0).capacity > 0) {

                }
            }
        }

        if (vertices[t].parent != -1) {
            return true;
        }
        return false;
    }

    /**
     * Algorithm to find the min-cut edges in a network
     */
    public void findMinCut(int s) {
        // TODO:
        System.out.println("-- Min Cut: " + this.name + " --");


    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("The Graph " + name + " \n");
        for (var vertex : vertices) {
            sb.append((vertex.toString()));
        }
        return sb.toString();
    }
}
