/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import Visitors.Visitor;
import java.util.ArrayList;


public class RestDotId extends RestDot implements Node{
 public Id id;
   public  ArrayList<Exp>exps;

    public RestDotId(Id id, ArrayList<Exp> exps) {
        this.id = id;
        this.exps = exps;
    }
   
    @Override
    public void Accept(Visitor s) {
        s.visit(this);
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
