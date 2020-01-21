package com.androj.kata.tree;

public class Node {

    private String value;
    private Node left;
    private Node right;

    public Node(String value,Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public Node(String value){
        this.value = value;
    }

    public Node(String value, Node left) {
        this.value = value;
        this.left = left;
    }

    public String getValue() {
        return value;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
