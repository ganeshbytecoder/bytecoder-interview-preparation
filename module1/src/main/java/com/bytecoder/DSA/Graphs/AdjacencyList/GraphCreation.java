package com.bytecoder.DSA.Graphs.AdjacencyList;

import lombok.Data;
import lombok.ToString;

import java.util.*;

// This class represents a directed graph using adjacency list representation

@Data
class Vertex<T> {
    int id;

    T node;

    boolean visited;

    @ToString.Exclude
    private Map<Vertex<T>, Integer> neighbors = new HashMap<>();

    Vertex(int index, T node){
        this.id = index;
        this.node = node;
        this.visited = false;
    }

    public void addNeighbor(Vertex<T> vertex, int cost){
        neighbors.put(vertex, cost);
    }
    

}


class Edge<T> {
    Vertex<T> start;

    Vertex<T> end;

    int cost;

    boolean directed;

    Edge(Vertex<T> start, Vertex<T> end, int cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
        this.directed = false;

    }

    @Override
    public String toString() {
        return super.toString();
    }
}



class Graph<T> {
    // No. of vertices
    private int V;

    // Adjacency Lists
    private final Map<T, Vertex<T>> vertices = new HashMap<>();


}
public class GraphCreation {
    public static void main(String[] args) {
//        Graph g = new Graph(4);
//        g.addEdge(0, 1);
//        g.addEdge(0, 2);
//        g.addEdge(1, 2);
//        g.addEdge(2, 0);
//        g.addEdge(2, 3);
//        g.addEdge(3, 3);
//        LinkedList<Integer> [] list = new LinkedList[10];
//        LinkedList<Integer> l1 = new LinkedList<>();
//        g.printAllRelationsOfV(0);



    }
}

