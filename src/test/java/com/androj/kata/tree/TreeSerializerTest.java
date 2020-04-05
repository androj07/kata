package com.androj.kata.tree;

import org.junit.Test;

import javax.annotation.processing.Completion;

import java.util.concurrent.CompletableFuture;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

public class TreeSerializerTest {
    @Test
    public void serializeTreeTestFromExample() {
        Node root = new Node("root",
                new Node("left",
                        new Node("left.left")),
                new Node("right"));

        TreeSerializer serializer = new TreeSerializer();


        String serializedTree = serializer.serialize(root);
        Node deserializedTree = serializer.deserialize(serializedTree);

        assertThat(deserializedTree.getValue(), equalTo("root"));
        assertThat(deserializedTree.getLeft().getValue(), equalTo("left"));
        assertThat(deserializedTree.getRight().getValue(), equalTo("right"));
        assertThat(deserializedTree.getLeft().getLeft().getValue(), equalTo("left.left"));
    }

    @Test
    public void serialiseMoreRightThanLeft() {
        Node root = new Node("root",
                new Node("left"),
                new Node("right",
                        new Node("right.left", new Node("right.left.left")), new Node("right.right")));

        TreeSerializer serializer = new TreeSerializer();


        String serializedTree = serializer.serialize(root);
        Node deserializedTree = serializer.deserialize(serializedTree);

        assertThat(deserializedTree.getValue(), equalTo("root"));
        assertThat(deserializedTree.getLeft().getValue(), equalTo("left"));
        assertThat(deserializedTree.getRight().getValue(), equalTo("right"));
        assertThat(deserializedTree.getRight().getLeft().getValue(), equalTo("right.left"));
        assertThat(deserializedTree.getRight().getRight().getValue(), equalTo("right.right"));
        assertThat(deserializedTree.getRight().getLeft().getLeft().getValue(), equalTo("right.left.left"));
    }

    @Test
    public void serializeTreeWithNoLeafs() {
        Node root = new Node("root");

        TreeSerializer serializer = new TreeSerializer();


        String serializedTree = serializer.serialize(root);
        Node deserializedTree = serializer.deserialize(serializedTree);

        assertThat(deserializedTree.getValue(), equalTo("root"));
        assertThat(deserializedTree.getLeft(), nullValue());
        assertThat(deserializedTree.getRight(), nullValue());
    }

    @Test
    public void serializeTreeWithOnlyLeftLeaf() {
        Node root = new Node("root",new Node("left"));

        TreeSerializer serializer = new TreeSerializer();


        String serializedTree = serializer.serialize(root);
        Node deserializedTree = serializer.deserialize(serializedTree);

        assertThat(deserializedTree.getValue(), equalTo("root"));
        assertThat(deserializedTree.getLeft().getValue(), equalTo("left"));
        assertThat(deserializedTree.getRight(), nullValue());
    }
    @Test
    public void serializeTreeWithOnlyRightLeaf() {
        Node root = new Node("root");
        root.setRight(new Node("right"));

        TreeSerializer serializer = new TreeSerializer();


        String serializedTree = serializer.serialize(root);
        Node deserializedTree = serializer.deserialize(serializedTree);

        assertThat(deserializedTree.getValue(), equalTo("root"));
        assertThat(deserializedTree.getLeft(), nullValue());
        assertThat(deserializedTree.getRight().getValue(), equalTo("right"));
    }
}