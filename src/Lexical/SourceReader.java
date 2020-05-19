/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lexical;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class SourceReader {
    public String readFile(String path) throws FileNotFoundException, IOException
    {
         BufferedReader bf=new BufferedReader(new FileReader(path));
      
        StringBuilder sb=new StringBuilder();
        String line=bf.readLine();
        while(line!=null)
        {
            sb.append(line);
            sb.append("\n");
         
            line=bf.readLine();
            
    
        }
        String content=sb.toString();
       
        bf.close();
       
        return content;
        
        
        
    }
    public void writeToFile(ArrayList<Token>s) throws IOException
    {
        
          BufferedWriter bw=new BufferedWriter(new FileWriter("f2.txt"));
          for(Token t:s)
          {
            bw.write(t.value+"@"+t.name);
            bw.newLine();
            
              
              
          }
          bw.close();
    }
    
}
