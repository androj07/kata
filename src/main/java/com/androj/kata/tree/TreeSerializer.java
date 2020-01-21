package com.androj.kata.tree;

public class TreeSerializer {
    private final String SEPARATOR = ";";
    private final String NODE_END = "_";

    public String serialize(Node root) {
        return this.serializeNode(root);
    }

    public Node deserialize(String serializedTree) {
        String[] nodes = serializedTree.split(NODE_END);
        if (nodes.length == 0) {
            return null;
        }

        String[] nodeParts = nodes[0].split(SEPARATOR);
        Node root = new Node(nodeParts[0]);
        deserializeNode(nodes, root, 0);
        return root;
    }

    private String serializeNode(Node node) {
        StringBuilder serializedTree = new StringBuilder(node.getValue().toString());

        serializedTree.append(SEPARATOR);
        if (node.getLeft() != null) {
            serializedTree.append(node.getLeft().getValue());
        }
        serializedTree.append(SEPARATOR);
        if (node.getRight() != null) {
            serializedTree.append(node.getRight().getValue());
        }
        serializedTree.append(NODE_END);

        if (node.getLeft() != null) {
            serializedTree.append(serializeNode(node.getLeft()));
        }
        if (node.getRight() != null) {
            serializedTree.append(serializeNode(node.getRight()));
        }
        return serializedTree.toString();
    }

    private void deserializeNode(String[] nodes, Node parent, int currentNodeIdx) {
        if (currentNodeIdx < nodes.length) {
            String[] nodeParts = nodes[currentNodeIdx].split(SEPARATOR);

            if (nodeParts.length >= 2 && !nodeParts[1].isEmpty()) {
                Node left = new Node(nodeParts[1]);
                parent.setLeft(left);
            }

            if (nodeParts.length == 3 && !nodeParts[2].isEmpty()) {
                Node right = new Node(nodeParts[2]);
                parent.setRight(right);
            }

            if(parent.getLeft()!=null){
                currentNodeIdx++;
                deserializeNode(nodes,parent.getLeft(),currentNodeIdx);
            }

            if(parent.getRight()!=null){
                currentNodeIdx++;
                deserializeNode(nodes,parent.getRight(),currentNodeIdx);

            }
        }
    }


}
