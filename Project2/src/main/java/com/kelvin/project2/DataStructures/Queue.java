/*
@Author: Kelvin Brobbey

Creating custom Queue class utilizing the LinkedList class we created in this 
folder. The head will represent the first of the queue and the tail the last of
the queue.
*/
package com.kelvin.project2.DataStructures;
import com.kelvin.project2.DataStructures.LinkedList;

public class Queue<T>{
    //Defining global variable
    private LinkedList<T> list;
    private int length;
    
    //Constructors:
    public Queue(){
        this.list = new LinkedList<>();
        this.length = 0;
    }
    
    //Operations:
    //Enqueue -> Adding element to the end of the queue
    public void enqueue(T value){
        list.append(value);
        length = list.size();
    }
    
    //Dequeue -> Removing element from the beginning of the queue
    public T dequeue(){
        LinkedList<T>.ListNode node = list.get(0);
        if(node != null){
            list.removeFirst();
            length = list.size();
            return node.val;
        }
        else return null;
    }
    
    //Empty -> Checking to see if the queue is empty
    public boolean isEmpty(){
        return length == 0;
    }
}
