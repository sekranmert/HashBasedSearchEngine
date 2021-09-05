/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaHashTable;

/**
 *
 * @author Sekran Mert Kılıç 52522588108
 */
public class MyHash{
    Integer occurance;
    String word; 

    
    public void setWord(String newWord){ //saves word and occurance
        this.word = newWord;
        this.occurance = 1;
    }
    
    public String getWord(){ // gives  back word
        return this.word;
    }
    
    public int getOcc(){  // gives back occurance
        return this.occurance;
    }
    
    public void incOcc(){ // increase occurance by 1
        this.occurance++;
    }
    
}
