/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.util.ArrayList;


public class ClassDeclarationExtends extends ClassDeclaration implements Node{
    public Id superClass;

    public ClassDeclarationExtends(Id superClass, Id className, ArrayList<VarDeclaration> varDeclarations, ArrayList<MethodDeclare> methodDeclares, ArrayList<ConstructorDeclaration> constructorDeclarations) {
        super(className, varDeclarations, methodDeclares, constructorDeclarations);
        this.superClass = superClass;
    }

   
    
}
