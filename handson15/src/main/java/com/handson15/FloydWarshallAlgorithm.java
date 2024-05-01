package com.handson15;

import java.util.*;

class Edge {
    String start;
    String end;
    int weight;

    Edge(String start, String end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
}

public class FloydWarshallAlgorithm {
    public static Map<String, Map<String, Integer>> floydWarshallAlgorithm(Map<String, Map<String, Integer>> graph) {
        Map<String, Map<String, Integer>> distance = new HashMap<>();

        for (String u : graph.keySet()) {
            distance.put(u, new HashMap<>());
            for (String v : graph.keySet()) {
                if (!u.equals(v)) {
                    distance.get(u).put(v, graph.getOrDefault(u, new HashMap<>()).getOrDefault(v, Integer.MAX_VALUE));
                } else {
                    distance.get(u).put(v, 0);
                }
            }
        }

        for (String k : graph.keySet()) {
            for (String i : graph.keySet()) {
                for (String j : graph.keySet()) {
                    distance.get(i).put(j, Math.min(distance.get(i).get(j), distance.get(i).get(k) + distance.get(k).get(j)));
                }
            }
        }

        return distance;
    }

    public static void printGraph(Map<String, Map<String, Integer>> graph) {
        System.out.println("Graph:");
        for (String u : graph.keySet()) {
            for (String v : graph.get(u).keySet()) {
                System.out.println(u + " -> " + v + " : " + graph.get(u).get(v));
            }
        }
        System.out.println();
    }

    public static void printShortestPaths(Map<String, Map<String, Integer>> allPairsShortestPaths) {
        System.out.println("Shortest Paths:");
        for (String u : allPairsShortestPaths.keySet()) {
            for (String v : allPairsShortestPaths.get(u).keySet()) {
                System.out.println("Shortest path from " + u + " to " + v + ": " + allPairsShortestPaths.get(u).get(v));
            }
        }
        System.out.println();
    }

    public static List<Edge> constructGraphFromShortestPaths(Map<String, Map<String, Integer>> graph, Map<String, Map<String, Integer>> allPairsShortestPaths) {
        List<Edge> shortestPathEdges = new ArrayList<>();
        for (String u : graph.keySet()) {
            for (String v : graph.get(u).keySet()) {
                shortestPathEdges.add(new Edge(u, v, allPairsShortestPaths.get(u).get(v)));
            }
        }
        return shortestPathEdges;
    }

    public static void printShortestPathGraph(List<Edge> shortestPathEdges) {
        System.out.println("Shortest Path Graph:");
        for (Edge edge : shortestPathEdges) {
            System.out.println(edge.start + " -> " + edge.end + " : " + edge.weight);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Map<String, Map<String, Integer>> graph = new HashMap<>();
        graph.put("1", Map.of("2", 3, "3", 8, "5", -4));
        graph.put("2", Map.of("5", 7, "4", 1));
        graph.put("3", Map.of("2", 4));
        graph.put("4", Map.of("3", -5, "1", 2));
        graph.put("5", Map.of("4", 6));

        printGraph(graph);

        Map<String, Map<String, Integer>> allPairsShortestPaths = floydWarshallAlgorithm(graph);
        printShortestPaths(allPairsShortestPaths);

        List<Edge> shortestPathEdges = constructGraphFromShortestPaths(graph, allPairsShortestPaths);
        printShortestPathGraph(shortestPathEdges);
    }
}

