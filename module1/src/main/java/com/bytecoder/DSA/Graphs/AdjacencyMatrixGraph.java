package com.bytecoder.DSA.Graphs;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
class Vertex<T> {
    int id;

    T node;

    boolean visited;

    Vertex(int index, T node){
        this.id = index;
        this.node = node;
        this.visited = false;
    }

}

@Data
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

class AdjacencyMatrix<T>{
    private List<Vertex<T>> vertices = new ArrayList<>();
    private int [][] matrix;



    AdjacencyMatrix(int n){

        matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j]= -1;
            }
        }
    }

    public Vertex<T> getVertex(T node){
        for(Vertex<T> vertex : vertices){
            if(vertex.node == node){
                return vertex;
            }
        }
        return null;
    }

    public void addVertex(int index, T node){
        vertices.add(new Vertex<T>(index,node));
    }

    public void addEdge( Edge edge){
        int i  =  edge.start.id;
        int j  =  edge.end.id;
        matrix[i][j] = edge.cost;
        if(!edge.directed){
            matrix[j][i] = edge.cost;

        }
    }

    void printMatrix(){
        for(Vertex<T> vertex: vertices){
            System.out.print(vertex.node.toString()+ " ");
        }
        System.out.println();
         for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}




public class AdjacencyMatrixGraph {


    public static void main(String[] args) {
        AdjacencyMatrix<String> matrix = new AdjacencyMatrix<>(4);

        String[] values = {"A", "B", "C", "D"};
        for (int i = 0; i < 4; i++) {
            matrix.addVertex(i, values[i]);
        }
//        for (int i = 0; i < 4; i++) {
//            for (int j = 0; j < 4; j++) {
//                System.out.print(matrix[i][j] + " ");
//            }
//            System.out.println();
//        }
        matrix.addEdge(new Edge(matrix.getVertex("A"), matrix.getVertex("B"), 10));
        matrix.addEdge( new Edge(matrix.getVertex("B"), matrix.getVertex("C"), 20));
        matrix.addEdge( new Edge(matrix.getVertex("B"), matrix.getVertex("A"), 10));
        System.out.println("Matrix");
        matrix.printMatrix();



    }
}
