/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import Visitors.Visitor;


public class Not implements Node{
    public int notLevel;//(!)+
    public PostfixExp postfixExp;

    public Not(int notLevel, PostfixExp postfixExp) {
        this.notLevel = notLevel;
        this.postfixExp = postfixExp;
    }
    
    @Override
    public void Accept(Visitor s) {
        s.visit(this);
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
