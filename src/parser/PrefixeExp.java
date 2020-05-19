/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import Visitors.Visitor;


public class PrefixeExp implements Node{

  
    @Override
    public void Accept(Visitor s) {
        s.visit(this);   
    }
    
   
}
