package parser;

import Visitors.Visitor;
import java.util.ArrayList;


public class ConstructorDeclaration implements Node{

    public  Id className;
    public ArrayList<VarDeclaration>Parameters;
    public ArrayList<VarDeclaration>varDeclarations;    
    public ArrayList<Statement> statements;

    public ConstructorDeclaration(Id className, ArrayList<VarDeclaration> Parameters, ArrayList<VarDeclaration> varDeclarations, ArrayList<Statement> statements) {
    	this.className = className;
        this.Parameters = Parameters;
        this.varDeclarations = varDeclarations;
        this.statements = statements;

    }

    @Override
    public void Accept(Visitor s) {
        s.visit(this);
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
