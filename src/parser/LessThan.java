/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import Visitors.Visitor;
import java.util.ArrayList;


public class LessThan implements Node{
    public ArrayList<Additive2>additives2;

    public LessThan(ArrayList<Additive2> additives) {
        this.additives2 = additives;
    }
    
    @Override
    public void Accept(Visitor s) {
        s.visit(this);
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
