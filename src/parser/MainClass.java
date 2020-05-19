/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import Visitors.Visitor;


public class MainClass implements Node{
    public Id className;
    public  Id args;
    public  Statement statement;

    public MainClass(Id className, Id args, Statement statement) {
        this.className = className;
        this.args = args;
        this.statement = statement;
    }
   
    @Override
    public void Accept(Visitor s) {
        s.visit(this);
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    } 
}
