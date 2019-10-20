package in.create.arena.blogapp.helpers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MSTKruskal {

    private Graph demoGraph;

    MSTKruskal(int vertices) {
        demoGraph = new Graph(vertices);
    }

    private class Edge {
        private int source;
        private int destination;
        private int weight;

        Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        private int getSource() {
            return source;
        }

        private int getDestination() {
            return destination;
        }

        private int getWeight() {
            return weight;
        }
    }

    private class Graph {
        private int vertices;
        private List<Edge> allEdges = new ArrayList<Edge>();

        Graph(int vertices) {
            this.vertices = vertices;
        }

        void addEdge(int source, int destination, int weight) {
            Edge tempEdge = new Edge(source, destination, weight);
            allEdges.add(tempEdge);
        }

        private int find(int[] parent, int vertex) {
            if (parent[vertex] != vertex) {
                return find(parent, parent[vertex]);
            }
            return vertex;
        }

        private void union(int[] parent, int x_set, int y_set) {
            int newXSet = find(parent, x_set);
            int newYSet = find(parent, y_set);
            parent[newYSet] = newXSet;
        }

        private void createSet(int[] parent) {
            for (int i=0; i<vertices; i++) {
                parent[i] = i;
            }
        }

        private void printEdges(List<Edge> allEdges, int totalWeight) {
            allEdges.forEach(edge -> {
                System.out.printf("Edge => Source: %d, Destination: %d, Weight: %d\n",
                        edge.getSource(), edge.getDestination(), edge.getWeight());
            });
            System.out.printf("Total weight of MST is => %d\n", totalWeight);
        }

        void runKruskalMST() {
            PriorityQueue<Edge> pq = new PriorityQueue<Edge>(allEdges.size(), Comparator.comparingInt(Edge::getWeight));
            pq.addAll(allEdges);
            int[] parent  = new int[vertices];
            int index = 0, totalWeight = 0;
            createSet(parent);
            List<Edge> mstEdges = new ArrayList<Edge>();
            while (index < vertices - 1) {
                Edge edge = pq.remove();
                int x_Set = find(parent, edge.getSource());
                int d_Set = find(parent, edge.getDestination());
                if (x_Set != d_Set) {
                    mstEdges.add(edge);
                    totalWeight += edge.getWeight();
                    union(parent, x_Set, d_Set);
                    index++;
                }
            }
            printEdges(mstEdges, totalWeight);
        }
    }

    public void demoKruskalAlgo() {
        demoGraph.addEdge(0, 1, 2);
        demoGraph.addEdge(0, 2, 1);
        demoGraph.addEdge(1, 2, 2);
        demoGraph.addEdge(1, 3, 1);
        demoGraph.addEdge(2, 4, 4);
        demoGraph.addEdge(3, 5, 6);
        demoGraph.addEdge(2, 3, 3);
        demoGraph.runKruskalMST();
    }
}
