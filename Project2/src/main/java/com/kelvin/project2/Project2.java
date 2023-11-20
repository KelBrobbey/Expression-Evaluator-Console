/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.kelvin.project2;
 
/**
*
* @author kelvinbrobbey
*/
public class Project2 {
 
    public static void main(String[] args) {
        String equation = "9* (55-(20/2*5)   +4)/  3  ";
        ExpressionEvaluator eval = new ExpressionEvaluator(equation);
        eval.describe();
        System.out.println("Prefix Expression for given equation: " + eval.prefix());
        System.out.println("Infix Expression for given equation: " + eval.infix());
        System.out.println("Postfix Expression for given equation: " + eval.postfix());
        System.out.println("Answer for given equation: " + eval.answer());
    }
}
