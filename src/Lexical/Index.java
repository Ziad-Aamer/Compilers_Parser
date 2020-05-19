/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lexical;

public class Index  implements Comparable<Index>{
    public int start;
    public int end;
    public int pattern;

    public Index(int start, int end ,int pattern) {
        this.start = start;
        this.end = end;
        
        this.pattern = pattern;
    }

    @Override
    public int compareTo(Index t) {
        
   return start-t.start;
    }
    
    
}
