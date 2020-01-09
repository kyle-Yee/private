package com.zx.stack;

public class StackList {
    int count=0;
    Node top;

    public void push(String data) {
        Node node=new Node(data);
        node.next=top;
        count++;
        top=node;
    }
    public String peek() {
        return top.data;
    }
    public String pop() {
        Node temp=top;
        if (top.next==null) {
            top=null;
        }else {
            top=top.next;
        }
        this.count--;
        return temp.data;
    }
    public int size() {
        return this.count;
    }
    public boolean isEmpty() {
        if (top==null) {
            return true;
        }else {
            return false;
        }
    }
    class Node{
        String data;
        Node next;
        public Node() {}
        public Node(String data) {
            this.data=data;
        }
    }
}