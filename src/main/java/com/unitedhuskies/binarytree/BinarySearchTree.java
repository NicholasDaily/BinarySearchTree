package com.unitedhuskies.binarytree;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree<E extends Comparable> {

    public static class Node<E extends Comparable> {
        private E value;
        private Node<E> parentNode;
        private Node<E> leftNode;
        private Node<E> rightNode;
        private int size = 0;
        Node(){

        }
        Node(Node<E> node, Node<E> parentNode){
            if(node != null) {
                this.leftNode = node.getLeftNode();
                this.rightNode = node.getRightNode();
                this.size = node.size();
            }
            this.parentNode = parentNode;

        }
        Node(E value){
            this.value = value;
        }

        private void setValue(E value){
            this.value = value;
        }

        private void setRightNode(Node<E> rightNode){
            this.rightNode = rightNode;
        }

        private void setLeftNode(Node<E> leftNode){
            this.leftNode = leftNode;
        }

        public Node<E> getRightNode(){
            return this.rightNode;
        }

        public Node<E> getLeftNode(){
            return this.leftNode;
        }

        public Node<E> getParentNode(){
            return this.parentNode;
        }

        private void incrementSize(){
            this.size += 1;
            if(this.parentNode != null) this.parentNode.incrementSize();
        }

        public E getValue(){
            return this.value;
        }

        public int size(){
            return this.size;
        }

    }
    Node<E> rootNode;

    public BinarySearchTree(){
        this.rootNode = new Node<E>();
    }

    public BinarySearchTree(E value){
        this.rootNode = new Node<E>(value);
        this.rootNode.incrementSize();
    }

    public BinarySearchTree(Node<E> rootNode){
        this.rootNode = new Node<E>(rootNode, null);
    }
    public BinarySearchTree(List<E> valueList){
        valueList.forEach(this::add);
    }

    public void add(E element){
        if(rootNode == null) rootNode = new Node<E>();
        if(rootNode.getValue() == null){
            rootNode.setValue(element);
            rootNode.incrementSize();
        }else{
            add(element, rootNode, null);
        }
    }

    private void add(E element, Node node, Node nodeParent){
        E nodeValue = (E) node.getValue();
        if(nodeValue == null) {
            node.setValue(element);
            node.incrementSize();
        }else {
            if (element.compareTo(nodeValue) >= 0) {
                Node<E> rightNode = node.getRightNode();
                if (rightNode == null){
                    node.setRightNode(new Node<E>(null, node));
                }
                add(element, node.getRightNode(), node);
            } else {
                Node<E> leftNode = node.getLeftNode();
                if (leftNode == null){
                    node.setLeftNode(new Node<E>(null, node));
                }
                add(element, node.getLeftNode(), node);
            }
        }
    }



    public E getRootValue(){
        return rootNode.getValue();
    }

    public Node<E> getRootNode(){
        return this.rootNode;
    }

    public int size(){
        return this.rootNode.size();
    }

    public List getSortedListAsc() {
        List list = new ArrayList();
        return getSortedList(rootNode, list, true);
    }

    public List getSortedListDesc() {
        return getSortedList(rootNode, new ArrayList(), false);
    }

    private List getSortedList(Node<E> node, List list, boolean ascending){
        if(ascending){
            if(node.getLeftNode() != null)
                getSortedList(node.getLeftNode(), list, ascending);
                list.add(node.getValue());
            if(node.getRightNode() != null)
                getSortedList(node.getRightNode(), list, ascending);
        }else{
            if(node.getRightNode() != null)
                getSortedList(node.getRightNode(), list, ascending);
            list.add(node.getValue());
            if(node.getLeftNode() != null)
                getSortedList(node.getLeftNode(), list, ascending);
        }
        return list;
    }

    public int countLeafNodes(){
        return countLeafNodes(rootNode, 0);
    }

    private int countLeafNodes(Node node, int count){
        if(node.getLeftNode() != null){
            count = countLeafNodes(node.getLeftNode(), count);
        }
        if(node.getRightNode() != null){
            count = countLeafNodes(node.getRightNode(), count);
        }
        if(node.getRightNode() == null && node.getLeftNode() == null) return ++count;
        return count;
    }
}
