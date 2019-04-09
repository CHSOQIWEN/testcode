package com.bittch.Exception;

import javax.xml.soap.Node;

/**
 * Auther:CHAOQIWEN
 *
 */
public class Test4 {

    
    private Node head;
    public   static class Node{
        public int value;
        public Node next;
        public  Node(int value){
            this.value=value;
            this.next=null;
        }
    }

    public Test4(){

        this.head=null;
    }

    public void pushFront(Node node){
        node.next = this.head;
        this.head=node;

    }
    //查找链表最后一个节点，前提是链表中至少有一个节点
     private Node curlast(){
        if(this.head==null){
            throw new Error();
        }
        Node cur = this.head;
        while(cur.next != null) {
            cur = cur.next;

        }
         return cur;
     }

    public void  pushBack(Node node){
        if(this.head==null){
            this.head=node;
        }else {
            Node node1 = curlast();
            node1.next = node;

        }
    }
    public  void popFront(){
        if(this.head == null){
            throw new Error();
        }else{
            this.head = this.head.next;
        }
    }
    private Node curLastLast(){
        Node cur1 = null;
        while(cur1.next.next != null  ){
            cur1 = cur1.next;
        }
        return cur1;
    }
    public  void  popBack(){
        if(this.head == null){
            throw new Error();
        }else {
            Node node2 = curLastLast();
            node2.next = null;
        }
    }
    public void display(){
        //如何通过循环，遍历链表的每一个节点
        Node cur = this.head;
        while (cur !=null) {
            System.out.println(cur.value);
            cur=cur.next;

        }
    }

    public static void main(String[] args) {
        Test4 test4 = new Test4();
        test4.pushFront(new Node(1));
        test4.pushBack(new Node(2));

        test4.display();
    }


}
