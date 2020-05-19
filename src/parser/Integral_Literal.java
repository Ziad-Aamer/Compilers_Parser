/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import Visitors.Visitor;


public class Integral_Literal extends PrimaryExp implements Node{
    public String v;

    public Integral_Literal(String v) {
        this.v = v;
    }
    
    @Override
    public void Accept(Visitor s) {
        s.visit(this);
       
    }
}
