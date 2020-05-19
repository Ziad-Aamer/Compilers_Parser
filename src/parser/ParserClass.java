/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import Lexical.Token;
import Visitors.Visitor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class ParserClass {
    Exp start;
    
    Queue<Token>PTokens;
    public boolean IntError;
    //ArrayList<Token> LexResult;
    
    public ParserClass(ArrayList<Token> LexResult) throws IOException {
    	//System.out.println("SIZE1 IS" + LexResult.size());
       LexResult = readInputFromFile();
       IntError=false;
        PTokens=new LinkedList<>();
        for(int i=0;i<LexResult.size();i++)
            if(!LexResult.get(i).name.equals("EOL")&&
                    !LexResult.get(i).name.equals("COMMENT1")
                    &&!LexResult.get(i).name.equals("COMMENT2"))
            {
                PTokens.add(LexResult.get(i));
            }
        
      }
    
    public ArrayList<Token>  readInputFromFile() throws IOException {
    	ArrayList<Token> LexResult1 = new ArrayList<>();
      	 BufferedReader br = new BufferedReader(new FileReader("f2.txt"));
    	String line = null;

	    while ((line = br.readLine()) != null) {
	      String[] values = line.split("@");
	      if(values.length==2){
	    	  //System.out.println(values[0]+ "-" + values[1]);
	      	LexResult1.add(new Token(values[0],values[1]));
	      }
	      else {
	    	  
	      }
	    	  
	     }
	    /*
	    for(int i=0;i<LexResult1.size();i++) {
	    	if(LexResult1.get(i).name.equals("EOL"))
	    	{
	    		LexResult1.get(i).value="\n";
	    	}
	    	//System.out.println(LexResult1.get(i).value+ "-" + LexResult1.get(i).name);
	    }
	    //System.out.println("SIZE2 IS" + LexResult1.size());
	     * 
	     */
	    br.close();
	    return LexResult1;
    }
    
    public Token match(String s)
    {
        if(PTokens.isEmpty())
        return null;
        //System.out.println(""+PTokens.element().name+" "+s+" "+PTokens.element().value);
            if(!PTokens.element().name.equals(s)||PTokens.isEmpty())
        return null;
        Token poll = PTokens.poll();
        //System.out.println(PTokens.element().name +" " +PTokens.element().value);
            return poll;
    }
    public void parse()
    {
      Goal g=Goal();
      //Exp g=exp();
       
        if(PTokens.isEmpty()&&g!=null)
            System.out.println("Syntax True");
        else System.out.println("False Syntax");
        Visitor s = new Visitor();
      //here the g object is the root and all of its children are nodes of tokens that has a value and name
       g.Accept(s);
    }
    public Exp  exp()
            
    {
        Add s=and();
    if(s!=null)
    {
       return  new Exp(s);
    }
    return null;
        
    }
    public Additive2 additive2()
    {
       ArrayList<Times>al=new ArrayList<>();
       ArrayList<String>aa=new ArrayList<>();
        Times l;
        l=times();
      if(l==null)
            return null;
      al.add(l);
        while(true)
        {  
            
            Token b=match("MINUS");
            if(b==null)
            {
                b=match("PLUS");
            }
            if(b==null)
                return new Additive2(al, aa) ;
            l=times();
            if(l==null)
            return null;
          al.add(l);
          aa.add(b.value);
            
        }
         
    }
            
    public Add and()
            
    { 
        ArrayList<LessThan>al=new ArrayList<>();
        LessThan l;
        l=lessThan();
      if(l==null)
            return null;
        while(true)
        {  
            al.add(l);
            Token b=match("AND");
            if(b==null)
                return new Add(al) ;
            l=lessThan();
            if(l==null)
            return null;
          
        }
        
    }
    
    public LessThan  lessThan()
      {
        ArrayList<Additive2>al=new ArrayList<>();
        Additive2 ad;
        ad=additive2(); 
        if(ad==null)
         return null;
        while(true)
        { al.add(ad);
            Token b=match("LESSTHAN");
            if(b==null)
                return new LessThan(al);
         ad=additive2();
         if(ad==null)
             return null;
          
               
        }
    }
    
    public Additive  additive()
            
    {  
        Times t=times();
     
        RestAdditive rs=restAdditive();
            
        if(t!=null)
            return new Additive(t, rs);
       
        return null;
        
    }
    
    public RestAdditive  restAdditive()
            
    {
        AdditivePlus p=additivePlus();
        if(p!=null)
        {
            return p;}
    AdditiveMinus m=additiveMinus();
    if(m!=null)
        return m;
         
    
        return null; 
    }
    
    public AdditivePlus  additivePlus()
            
    { 
        ArrayList<Times>t=new ArrayList<>();
        Times s;
        Token b;
        
        while(true){
        b=match("PLUS");
        if(b==null&&t.size()>0)
            return new AdditivePlus(t);
        else if(b==null)
            return null;
        s=times();
        if(s==null)
        {
        //    IntError=true;
            return null;
        }
        t.add(s);
        
        }
    }
    
    public AdditiveMinus  additiveMinus()
            
    {
        
      ArrayList<Times>t=new ArrayList<>();
        Times s;
        while(true){
          Token b=match("MINUS");
        if(b==null&&t.size()>0)
        {
            return new AdditiveMinus(t);
        }
        else if(b==null)
            return null;
        s=times();
        if(s==null)
        {
           // IntError= true;
            return null;
        }
        t.add(s);
        
        }
    
    }
    
    public Times  times()
            
    {
        PrefixeExp p;
        Token b;
        ArrayList<PrefixeExp>pp = new ArrayList<>();
        p=preFix();
        if(p==null)
        return null;
           
        while(true)
        {pp.add(p);
            b=match("MULTIPLY");
            if(b==null)
                return new Times(pp);
            p=preFix();
            if(p==null)
                return null;
            
        }
        
    }
    
    public PrefixeExp  preFix()
            
    {
       
      
        PreFixeExpPostFix pp=prefixPostFix();
        if(pp!=null)
            return pp;
      
        PreFixeExpNot p=prefixNot();
        if(p!=null)
            return p;
         return null;
    }
    
    public PreFixeExpNot  prefixNot()
            
    {
        Not n=not();
    if(n!=null)
        return new PreFixeExpNot(n);
         return null;
        
    }
    
    public Not  not()
            
    {
        PostfixExp p;
        Token b;
        b=match("NOT");
        int i=0;
        if(b==null)
            return null;
        i++;
        while(true)
        {
            b=match("NOT");
            if(b==null)
                break;
            i++;
        }
        p=postFix();
            if(p==null)
                return null;
            return new Not(i, p);
             
       
    }
    
    public PreFixeExpPostFix  prefixPostFix()
            
    {
       PostfixExp p=postFix();
       if(p==null)
      return null;
    
        return new PreFixeExpPostFix(p);
    }
    
    public PostfixExp  postFix()
    {
        PrimaryExp p=Primaryexp();
        if(p==null)
        return null; 
        
    ArrayList<RestPostFix>al=new ArrayList<>();
    RestPostFix pp;
    while(true)
    {
        pp=restPostFix();
        if(pp==null)
        {
            return new PostfixExp(p, al);}
      
            al.add(pp);
    }
    }
    
    public RestPostFix  restPostFix()
            
    {
        Exp e;
        RestDot rd;
        if(match("LEFT_SQUARE_B")!=null)
        {IntError=true;
            e=exp();
        if(e==null)   
         return null;
           if(match("RIGHT_SQUARE_B")!=null)
           {IntError=false;
               return new RestPostFixP(e);
           }  
          
        }
        else if(match("DOT")!=null)
        {IntError=true;
            rd=restDot();
            if(rd!=null)
            {IntError=false;
                return new RestPostFixDot(rd);}
        }
         return null;
         }
    
    public RestDot  restDot()
            
    {
        ArrayList<Exp>al=new ArrayList<>();
    Exp e;
        if(match("LENGTH")!=null)
            return new RestDotLength();
        Id i=id();
        
         if(i!=null)
        {
            if(match("LEFT_ROUND_B")!=null)
            {
                e=exp();
          if(e!=null)
                {  
                    al.add(e);
                while(true)
                {
                    if(match("COMMA")==null)
                    {   
                        break;}
                    e=exp();
                    if(e==null)
                        return null;
                    al.add(e);
                }
                }
                if(match("RIGHT_ROUND_B")!=null)
                { 
                    return new RestDotId(i, al);}
            }
            else
            {
                return null;
            }
            
        }
      return null;     
    }
    public PrimaryExp  Primaryexp()
            
    {
        Token t=match("INTEGER_LITERAL");
        
        if(t!=null)
        { 
            return new Integral_Literal(t.value);
            
        }
        
        else if(match("TRUE")!=null)
            return new True();
              else if(match("FALSE")!=null)
            return new Flase();
              else if(match("THIS")!=null)
            return new This();
              else if(match("LEFT_ROUND_B")!=null)
              {
                Exp e=exp();
                if(e==null)
                    return null;
                
                if(match("RIGHT_ROUND_B")!=null)
                    return new InsideExp(e);
                return null;
              }
            
        else if(match("NEW")!=null)
            {
                PrimaryExpNewRest rr=primaryExpNewRest();
                if(rr!=null)
                    return new PrimaryExpNew(rr);
                else return null;
            }
        Id i=id();
         if(i!=null)
            {
                return i;
            }
      return null;   
        
    }
    
    
    public Id  id()      
    {
        Token t=match("ID");
        if(t!=null)
            return new Id(t.value);
      return null;   
    }
     
    public PrimaryExpNewRest primaryExpNewRest()
    {
        if(match("INT")!=null)
        {
           if(match("LEFT_SQUARE_B")!=null)
           {
               Exp e=exp();
               if(e!=null
                       )
               {
                   if(match("RIGHT_SQUARE_B")!=null)
                       return new PrimaryExpNewRestInt(e);
                  
               }
              
           }
        }
        else 
        {Id i=id();
            if(i!=null)
            {
                if(match("LEFT_ROUND_B")!=null)
                {
                    if(match("RIGHT_ROUND_B")!=null)
                        return new PrimaryExpNewRestId(i);
                    
                }
             
            }
            
        }
        return null;
    }
  public Goal Goal()
  {
      MainClass mc=MainClass();
      ArrayList<ClassDeclaration>al=new ArrayList<>();
      ClassDeclaration c;
  
	  if(mc!=null)
	  {
	      
	  while(true)
	  {
	      c=classDecalation();
	      if(c!=null)
	      {
	          al.add(c);
	      }
	      
	           
	      else{
	          return new Goal(mc, al);}
	         
	  }
      
	  }
      
     return null;
  }
   public MainClass MainClass()
  {
      if(match("CLASS")!=null)
      {
          Id id=id();
         boolean B=id==null;
          Token b=match("LEFT_CURLY_B");
          Token c=match("PUBLIC");
          Token d=match("STATIC");
          Token e=match("VOID");
          Token f=match("MAIN");
          Token g=match("LEFT_ROUND_B"); 
          Token h=match("STRING"); 
          Token i=match("LEFT_SQUARE_B"); 
          Token j=match("RIGHT_SQUARE_B"); 
          Token K=match("ID"); 
          Token L=match("RIGHT_ROUND_B"); 
          Token M=match("LEFT_CURLY_B"); 
         
      if(!B&&b!=null&&c!=null&&d!=null
         &&e!=null&&f!=null&&g!=null
        &&h!=null&&i!=null&&j!=null&K!=null&&L!=null&&M!=null){
          
      Statement s=Statement();
      
      if(s!=null)
      {
         Token LL=match("RIGHT_CURLY_B"); 
       Token KK=match("RIGHT_CURLY_B"); 
       if(LL!=null&&KK!=null)
           return new MainClass(id, new Id(K.value), s);
          
          
      }
      }
          
      }
      
   return null;
  } public ClassDeclaration classDecalation()
  {
      ArrayList<VarDeclaration>al=new ArrayList<>();
      VarDeclaration d;
      ArrayList<MethodDeclare>al1=new ArrayList<>();
      MethodDeclare md;
      ArrayList<ConstructorDeclaration> al2= new ArrayList<>();
      ConstructorDeclaration cd;
        Token b2,b = match("CLASS");
        Id i=null;
              
      boolean flag=false;
      if(b==null)
      {
          return  null;
      }
      Id i2=id();
      
      if(i2!=null)
      {
          if(match("EXTEND")!=null)
          {  i=id();
              if(i!=null)
              flag=true;
              else{
            //  IntError=true;
              return null;
              }
          }
         if(match("LEFT_CURLY_B")!=null)
         {
             while(true)
             {d=VarDeclaration();
             if(d!=null)
                 al.add(d);
                 else
                 break;
             }
             while(true)
             {
                 cd=constructorDeclaration(i2.i);
                 if(cd!=null)
                 al2.add(cd);
          
                 else
                 break;
             }
             while(true)
             {
                 md=MethodDeclaration();
                 if(md!=null)
                 al1.add(md);
          
                 else
                 break;
             }
             if(match("RIGHT_CURLY_B")!=null)
             {
                 if(flag)
                 {
                     return new ClassDeclarationExtends(i,i2 ,al, al1, al2);
                 }
                 else
                 {
                     return new ClassDeclaration(i2, al, al1, al2);
                 }
             }
         }
      }
     // IntError=true;
      return null;
  } public MethodDeclare MethodDeclaration()
  {
      String accessModifier="";

	  ArrayList<VarDeclaration>v1=new ArrayList<>();
	  ArrayList<VarDeclaration>v2=new ArrayList<>();
	  ArrayList<Statement>s=new ArrayList<>();
	  Exp returnE;
      
	  if(match("PUBLIC")!=null)
    	  accessModifier="public";
      else if(match("PRIVATE")!=null)
    	  accessModifier="private";
      else if(match("PROTECTED")!=null)
    	  accessModifier="protected";
      else
    	  return null;
      
      Type t1=Type(),t2;
      Id i1=id(),i2;
        Token bb=match("LEFT_ROUND_B");
      if(t1!=null&&i1!=null&&bb!=null)
      {
        v1=checkParam();
        
        
          Token B=match("RIGHT_ROUND_B");
          Token B2=match("LEFT_CURLY_B");
         if(B!=null&&B2!=null)
         {
             while(true)
             {
                 VarDeclaration v=VarDeclaration();
                 if(v!=null)
                 {
                     v2.add(v);
                 }
                 else 
                 break;
             }
             while(true)
             {
                 Statement ss=Statement();
                 if(ss!=null)
                     s.add(ss);
                 else break; 
                 
             }
             if(match("RETURN")!=null)
             {
               returnE =exp();
              
                 Token B3=match("SEMICOLON");
                 
                 Token B4=match("RIGHT_CURLY_B");
                
               if(B3!=null&&B4!=null&&returnE!=null)
               {
                  
                   return new MethodDeclare(accessModifier, t1, i1, v1, v2, s, returnE);
             }
             }
             
         }
            
        
      }
      
     // IntError=true;
      return null;
  }
  public ConstructorDeclaration constructorDeclaration(String className) {

	  ArrayList<VarDeclaration>v1=new ArrayList<>();
	  ArrayList<VarDeclaration>v2=new ArrayList<>();
	  ArrayList<Statement>s=new ArrayList<>();
	  
      Id i1=id(),i2;
      //System.out.println("Const  " + i1.i  + "  " + className);
      if(i1!=null && !i1.i.equals(className))
    	  return null;
      
      Token bb=match("LEFT_ROUND_B");
      if(i1!=null&&bb!=null)
      {
        v1=checkParam();
        
        
          Token B=match("RIGHT_ROUND_B");
          Token B2=match("LEFT_CURLY_B");
         if(B!=null&&B2!=null)
         {
             while(true)
             {
                 VarDeclaration v=VarDeclaration();
                 if(v!=null)
                 {
                     v2.add(v);
                 }
                 else 
                 break;
             }
             while(true)
             {
                 Statement ss=Statement();
                 if(ss!=null)
                     s.add(ss);
                 else break; 
                 
             }
              Token B4=match("RIGHT_CURLY_B");
                
               if(B4!=null)
               {
                  
                   return new ConstructorDeclaration(i1, v1, v2, s);
             }
             }
             
         }
     // IntError=true;
      return null;
  }
  public ArrayList<VarDeclaration>checkParam()
  {
      ArrayList<VarDeclaration>al=new ArrayList<>();
      Type t=Type();
      Id id;
      if(t==null)
          return null;
    id=id();
    if(id==null)
    {
       // IntError=true;
        return null;
    }
      al.add(new VarDeclaration(t, id));
      while(true)
      {
          if(match("COMMA")==null)
          { 
             return al;
              
          }
           t=Type();
           id=id();
           if(t!=null&&id!=null)
           {
               al.add(new VarDeclaration(t, id));
              
               
           }
          else
           {
              // IntError=true;
               
               return null;
           }
          
          
      }
    
  }
  public VarDeclaration VarDeclaration()
  {
      Type b=Type();
      if(b!=null){
  Id bb=id();
          Token bbb=match("SEMICOLON");
  if(bb!=null&&bbb!=null)
      return new VarDeclaration(b, bb);
   // IntError=true;
      }
  
      
    return null;
  } 
  public Type Type()
  {
   if(TypeBoolean()!=null)
       return new TypeBoolean();
   
   else if(TypeChar()!=null)
   {
       return new TypeChar();
   }
    else if(TypeString()!=null)
   {
       return new TypeString();
   }
    else if(TypeFloat()!=null)
   {
       return new TypeFloat();
   }
     else if(TypeInt()!=null)
   {
       return new TypeInt();
   }
   
      return null;
  } public TypeBoolean TypeBoolean()
  {
      if(match("BOOLEAN")!=null)
      {
          if(match("LEFT_SQUARE_B")!=null)
          {
              if(match("RIGHT_SQUARE_B")!=null)
              {
                  return new TypeBooleanArray();
              }
            //  IntError=true;
              return null;
          }
          return new TypeBoolean();
          
          
      }
      return null;
  } public TypeInt TypeInt()
  {
      if(match("INT")!=null)
      {
          if(match("LEFT_SQUARE_B")!=null)
          {
              if(match("RIGHT_SQUARE_B")!=null)
              {
                  return new TypeIntArray();
              }
            //  IntError=true;
              return null;
          }
          return new TypeInt();
          
          
      }
      
      return null;
  } public TypeChar TypeChar()
  {
      
      if(match("CHARACTER")!=null)
      {
          if(match("LEFT_SQUARE_B")!=null)
          {
              if(match("RIGHT_SQUARE_B")!=null)
              {
                  return new TypeCharArray();
              }
             // IntError=true;
              return null;
          }
          return new TypeChar();
          
          
      }
      return null;
      
  } public TypeString TypeString()
  {
      if(match("STRING")!=null)
      {
          if(match("LEFT_SQUARE_B")!=null)
          {
              if(match("RIGHT_SQUARE_B")!=null)
              {
                  return new TypeStringArray();
              }
              return null;
          }
        //  IntError=true;
          return new TypeString();
          
          
      }
      
      return null;
  } public TypeFloat TypeFloat()
  {
      
      if(match("FLOAT")!=null)
      {
          if(match("LEFT_SQUARE_B")!=null)
          {
              if(match("RIGHT_SQUARE_B")!=null)
              {
                  return new TypeFloatArray();
              }
            //  IntError=true;
              return null;
          }
          return new TypeFloat();
          
          
      }
    return null;
  }
   public Statement Statement()
  {
      if(match("LEFT_CURLY_B")!=null)
      {
          ArrayList<Statement>al=new ArrayList<>();
      Statement s;
      
      while(true)
      {s=Statement();
          if(s!=null)
      {
          al.add(s);
          
      }
          else if(s==null/*&&!IntError*/)
          {
              if(match("RIGHT_CURLY_B")!=null)
              {
                  return new StatementBlock(al);
              }
            //  IntError=true;
              return  null;
          }
          
         
          
      }
          
      }
      else if(match("IF")!=null)
      {
          if(match("LEFT_ROUND_B")!=null)
          {
              Exp E=exp();
              Token B=match("RIGHT_ROUND_B");
          Statement s1=Statement();
          StatementIfRest ss=StatementIfRest();
          if(E!=null&&B!=null&&ss!=null)
                  {
                     
                    return new StatementIf(E, s1, ss);
                  }
            //  IntError=true;
              return null;
          }
          
          
      }
      else if(match("WHILE")!=null)
      {
          if(match("LEFT_ROUND_B")!=null)
          {
          Exp E=exp();
              Token B=match("RIGHT_ROUND_B");
          Statement S=Statement();
          if(E!=null&&B!=null&&S!=null)
              return new StatementWhile(E, S);
        // IntError=true; 
         return null;
          }
          
          
      }
      else if(match("SYSTEM.OUT.PRINTLN")!=null)
      {
          Token b=match("LEFT_ROUND_B");
          Exp E=exp();
          Token b2=match("RIGHT_ROUND_B");
              Token b3=match("SEMICOLON");
      Visitor s=new Visitor();
              
        
              if(b!=null
                      &&b2!=null
                      &&b3!=null
                      &&E!=null)
              {
                  return new StatementPrint(E);
              }
          
         // IntError=true;
          return null;
      }
      
      
      Id ii=id();
          StatementIdentifierRest rs=StatemntIdRest();
          if(rs!=null)
          {
              return new StatementIdentifier(ii, rs);
          }
     //    IntError=true;
         return null;
      
    
  } public StatementIdentifierRest StatemntIdRest()
  {
      Exp E;
      if(match("EQUAL")!=null)
      {
          E=exp();
          if(E!=null)
          {
              if(match("SEMICOLON")!=null)
              {
                  return    new StatementIdentifierRestAssign(E);
              }
           //   IntError=true;
          }
          return null;
      }
      else if(match("LEFT_SQUARE_B")!=null)
      {
          E=exp();
          if(E!=null)
          {
              Token b=match("RIGHT_SQUARE_B");
         
              Token c=match("EQUAL");
              Exp E2=exp();
              Token d=match("SEMICOLON");
                if(b!=null&&c!=null&&E2!=null&&d!=null)
                {
                  return new StatementIdentifierRestArrayIndex(E, E2);
                }
                      
             //     IntError=true;  
          
            
          
              
          }
          
       //  IntError=true;   
      }
      return null;
  } 
  public StatementIfRest StatementIfRest()
  {
      if(match("ELSE")!=null)
      {
          Statement s=Statement();
          if(s!=null)
              return new StatementIfRestMatched(s);
          //IntError=true;
          return null;
      }
      return new StatementIfRest();
  } 
}
