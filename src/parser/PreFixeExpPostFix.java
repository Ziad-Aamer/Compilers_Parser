/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import Visitors.Visitor;


public class PreFixeExpPostFix extends PrefixeExp implements Node{
    public PostfixExp postfixExp;

    public PreFixeExpPostFix(PostfixExp postfixExp) {
        this.postfixExp = postfixExp;
    }
    
    @Override
    public void Accept(Visitor s) {
        s.visit(this);
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
