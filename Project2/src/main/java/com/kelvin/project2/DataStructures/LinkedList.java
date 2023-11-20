/*
@Author: Kelvin Brobbey

Creating custom LinkedList class that will then be used to build a custom stack
and queue implementation.
*/
package com.kelvin.project2.DataStructures;

public class LinkedList<T>{
    //Defining class for single ListNode
    public class ListNode{
        T val;
        ListNode next;
        ListNode(T x){ val = x; next = null; }
    }
    
    //Defining global variables
    private ListNode head;
    private ListNode tail;
    private int length;
    
    //Constructors:
    public LinkedList(){
        this.head = null;
        this.tail = null;
        this.length = 0;
    }
    public LinkedList(T value){
        ListNode newNode = new ListNode(value);
        head = newNode;
        tail = newNode;
        length = 1;
    }
    
    //Accessory and Setter Methods
    public ListNode get(int index){
        if(index < 0 || index >= length) return null;
        else{
            int count = 0;
            ListNode curr = head;
            while(count < index){
                curr = curr.next;
                count++;
            }
            return curr;
        }
    }
    
    public T getValue(int index){
        ListNode answer = get(index);
        if(answer != null) return answer.val;
        else{
            System.err.println("Error, the index is either out of bounds or the linkedlist is already null");
            return null;
        }
    }
    
    public int size(){
        return this.length;
    }
    
    public boolean setValue(int index, T value){
        if(index < 0 || index >= length) return false;
        else{
            ListNode temp = get(index);
            temp.val = value;
            return true;
        }
    }
    
    //Operations:
    //Prepend -> Adding node to the beginning of the list
    public void prepend(T value){
        ListNode newNode = new ListNode(value);
        newNode.next = head;
        head = newNode;
        if(length == 0) tail = newNode;
        length++;
    }
    
    //Append -> Adding node to the end of the list
    public void append(T value){
        ListNode newNode = new ListNode(value);
        if(length == 0){
            head = newNode;
            tail = newNode;
        }
        else{
            tail.next = newNode;
            tail = tail.next;
        }
        length++;
    }
    
    //Middle -> Adding node to a specific index
    public void addAtIndex(int index, T value){
        if(index == 0) prepend(value);
        else if(index == length - 1) append(value);
        else{
            ListNode newNode = new ListNode(value);
            ListNode prev = get(index - 1);
            newNode.next = prev.next;
            prev.next = newNode;
            length++;
        }
    }
    
    //Printing -> Storing the values of linkedlist in a string to print
    public String printList(){
        if(length == 0) return "[]";
        String answer = "[";
        ListNode curr = head;
        while(curr != null){
            if(curr.next != null) answer += curr.val + ", ";
            else answer += curr.val + "]";
            curr = curr.next;
        }
        return answer;
    }
    
    //Remove first -> Removing the first node
    public void removeFirst(){
        if(length != 0){
            ListNode temp = head;
            head = head.next;
            temp.next = null;
            length--;
        }
        if(length == 0) tail = null;
    }
    
    //Remove last -> Removing last node
    public void removeLast(){
        if(length != 0){
            ListNode prev = head;
            ListNode temp = head;
            while(temp.next != null){
                prev = temp;
                temp = temp.next;
            }
            tail = prev;
            tail.next = null;
            length--;
            if(length == 0){ head = null; tail = null; }
        }
    }
    
    //Delete -> Removing node from specific index
    public void delete(int index){
        if(index == 0) removeFirst();
        else if(index == length - 1) removeLast();
        else{
            ListNode pre = get(index - 1);
            ListNode temp = get(index);
            pre.next = temp.next;
            temp.next = null;
            length--;
        }
    }
}