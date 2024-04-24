package com.handson14;

import java.util.*;

class TopologicalSort {
    private Map<Integer, List<Integer>> graph;
    private int V;

    public TopologicalSort(int vertices) {
        graph = new HashMap<>();
        V = vertices;
        for (int i = 0; i < V; i++) {
            graph.put(i, new ArrayList<>());
        }
    }

    public void addEdge(int u, int v) {
        graph.get(u).add(v);
    }

    private void topologicalSortUtil(int v, boolean[] visited, Stack<Integer> stack) {
        visited[v] = true;

        for (int i : graph.get(v)) {
            if (!visited[i]) {
                topologicalSortUtil(i, visited, stack);
            }
        }
        stack.push(v);
    }

    public List<Integer> topologicalSort() {
        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                topologicalSortUtil(i, visited, stack);
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }

    public static void main(String[] args) {
        TopologicalSort g = new TopologicalSort(6);
        g.addEdge(3, 4);
        g.addEdge(3, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 5);
        g.addEdge(0, 5);
        g.addEdge(0, 4);

        System.out.println("Topological Sort:");
        System.out.println(g.topologicalSort());
    }
}

