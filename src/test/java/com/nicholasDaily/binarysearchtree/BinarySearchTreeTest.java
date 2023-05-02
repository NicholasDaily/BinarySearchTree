package com.nicholasDaily.binarysearchtree;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("BST tests!")
public class BinarySearchTreeTest {

    @Nested
    @DisplayName("Creation of binary search tree")
    @ExtendWith(BinarySearchTreeParameterResolver.class)
    class CreationTests {
        @Test
        @DisplayName("First value inserted gets added to root node")
        public void firstValueInsertedGetsAddedToTheRootNode(BinarySearchTree bst) {
            bst.add(Integer.valueOf(1));
            assertEquals(Integer.valueOf(1), bst.getRootValue());
        }

        @Test
        @DisplayName("Constructor with value adds value to root node")
        public void constructorOfBST(){
            BinarySearchTree<Integer> bst = new BinarySearchTree<>(Integer.valueOf(1));
            assertEquals(Integer.valueOf(1), bst.getRootValue());
        }

        @Test
        @DisplayName("Constructor with List adds all values to the tree")
        public void constructorOfBSTAddsAllValuesWhenAListIsPassedThrough(){
            List<Integer> integers = List.of(16, 8, 24, 28, 4, 30, 2, 6, 12, 20, 26, 10, 14, 18, 22, 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31);
            BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>(integers);
            assertEquals(31, bst.size());
        }
    }

    @Nested
    @DisplayName("Insertion method tests")
    @ExtendWith(BinarySearchTreeParameterResolver.class)
    class InsertionTests{
        @Test
        @DisplayName("Insertion of an object greater than the root will create a right child node")
        public void insertionOfAnObjectGreaterThanTheRootNodeWillCreateARightChildNode(BinarySearchTree bst){
            bst.add(Integer.valueOf(5));
            bst.add(Integer.valueOf(6));
            assertTrue(bst.getRootNode().getRightNode() != null);
            assertTrue(bst.getRootNode().getLeftNode() == null);
        }

        @Test
        @DisplayName("Insertion of an object equal to the root will create a right child node")
        public void insertionOfAnObjectEqualToTheRootNodeWillCreateARightChildNode(BinarySearchTree bst){
            bst.add(Integer.valueOf(5));
            bst.add(Integer.valueOf(5));
            assertTrue(bst.getRootNode().getRightNode() != null);
            assertTrue(bst.getRootNode().getLeftNode() == null);
        }

        @Test
        @DisplayName("Insertion of an object smaller than the root will create a left child node")
        public void insertionOfAnObjectLessThanTheRootNodeWillCreateALeftChildNode(BinarySearchTree bst){
            bst.add(Integer.valueOf(6));
            bst.add(Integer.valueOf(5));
            assertTrue(bst.getRootNode().getRightNode() == null);
            assertTrue(bst.getRootNode().getLeftNode() != null);
        }
    }

    @Nested
    @DisplayName("Getter and search method tests")
    @ExtendWith(FilledBinarySearchTreeParameterResolver.class)
    class GetterTests{
        @Test
        @DisplayName("Size method returns an accurate size")
        public void sizeMethodReturnsAccurateSize(BinarySearchTree bst){
            assertEquals(31, bst.size());
        }

        @Test
        @DisplayName("getSortedListAsc method returns a List of objects in sorted order")
        public void getSortedListAscMethodReturnsAListOfObjectsInSortedOrder(BinarySearchTree bst){
            List<Integer> sorted = bst.getSortedListAsc();
            List sortedExpected = new ArrayList();

            for(int i = 1; i < 32; i++){
                sortedExpected.add(i);
            }

            assertArrayEquals(sortedExpected.stream().toArray(), sorted.stream().toArray());
        }

        @Test
        @DisplayName("getSortedListDesc method returns a List of objects in reverse sorted order")
        public void getSortedListDescMethodReturnsAListOfObjectsInReverseSortedOrder(BinarySearchTree bst){
            List<Integer> sorted = bst.getSortedListDesc();
            List sortedExpected = new ArrayList();

            for(int i = 31; i > 0; i--){
                sortedExpected.add(i);
            }

            assertArrayEquals(sortedExpected.stream().toArray(), sorted.stream().toArray());
        }

        @Test
        @DisplayName("Get number of leaf nodes returns the correct number")
        public void countLeafNodes(BinarySearchTree bst){
            assertEquals(16, bst.countLeafNodes());
        }
     }

    @Nested
    @DisplayName("Node behaviour")
    @ExtendWith(FilledBinarySearchTreeParameterResolver.class)
    class transferringNodeToNewTree {
        @Test
        @DisplayName("Parent nodes are linked correctly with child nodes")
        public void parentNodesAreLinkedCorrectlyWithChildNodes(BinarySearchTree bst){
            BinarySearchTree.Node<Integer> node = bst.getRootNode();
            int value = node.getRightNode().getParentNode().getValue();
            assertEquals(node.getValue(), value);
            value = node.getLeftNode().getParentNode().getValue();
            assertEquals(node.getValue(), value);
        }

        @Test
        @DisplayName("BST created from node has correct size")
        public void bstCreatedFromNodeHasCorrectSize(BinarySearchTree bst){
            BinarySearchTree.Node<Integer> rootNode = bst.getRootNode();
            BinarySearchTree.Node<Integer> secondRightThenFirstLeft = rootNode.getRightNode().getRightNode().getLeftNode();
            BinarySearchTree newTree = new BinarySearchTree(secondRightThenFirstLeft);
            assertEquals(3, newTree.size());
        }
    }
}
