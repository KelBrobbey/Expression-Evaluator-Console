/*
Author: Kelvin Brobbey

This class will accept a string representing a mathematical equation. After receiving
the string, should be able to compute equation and return prefix, infix, and postfix.
NO BUILT IN LIBRARIES ALLOWED FOR DATA STRUCTURES (e.g. ArrayLists)
*/
package com.kelvin.project2;
import com.kelvin.project2.DataStructures.*;
 
 
public class ExpressionEvaluator{
    //Defining global variables:
    private String equation;
    private LinkedList<Token> list;
   
    //Constructor
    public ExpressionEvaluator(String equation){
        this.equation = equation;
        this.list = new LinkedList<>();
       tokenize();
    }
   
    //Accessory and Setter Methods
    public String getEquation(){ return this.equation; }
    public void setEquation(String equation){ this.equation = equation; tokenize(); }
   
    //Tokenize the expression -> breaking down expression into individual tokens, stored in list
    private void tokenize(){
        int count = 0;
        StringBuilder number;
        while(count < equation.length()){
            char curr = equation.charAt(count);
            if(isDigit(curr)){
                number = new StringBuilder();
                while(count < equation.length() && isDigit(equation.charAt(count))){
                    number.append(equation.charAt(count));
                    count++;
                }
                Token toke = new Token(Token.TokenType.NUMBER, number.toString());
                list.append(toke);
            }
            else if(isOperator(curr)){
                Token toke = new Token(Token.TokenType.OPERATOR, "" + curr);
                list.append(toke);
                count++;
            }
            else if(curr == '('){
                Token toke = new Token(Token.TokenType.LEFT_PAREN, "" + curr);
                list.append(toke);
                count++;
            }
            else if(curr == ')'){
                Token toke = new Token(Token.TokenType.RIGHT_PAREN, "" + curr);
                list.append(toke);
                count++;
            }
            else if(isWhitespace(curr)) count++;
            else System.err.println("Invalid character: " + curr);
           
        }
    }
    //Methods to check if it is an operator, digit, or whitespace
    private boolean isOperator(char c){ return c == '+' || c == '-' || c == '*' || c == '/' || c == '^'; }
    private boolean isDigit(char c){ return c >= '0' && c <= '9'; }
    private boolean isWhitespace(char c) { return c == ' ' || c == '\t' || c == '\n'; }
   
    //Operations:
    //Method to display equation and result similar to math equation
    public void describe(){
        System.out.println("Expression and result: " + this.infix() + " = " + this.answer());
    }
 
    //Method to get infix expression notation from list
    public String infix(){
        StringBuilder answer = new StringBuilder();
        int count = 0;
        Token curr;
        while(count < list.size() - 1){
            curr = list.getValue(count);
            if(curr.value.equals("(")) answer.append(curr.value);
            else if(curr.tokenType == Token.TokenType.NUMBER && list.getValue(count + 1).tokenType == Token.TokenType.RIGHT_PAREN) answer.append(curr.value);
            else answer.append(curr.value + " ");
            count++;
        }
        curr = list.getValue(list.size() - 1);
        answer.append(curr.value);
        return answer.toString();
    }
 
    //Method to get postfix expression notation from list
    public String postfix(){
        StringBuilder answer = new StringBuilder();
        Stack<String> operators = new Stack<>();
        int count = 0;
        Token curr;
        while(count < list.size()){
            curr = list.getValue(count);
            if(curr.tokenType == Token.TokenType.NUMBER) answer.append(curr.value + " ");
            else if(curr.tokenType == Token.TokenType.OPERATOR){
                while(!operators.isEmpty() && getPrecedence(operators.peek()) >= getPrecedence(curr.value)) answer.append(operators.pop() + " ");
                operators.push(curr.value);
            }
            else if(curr.tokenType == Token.TokenType.LEFT_PAREN) operators.push(curr.value);
            else if(curr.tokenType == Token.TokenType.RIGHT_PAREN){
                while(!operators.isEmpty() && !operators.peek().equals("(")) answer.append(operators.pop() + " ");
                operators.pop();
            }
            count++;
        }
        
        while(!operators.isEmpty()) answer.append(operators.pop() + " ");
        return answer.toString().trim();
    }
 
    //Method to get prefix expression notation from list
    public String prefix(){
        StringBuilder prefix = new StringBuilder();
        Stack<String> operators = new Stack<>();
        Token curr;
        for(int i = this.list.size() - 1; i >= 0; i--){
            curr = this.list.getValue(i);
            if(curr.tokenType == Token.TokenType.NUMBER) prefix.insert(0, " " + curr.value);
            else if(curr.tokenType == Token.TokenType.RIGHT_PAREN) operators.push(curr.value);
            else if(curr.tokenType == Token.TokenType.LEFT_PAREN){
                while(!operators.isEmpty() && !operators.peek().equals(")")) prefix.insert(0, " " + operators.pop());
                operators.pop();
            }
            else if(curr.tokenType == Token.TokenType.OPERATOR){
                while(!operators.isEmpty() && getPrecedence(operators.peek()) > getPrecedence(curr.value)) prefix.insert(0, " " + operators.pop());
                operators.push(curr.value);
            }
        }
 
        while(!operators.isEmpty()) prefix.insert(0, " " + operators.pop());
        return prefix.toString().trim();
 
    }
 
    //Method to determine precedence for operators
    private int getPrecedence(String c){
        if(c.equals("+") || c.equals("-")) return 1;
        else if(c.equals("*") || c.equals("/")) return 2;
        else if(c.equals("^")) return 3;
        else return -1;
    }
 
    //Returning answer for expression given, using technique to solve Reverse Polish Notation
    public int answer(){
        String expression = this.postfix();
        Stack<Integer> nums = new Stack<>();
        int count = 0;
        while(count < expression.length()){
            if(isDigit(expression.charAt(count))){
                int result = 0;
                while(isDigit(expression.charAt(count))){
                    result = result * 10 + (expression.charAt(count) - '0');
                    count++;
                }
                nums.push(result);
            }
            else if(isWhitespace(expression.charAt(count))) count++;
            else{
                int num1 = nums.pop();
                int num2 = nums.pop();
                if(expression.charAt(count) == '+') num2 += num1;
                else if(expression.charAt(count) == '-') num2 -= num1;
                else if(expression.charAt(count) == '*') num2 *= num1;
                else if(expression.charAt(count) == '/') num2 /= num1;
                else if(expression.charAt(count) == '^') num2 = exponential(num2, num1);
                nums.push(num2);
                count++;
            }
        }
        return nums.peek();
    }
 
    //Method for the exponent operation
    private int exponential(int num, int pow){
        if(pow == 0) return 1;
        if(pow == 1) return num;
        int answer = 1;
        while(pow > 0){
            answer *= num;
            pow--;
        }
        return answer;
    }
   
}