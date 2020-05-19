/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in tahe editor.
 */
package Lexical;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Regular_Expression {

	
    public  ArrayList<Token> mainF() throws IOException {
     
       ArrayList<Index> c=new ArrayList<>();
       SourceReader s=new SourceReader();
       ArrayList<Token>result=new ArrayList<>();
       
       String inn[]={
       "(\")[^\"]*(\")","(/\\*)(.|[\r\n])*?(\\*/)","/\\*","\\*/","\\+","\\{","\\}","\\[","\\]","\\(","\\)",",",
           ";","\\.","\\!","=","&&","\\-","\\*","<",">","\\bif\\b","\\bint\\b","\\belse\\b",
           "\\bmain\\b","\\bthis\\b","\\btrue\\b","\\bvoid\\b","\\bclass\\b","\\bfalse\\b","\\bwhile\\b",
           "\\blength\\b","\\bpublic\\b",
       "\\breturn\\b","\\bstatic\\b","\\bnew\\b","\\bString\\b","\\bfloat\\b","\\bchar\\b",
       "\\bboolean\\b","\\bextends\\b","\\bSystem\\.out\\.println\\b","\\b\\d*\\.\\d+\\b","\\b\\d+\\b",
       "\\b([a-zA-Z_$])([a-zA-Z_$0-9]*)\\b","'.'","//(.*)","'","\"","\n"
       
       };
       String []inn2={"STRING_LITERAL","COMMENT2","COMMENT_L","COMMENT_R","PLUS","LEFT_CURLY_B","RIGHT_CURLY_B",
           "LEFT_SQUARE_B","RIGHT_SQUARE_B","LEFT_ROUND_B","RIGHT_ROUND_B","COMMA","SEMICOLON",
           "DOT","NOT","EQUAL","AND","MINUS","MULTIPLY","LESSTHAN","GREATERTHAN","IF","INT","ELSE",
           "MAIN","THIS","TRUE","VOID","CLASS","FALSE","WHILE","LENGTH","PUBLIC",
       "RETURN","STATIC","NEW","STRING","FLOAT","CHARACTER","BOOLEAN","EXTENDS","SYSTEM.OUT.PRINTLN","FLOAT_LETRAL"
               ,"INTEGER_LITERAL"
               ,"ID","A_CHAR","COMMENT1","SQUOTE","DQUOTE" ,"EOL"};
        Pattern p;
        Matcher m;
        
        String input=s.readFile("ff.txt");
        //System.out.println(input);
      int currentlength;
        while(input.length()>0)
        { c.clear();  
            currentlength=input.length();
            for(int j=0;j<inn.length;j++)
        {
          
            p=Pattern.compile(inn[j]);
            m=p.matcher(input);
        
            if(m.find())
            {
               c.add(new Index(m.start(), m.end(), j));
            }
          
       } 
               if(c.isEmpty())
            {
                System.out.println("Error");
                break;
            }
           int u=0;
            int small=c.get(0).start;
            for (int ii = 1; ii < c.size(); ii++) {
                if(c.get(ii).start<small)
                 {    u=ii;
                    small=c.get(ii).start;
                 }
            }
            result.add(new Token(input.substring(c.get(u).start,c.get(u).end), inn2[c.get(u).pattern]));
          //s.writeToFile(result);
           if(c.get(u).end>=currentlength)
                break;
            input=input.substring(c.get(u).end);
            c.clear();
        }
     return result;
     }
     
     
    }
       
    
