/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import Lexical.Regular_Expression;
import Lexical.Token;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JTree;

public class ParserMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        /*  ParserClass p=new ParserClass();
        p.parse();*/
        Regular_Expression r=new Regular_Expression();
        ArrayList<Token> mainF = r.mainF();
        
        ParserClass p=new ParserClass(mainF);
     
        p.parse();
     
              
    }
    
}
