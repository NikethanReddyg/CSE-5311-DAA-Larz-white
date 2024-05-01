package com.handson15;

import java.util.Scanner;

class Edge {
    int source, destination, weight;

    Edge(int src, int dest, int weight) {
        this.source = src;
        this.destination = dest;
        this.weight = weight;
    }
}

class Graph {
    int numVertices, numEdges;
    Edge[] edges;

    Graph(int numVertices, int numEdges) {
        this.numVertices = numVertices;
        this.numEdges = numEdges;
        this.edges = new Edge[numEdges];
    }
}

public class BellmanFordAlgorithm {
    static Graph createGraph(int numVertices, int numEdges) {
        return new Graph(numVertices, numEdges);
    }

    static void initializeDistances(int[] dist, int numVertices, int src) {
        for (int i = 0; i < numVertices; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[src] = 0;
    }

    static void relaxEdges(Graph graph, int[] dist) {
        for (int i = 0; i < graph.numVertices - 1; i++) {
            for (int j = 0; j < graph.numEdges; j++) {
                int u = graph.edges[j].source;
                int v = graph.edges[j].destination;
                int weight = graph.edges[j].weight;
                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                }
            }
        }
    }

    static void checkNegativeCycles(Graph graph, int[] dist) {
        for (int i = 0; i < graph.numEdges; i++) {
            int u = graph.edges[i].source;
            int v = graph.edges[i].destination;
            int weight = graph.edges[i].weight;
            if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                System.out.println("Graph contains negative-weight cycle");
                return;
            }
        }
    }

    static void printDistances(int[] dist, int numVertices) {
        System.out.println("Vertex   Distance from Source");
        for (int i = 0; i < numVertices; i++) {
            if (dist[i] == Integer.MAX_VALUE)
                System.out.println(i + " -> INF");
            else
                System.out.println(i + " -> " + dist[i]);
        }
    }

    static void bellmanFord(Graph graph, int src) {
        int[] dist = new int[graph.numVertices];
        initializeDistances(dist, graph.numVertices, src);
        relaxEdges(graph, dist);
        checkNegativeCycles(graph, dist);
        printDistances(dist, graph.numVertices);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int numVertices = scanner.nextInt();

        System.out.print("Enter the number of edges: ");
        int numEdges = scanner.nextInt();

        Graph graph = createGraph(numVertices, numEdges);

        System.out.println("Enter the edges (source destination weight):");
        for (int i = 0; i < numEdges; i++) {
            int src = scanner.nextInt();
            int dest = scanner.nextInt();
            int weight = scanner.nextInt();
            graph.edges[i] = new Edge(src, dest, weight);
        }

        System.out.print("Enter the source vertex for the Bellman-Ford algorithm: ");
        int sourceVertex = scanner.nextInt();

        bellmanFord(graph, sourceVertex);

        scanner.close();
    }
}

