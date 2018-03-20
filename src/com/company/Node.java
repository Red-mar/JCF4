package com.company;

public class Node implements Comparable {
    Node leftNode, rightNode;
    char character;
    int frequency;

    public Node(char character, int frequency, Node leftNode, Node rightNode){
        this.character = character;
        this.frequency = frequency;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    @Override
    public int compareTo(Object o) {
        Node n = (Node)o;
        return frequency - ((Node) o).frequency;
    }
}
