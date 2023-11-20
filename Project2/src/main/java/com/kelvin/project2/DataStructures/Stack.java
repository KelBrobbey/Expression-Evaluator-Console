/*
@Author: Kelvin Brobbey

Creating custom Stack class utilizing the LinkedList class we created in this
folder. The top of the stack will be represented by the head
*/
package com.kelvin.project2.DataStructures;
import com.kelvin.project2.DataStructures.LinkedList;

public class Stack<T>{
    //Defining global variables
    private LinkedList<T> list;
    private int height;
    
    //Constructors:
    public Stack(){
        this.list = new LinkedList<>();
        this.height = 0;
    }
    
    //Operations:
    //Push -> Adding a value to the top of the stack
    public void push(T value){
        list.prepend(value);
        height = list.size();
    }
    
    //Pop -> Removing the top element of the stack and returning its value
    public T pop(){
        LinkedList<T>.ListNode node = list.get(0);
        if(node != null){
            list.removeFirst();
            height = list.size();
            return node.val;
        }
        else return null;
    }
    
    public T peek(){
        return list.getValue(0);
    }
    
    //Empty -> Checking to see if the stack is empty or not
    public boolean isEmpty(){
        return height == 0;
    }
}
