package com.handson14;

import java.util.*;

class Graph {
    private Map<Integer, List<Integer>> graph;

    public Graph() {
        graph = new HashMap<>();
    }

    public void addEdge(int u, int v) {
        if (!graph.containsKey(u)) {
            graph.put(u, new ArrayList<>());
        }
        graph.get(u).add(v);
    }

    private void dfsUtil(int v, boolean[] visited) {
        visited[v] = true;
        System.out.print(v + " ");

        if (graph.containsKey(v)) {
            for (int i : graph.get(v)) {
                if (!visited[i]) {
                    dfsUtil(i, visited);
                }
            }
        }
    }

    public void dfs(int v) {
        boolean[] visited = new boolean[graph.size()];
        dfsUtil(v, visited);
    }

    public static void main(String[] args) {
        Graph g = new Graph();
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        System.out.println("Depth First Traversal:");
        g.dfs(2);
    }
}

