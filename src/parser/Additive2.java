/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import Visitors.Visitor;
import java.util.ArrayList;


public class Additive2 implements Node{
     public ArrayList<Times>timeses;
     public ArrayList<String>al;

    public Additive2(ArrayList<Times> timeses, ArrayList<String> al) {
        this.timeses = timeses;
        this.al = al;
    }

    @Override
    public void Accept(Visitor s) {
        s.visit(this);
    }
     
}
