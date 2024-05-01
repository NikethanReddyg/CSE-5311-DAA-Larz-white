package com.handson15;

import java.util.*;

public class DijkstrasAlgorithm {

    public static Map<String, Integer> dijkstra(Map<String, Map<String, Integer>> graph, String start) {
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> previous = new HashMap<>();
        PriorityQueue<String> queue = new PriorityQueue<>(Comparator.comparing(distances::get));

        // Initialization
        for (String vertex : graph.keySet()) {
            if (vertex.equals(start)) {
                distances.put(vertex, 0);
            } else {
                distances.put(vertex, Integer.MAX_VALUE);
            }
            queue.offer(vertex);
        }

        // Dijkstra's Algorithm
        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (distances.get(current) == Integer.MAX_VALUE) {
                break; // All remaining vertices are inaccessible from start
            }
            for (Map.Entry<String, Integer> neighborEntry : graph.get(current).entrySet()) {
                String neighbor = neighborEntry.getKey();
                int newDistance = distances.get(current) + neighborEntry.getValue();
                if (newDistance < distances.get(neighbor)) {
                    distances.put(neighbor, newDistance);
                    previous.put(neighbor, current);
                    // Reorder the priority queue to reflect the updated distances
                    queue.remove(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
        return distances;
    }

    public static void main(String[] args) {
        // Example graph represented as adjacency list
        Map<String, Map<String, Integer>> graph = new HashMap<>();
        graph.put("A", Map.of("B", 7, "C", 9, "F", 14));
        graph.put("B", Map.of("A", 7, "C", 10, "D", 15));
        graph.put("C", Map.of("A", 9, "B", 10, "D", 11, "F", 2));
        graph.put("D", Map.of("B", 15, "C", 11, "E", 6));
        graph.put("E", Map.of("D", 6, "F", 9));
        graph.put("F", Map.of("A", 14, "C", 2, "E", 9));

        String startNode = "A";
        Map<String, Integer> distances = dijkstra(graph, startNode);

        System.out.println("Shortest distances from node " + startNode + ":");
        for (Map.Entry<String, Integer> entry : distances.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
