/*
@Author: Kelvin Brobbey

Implementing Token class to individualize and break down input expression into
individual tokens -> Operands, operators, parenthesses.
*/
package com.kelvin.project2;

public class Token{
    //Defining enums for all possible individual tokens
    public enum TokenType{
        NUMBER,
        OPERATOR,
        LEFT_PAREN,
        RIGHT_PAREN
    }
    
    //Defining global variables:
    TokenType tokenType;
    String value;
    
    public Token(){}
    
    public Token(TokenType tokenType, String value){
        this.tokenType = tokenType;
        this.value = value;
    }
    
    //Accessory
    public TokenType getTokenType(){ return this.tokenType; }
    public String getValue(){ return this.value; }
    
}
