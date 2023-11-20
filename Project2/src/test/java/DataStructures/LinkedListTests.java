/*
@Author: Kelvin Brobbey

This file is being used to test the functionality of the custom LinkedList class
created in the DataStructures folder in the src folder.
*/
package DataStructures;
import com.kelvin.project2.DataStructures.LinkedList;

public class LinkedListTests{
    public static void main(String[]args){
        LinkedList test = new LinkedList();
        System.out.println(test.printList());
        //System.out.println(test.getValue(0));
        test.prepend(5);
        System.out.println(test.printList());
        System.out.println(test.getValue(0));
        test.prepend(0);
        System.out.println(test.printList());
        System.out.println(test.size());
        test.removeLast();
        System.out.println(test.printList());
    }
}
