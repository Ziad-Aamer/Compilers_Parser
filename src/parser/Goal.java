package parser;

import Visitors.Visitor;
import java.util.ArrayList;


public class Goal implements Node{
    
	public MainClass mainClass;
    public ArrayList<ClassDeclaration> classDeclarations;

    public Goal(MainClass mainClass, ArrayList<ClassDeclaration> classDeclarations) {
        this.mainClass = mainClass;
        this.classDeclarations = classDeclarations;
    }

    @Override
    public void Accept(Visitor s) {
        s.visit(this);
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
