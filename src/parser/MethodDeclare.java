package parser;

import Visitors.Visitor;
import java.util.ArrayList;


public class MethodDeclare implements Node{
    public String accessModifier;
    public Type returnType;
    public  Id MethodName;
     public ArrayList<VarDeclaration>Parameters;
    public ArrayList<VarDeclaration>varDeclarations;
    
    public  ArrayList<Statement> statements;
    public Exp Reexpression;

    public MethodDeclare(String accessModifier, Type returnType, Id MethodName, ArrayList<VarDeclaration> Parameters, ArrayList<VarDeclaration> varDeclarations, ArrayList<Statement> statements, Exp Reexpression) {
        this.accessModifier = accessModifier;
        this.returnType = returnType;
        this.MethodName = MethodName;
        this.Parameters = Parameters;
        this.varDeclarations = varDeclarations;
        this.statements = statements;
        this.Reexpression = Reexpression;
    }

    @Override
    public void Accept(Visitor s) {
        s.visit(this);
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
