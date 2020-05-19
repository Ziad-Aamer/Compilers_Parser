/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import Visitors.Visitor;
import java.util.ArrayList;


public class Add implements Node{
    public ArrayList<LessThan>lessThans;

    public Add(ArrayList<LessThan> lessThans) {
        this.lessThans = lessThans;
    }

    @Override
    public void Accept(Visitor s) {
s.visit(this);
    }
    
}
