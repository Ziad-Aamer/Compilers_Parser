/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import Visitors.Visitor;
import java.util.ArrayList;


public class ClassDeclaration implements Node{
    public Id className;
    public ArrayList<VarDeclaration> varDeclarations;
    public ArrayList<MethodDeclare> methodDeclares;
    public ArrayList<ConstructorDeclaration> constructorDeclarations;
    
    public ClassDeclaration(Id className, ArrayList<VarDeclaration> varDeclarations, ArrayList<MethodDeclare> methodDeclares, ArrayList<ConstructorDeclaration> constructorDeclarations) {
        this.className = className;
        this.varDeclarations = varDeclarations;
        this.methodDeclares = methodDeclares;
        this.constructorDeclarations = constructorDeclarations;
    }
 @Override
    public void Accept(Visitor s) {
        s.visit(this);
    }   
}
