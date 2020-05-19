/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import Visitors.Visitor;
import java.util.ArrayList;

public class AdditivePlus  extends RestAdditive implements Node{
    public ArrayList<Times>timeses;

    public AdditivePlus(ArrayList<Times> timeses) {
        this.timeses = timeses;
    }
    @Override
    public void Accept(Visitor s) {
        s.visit(this);
    }
}
