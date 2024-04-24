package com.handson14;

import java.util.*;

class KruskalsAlgorithm {
    static class Edge implements Comparable<Edge> {
        int src, dest, weight;

        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        public int compareTo(Edge compareEdge) {
            return this.weight - compareEdge.weight;
        }
    }

    int V, E;
    Edge edge[];

    KruskalsAlgorithm(int v, int e) {
        V = v;
        E = e;
        edge = new Edge[E];
        for (int i = 0; i < e; ++i)
            edge[i] = new Edge(0, 0, 0);
    }

    int find(int parent[], int i) {
        if (parent[i] == i)
            return i;
        return find(parent, parent[i]);
    }

    void union(int parent[], int rank[], int x, int y) {
        int xroot = find(parent, x);
        int yroot = find(parent, y);

        if (rank[xroot] < rank[yroot])
            parent[xroot] = yroot;
        else if (rank[xroot] > rank[yroot])
            parent[yroot] = xroot;
        else {
            parent[yroot] = xroot;
            rank[xroot]++;
        }
    }

    void kruskalMST() {
        Edge result[] = new Edge[V];
        int e = 0;
        int i = 0;
        for (i = 0; i < V; ++i)
            result[i] = new Edge(0, 0, 0);

        Arrays.sort(edge);

        int parent[] = new int[V];
        int rank[] = new int[V];

        for (i = 0; i < V; ++i) {
            parent[i] = i;
            rank[i] = 0;
        }

        i = 0;

        while (e < V - 1) {
            Edge next_edge = edge[i++];
            int x = find(parent, next_edge.src);
            int y = find(parent, next_edge.dest);

            if (x != y) {
                result[e++] = next_edge;
                union(parent, rank, x, y);
            }
        }

        System.out.println("Edges in the Minimum Spanning Tree:");
        for (i = 0; i < e; ++i)
            System.out.println(result[i].src + " -- " + result[i].dest + " == " + result[i].weight);
    }

    public static void main(String[] args) {
        int V = 4; // Number of vertices
        int E = 5; // Number of edges
        KruskalsAlgorithm graph = new KruskalsAlgorithm(V, E);

        graph.edge[0] = new Edge(0, 1, 10);
        graph.edge[1] = new Edge(0, 2, 6);
        graph.edge[2] = new Edge(0, 3, 5);
        graph.edge[3] = new Edge(1, 3, 15);
        graph.edge[4] = new Edge(2, 3, 4);

        graph.kruskalMST();
    }
}
