/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visitors;

import parser.*;


public class Visitor {
    public void visit(Goal x)
    {
        
        if(x.mainClass!=null)
            x.mainClass.Accept(this);
        for(ClassDeclaration a:x.classDeclarations)
        {
            if(a!=null)
                a.Accept(this);
        }
    }
    public void visit(Not x)
    {
        for(int i=0;i<x.notLevel;i++)
            System.out.print("!");
        if(x.postfixExp!=null)
        {
            x.postfixExp.Accept(this);
        }
        
    }
    public void visit(MainClass m)
    {
        System.out.print("class ");
        if(m!=null)
        {
            if(m.className!=null)
                m.className.Accept(this);
            System.out.println("{");
            System.out.print("public static void Main( String[ ]");
            if(m.args!=null)
                m.args.Accept(this);
            System.out.print(")");
            System.out.print("\n{");
            if(m.statement!=null)
                m.statement.Accept(this);
            System.out.println("}}");
            
        }
        
    }
    public void visit(Add a)
    {
        if(a.lessThans.size()>0)
        {
            a.lessThans.get(0).Accept(this);
            for(int i=1;i<a.lessThans.size();i++)
            {
                System.out.print("\t&&");
                a.lessThans.get(i).Accept(this);
            }
            
        }
    }
    public void visit(Additive a)
    {
            if(a.times!=null)
            {
                a.times.Accept(this);
                
            }
            if(a.restAdditive!=null)
            {
                    a.restAdditive.Accept(this);
               
            }
    }
    
    public void visit(AdditiveMinus a)
    {
        for(int i=0;i<a.timeses.size();i++)
        {System.out.print("-");
            a.timeses.get(i).Accept(this);
        }
    }
    public void visit(AdditivePlus a)
    {
        
        for(int i=0;i<a.timeses.size();i++)
        {System.out.print("+");
            a.timeses.get(i).Accept(this);
        }
    }public void visit(LessThan a)
    {
        if(a.additives2.size()>0)
        {
            a.additives2.get(0).Accept(this);
            for(int i=1;i<a.additives2.size();i++)
            {
                System.out.print("<");
                a.additives2.get(i).Accept(this);
            }
            
        }
    }
    public void visit(Times a)
    {
    if(a.prefixeExps.size()>0)
    {
        a.prefixeExps.get(0).Accept(this);
        
        for(int i=1;i<a.prefixeExps.size();i++)
        {
            System.out.print("*");
            a.prefixeExps.get(i).Accept(this);
        }
        
        
        
    }
    }
    public void visit(Statement a)
    {
    }public void visit(StatementBlock a)
    {
        System.out.print("{");
        for(Statement s:a.statements)
        {
            if(a!=null)
                a.Accept(this);
        }
        System.out.print("}");
        
    }
    public void visit(StatementIdentifier a)
    {
        if(a.id!=null)
            a.id.Accept(this);
        if(a.rest!=null)
            a.rest.Accept(this);
    }
    public void visit(StatementIdentifierRest a)
    {
        
    }
    public void visit(StatementIdentifierRestArrayIndex a)
    {
        System.out.print("[");
        if(a.exp1!=null)
            a.exp1.Accept(this);
        System.out.print("]");
        System.out.print("=");
        if(a.exp2!=null)
            a.exp2.Accept(this);
        System.out.print(";");
                
                
    }
    public void visit(StatementIdentifierRestAssign a)
    {System.out.print("=");
    if(a.exp!=null)
        a.exp.Accept(this);
        System.out.print(";");
    }
    public void visit(StatementIf a)
    {
        System.out.print("if(");
        if(a.exp!=null)
            a.exp.Accept(this);
         System.out.print(")\n");
        if(a.statement!=null)
            a.statement.Accept(this);
        if(a.sir!=null)
            a.sir.Accept(this);
                
       
                
                
    }
    public void visit(StatementIfRest a)
    {
        
    }
    public void visit(StatementIfRestMatched a)
    {System.out.print("\nelse\n");
    if(a.s!=null)
        a.s.Accept(this);
    }
    public void visit(StatementPrint a)
    {
        System.out.print("System.out.println(");
        if(a.exp!=null)
            a.exp.Accept(this);
        System.out.print(")");
        System.out.print(";\n");
    }
    public void visit(StatementWhile a)
    {
        System.out.print("while(");
        if(a.exp!=null)
            a.exp.Accept(this);
        System.out.println(")");
        if(a.statement!=null)
            a.statement.Accept(this);
    }
    public void visit(Exp a)
    {
        if(a.add!=null)
        {
            a.add.Accept(this);
        }
    }
   public void visit(TypeBoolean a)
    {
        System.out.print("boolean");    
    }
    public void visit(TypeBooleanArray a)
    {
      System.out.print("boolean []");   
    }public void visit(TypeChar a)
    {System.out.print("char"); 
    }public void visit(TypeCharArray a)
    {
        System.out.print("char []"); 
    }
    public void visit(TypeFloat a)
    {System.out.print("float");
    }
    public void visit(TypeFloatArray a)
    {
        System.out.print("float []");
    }public void visit(TypeInt a)
    {
        System.out.print("int");
    }public void visit(TypeIntArray a)
    {System.out.print("int []");
    }public void visit(TypeString a)
    {System.out.print("String");
    }
    public void visit(TypeStringArray a)
    {System.out.print("String []");
    }public void visit(PrimaryExp a)
    {
        if(a!=null)
            a.Accept(this);
    }
    public void visit(Id i)
    {
        if(i!=null)
        System.out.print(""+i.i);
    }
    public void visit(PrimaryExpNewRest a)
    {
      
       
    }
    public void visit(PrimaryExpNew a)
    {System.out.print("new");
    if(a.rest!=null)
    {
        a.rest.Accept(this);
        
    }
    }
    public void visit(PrimaryExpNewRestId a)
    {
        if(a.id!=null)
        {
            a.id.Accept(this);
            System.out.print("()");
        }
    }
    public void visit(PrimaryExpNewRestInt a)
    {
        System.out.print("int[");
        if(a.exp!=null)
            a.exp.Accept(this);
        System.out.println("]");
    }public void visit(RestAdditive a)
    {
    }public void visit(RestDot a)
    {
    }
    
    public void visit(RestDotId a)
    {
        if(a.id!=null)
            a.id.Accept(this);
        System.out.print("(");
        if(a.exps.size()>0){
    if( a.exps.get(0)!=null)
        a.exps.get(0).Accept(this);
        for(int i=1;i<a.exps.size();i++)
        { System.out.print(",");
              a.exps.get(i).Accept(this);
             
            
        }}
        System.out.print(")");
    }
    
    public void visit(RestDotLength a)
    {
        System.out.print("length");
    }
    public void visit(RestPostFix a)
    {
    }
    
    public void visit(RestPostFixDot a)
    {
        System.out.print(".");
        if(a.restDot!=null)
            a.restDot.Accept(this);
    }
    public void visit(RestPostFixP a)
    {
        System.out.print("[");
     
        if(a.exp!=null)
            a.exp.Accept(this);
        System.out.print("]");
    }
    public void visit(PreFixeExpNot a)
    {
        if(a.not!=null)
        {
            a.not.Accept(this);
            
        }
    }
    public void visit(PreFixeExpPostFix a)
    {if(a.postfixExp!=null)
        a.postfixExp.Accept(this);
      
    }
    public void visit(PrefixeExp a)
    {
      
    }
    public void visit(PostfixExp a)
    {
        if(a.primaryExp!=null)
            a.primaryExp.Accept(this);
        
        for(RestPostFix b:a.restPostFix)
        {
            if(b!=null)
            b.Accept(this);
        }
    }
    public void visit(MethodDeclare a)
    {
        
        System.out.print(a.accessModifier+" ");
        if(a.returnType!=null)
            a.returnType.Accept(this);
        if(a.MethodName!=null)
            a.MethodName.Accept(this);
        System.out.print("(");
        if(a.Parameters.size()>0)
        {if(a.Parameters.get(0)!=null)
        {
            a.Parameters.get(0).type.Accept(this);
            System.out.print(" ");
             a.Parameters.get(0).identifier.Accept(this);
            
        }
            for (int i = 1; i < a.Parameters.size(); i++) {
                System.out.println(", ");
                if(a.Parameters.get(i).type!=null&&a.Parameters.get(0).identifier!=null)
        {
            a.Parameters.get(i).type.Accept(this);
             System.out.print(" ");
             a.Parameters.get(i).identifier.Accept(this);
        }
            }
        }
        System.out.print(")");
        System.out.print("{\n");
        for(VarDeclaration declaration:a.varDeclarations)
        { if(declaration!=null)
        {
            declaration.Accept(this);
            System.out.print("\n");
        }
            
            
            }
         for(Statement declaration:a.statements)
        { if(declaration!=null)
        {
            declaration.Accept(this);
            System.out.print("\n");
        }
            
            
            }
         System.out.print("\treturn\t");
         if(a.Reexpression!=null)
             a.Reexpression.Accept(this);
         System.out.print(";");
            System.out.print("\n}");
    }
    public void visit(ClassDeclarationExtends cd)
    {
        System.out.print("class");
        if(cd.className!=null)
            cd.className.Accept(this);
        System.out.print("\textends\t");
        if(cd.superClass!=null)
            cd.superClass.Accept(this);
        System.out.print("\t{\n");
        for(VarDeclaration a:cd.varDeclarations)
        {
            if(a!=null)
                a.Accept(this);
        }
        for(MethodDeclare a:cd.methodDeclares)
        {
            if(a!=null)
                a.Accept(this);
        }     
        System.out.print("\n}");
    
    }
    public void visit( ClassDeclaration cd)
    {
        System.out.print("class");
        if(cd.className!=null)
            cd.className.Accept(this);
        System.out.print("{\n");
        for(VarDeclaration a:cd.varDeclarations)
        {
            if(a!=null)
                a.Accept(this);
        }
        for(ConstructorDeclaration a:cd.constructorDeclarations)
        {
            if(a!=null)
                a.Accept(this);
        }
        for(MethodDeclare a:cd.methodDeclares)
        {
            if(a!=null)
                a.Accept(this);
        }     
        System.out.print("\n}");
    }
    public void visit(VarDeclaration a)
    {
         if(a.type!=null)
        a.type.Accept(this);
         System.out.println("\t");
    if(a.identifier!=null)
            a.identifier.Accept(this);
        System.out.print(";\n");
   
    }
    public void visit(True a)
    {System.out.print("true");
    }public void visit(Flase a)
    {
        System.out.print("false");
    }public void visit(Integral_Literal a)
    {
       if(a!=null)
            System.out.print(a.v);
    }public void visit(This a)
    {
        System.out.print("this");
    }public void visit(InsideExp a)
    {
        System.out.print("(");
        if(a.exp!=null)
            a.exp.Accept(this);
        System.out.print(")");
    }

    public void visit(Additive2 aThis) {
        if(aThis.timeses!=null&&aThis.al!=null)
        {
            if(aThis.timeses.size()>0)
            {
                aThis.timeses.get(0).Accept(this);
                
                
            }
          
            for(int i=1;i<aThis.timeses.size();i++)
            {
                System.out.print(""+aThis.al.get(i-1));
               aThis.timeses.get(i).Accept(this); 
                
            }
            
        }
    }
	public void visit(ConstructorDeclaration a) {
		// TODO Auto-generated method stub

        if(a.className!=null)
        	a.className.Accept(this);
        System.out.print("(");
        if(a.Parameters!=null && a.Parameters.size()>0)
        {if(a.Parameters.get(0)!=null)
        {
            a.Parameters.get(0).type.Accept(this);
            System.out.print(" ");
             a.Parameters.get(0).identifier.Accept(this);
            
        }
            for (int i = 1; i < a.Parameters.size(); i++) {
                System.out.println(", ");
                if(a.Parameters.get(i).type!=null&&a.Parameters.get(0).identifier!=null)
        {
            a.Parameters.get(i).type.Accept(this);
             System.out.print(" ");
             a.Parameters.get(i).identifier.Accept(this);
        }
            }
        }
        System.out.print(")");
        System.out.print("{\n");
        for(VarDeclaration declaration:a.varDeclarations)
        { if(declaration!=null)
        {
            declaration.Accept(this);
            System.out.print("\n");
        }
            
            
            }
         for(Statement declaration:a.statements)
        { if(declaration!=null)
        {
            declaration.Accept(this);
            System.out.print("\n");
        }
            
            
         }
            System.out.print("\n}\n");
	}

   
}
