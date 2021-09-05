/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaHashTable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sekran Mert Kılıç 52522588108
 */
public class HashTable implements Interface{
    public MyHash[] newHash;
    int hashSize; //Hash tables size
    int collisions = 0;
    
    @Override
    public Integer GetHash(String mystring) {
        /* generate an integer value (hash
        index) related to the input word. If 
        collusion occurs the collusion has
        to be solved by double hash method.*/
        Integer index = 0;
        mystring = mystring.toLowerCase();
        for(int i= 0;i< mystring.length();i++){
            index += mystring.charAt(i)*7^i; // used 7, a prime number and its power to decrease collisions
        }            
        return index;

    }

    @Override
    public void ReadFileandGenerateHash(String filename, int size) {
        /* Create the open address hash structure 
        with the size given by the user. The file 
        which contains a very long text will be 
        parsed and during the parsing hash table 
        must be modified by the words.*/
        newHash = new MyHash[size];
        String dummyString;
        this.hashSize = size;
        
        File file = new File(filename); 
        try (Scanner NewFile = new Scanner(file)) {                                                    
            while(NewFile.hasNext()){
                
                dummyString = NewFile.next();
                dummyString = dummyString.replaceAll("[^A-Za-z0-9-']","");
                dummyString = dummyString.toLowerCase();
                
                int hashIndex = (GetHash(dummyString))% size;
                if (hashIndex < 0){
                    hashIndex += size;
                }
                
                MyHash myNew = new MyHash();
                if(newHash[hashIndex]== null){
                   newHash[hashIndex] = myNew; 
                   newHash[hashIndex].setWord(dummyString);
            
                }
                else if (newHash[hashIndex].getWord().equals(dummyString)){
                   newHash[hashIndex].incOcc();
                }
                else{
                    int newIndex;
                    int i = 1;
                    newIndex = (hashIndex + GetHash(dummyString)*i)% hashSize;
                    if (newIndex < 0){
                        newIndex += size;
                    }
                    collisions ++ ;
                    while(newHash[newIndex]!= null){
                        if (newHash[newIndex].getWord().equals(dummyString)){
                            newHash[newIndex].incOcc();
                            break;
                        }
                        i++;
                        newIndex = (newIndex + GetHash(dummyString)*i)%hashSize;
                        if (newIndex < 0){
                            newIndex += size;
                        }
                        collisions ++ ;
                    }
                    if(newHash[newIndex]== null){
                        newHash[newIndex] = myNew; 
                        newHash[newIndex].setWord(dummyString);
                    }
                }
                    
                
            }
        System.out.println("The file has been opened and the words has been mapped");
        }
        catch (FileNotFoundException ex) {
            Logger.getLogger(HashTable.class.getName()).log(Level.SEVERE, null, ex);
        }    
    } 
    
    @Override
    public void DisplayResult(String Outputfile) {
        /* All the words in the text and their frequency 
        has to be displayed in a text file.*/
        File outFile = new File(Outputfile);//source #4 
        PrintWriter out; 
        try {
            out = new PrintWriter(outFile);
            for(int i = 0;i<hashSize;i++){
                if(newHash[i]!=null){
                    out.println(newHash[i].getWord()+" has occurred "+ newHash[i].getOcc()+" times in this document");               
                }
            }
            out.close();
            System.out.println("The file has been opened and the results has been written");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HashTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void DisplayResult() {
        /* All the words in the text and their frequency
        has to be displayed on the screen.*/
        System.out.println("Results:");
        for(int i = 0;i<hashSize;i++){
            if(newHash[i]!=null){
                System.out.println(newHash[i].getWord()+" has occurred "+ newHash[i].getOcc()+" times in this document");               
            }
        } 
        System.out.println("Results end...");
    }

    @Override
    public void DisplayResultOrdered(String Outputfile) {
        /* All the words and in the text and their
        frequency has to be displayed in a text file
        in an ordered fashion. The most repeated words
        will be listed at the beginning and the least
        repeated words at the end */
        MyHash dummyHash[];
        MyHash dHash;
        dummyHash = newHash.clone();
        
        for (int i = 0; i<hashSize; i++){
            if (dummyHash[i]!= null){
                for(int a = i+1; a<hashSize; a++){
                    if (dummyHash[a]!= null){
                        if(dummyHash[a].getOcc() >= dummyHash[i].getOcc()){
                            dHash = dummyHash[a];
                            dummyHash[a] = dummyHash[i];
                            dummyHash[i] = dHash;
                        }    
                    } 
                }
            }    
        }
        File outFile = new File(Outputfile);//source #4 
        PrintWriter writeFile; 
        try {
            writeFile = new PrintWriter(outFile);
            for(int i = 0 ;i < hashSize ;i++){
                if(dummyHash[i]!=null){
                    writeFile.println(dummyHash[i].getWord()+" has occurred "+ dummyHash[i].getOcc()+" times in this document");               
                }
            }
            writeFile.close();
            System.out.println("The file has been opened and the results has been written");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HashTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int showFrequency(String myword) {
        /*The frequency of myword in the text
        file will be given. If there is no myword
        in the text -1 must be returned.*/
        int hashIndex = GetHash(myword)% hashSize;
        if (hashIndex < 0){
            hashIndex += hashSize;
            }
        if(newHash[hashIndex] == null){
            return -1;
        }
        else if (newHash[hashIndex].getWord().equalsIgnoreCase(myword)){
            return newHash[hashIndex].getOcc();
        }
        else{
            int newIndex;
            int i = 1;
            newIndex = (hashIndex  + GetHash(myword)*i)%hashSize;
            if (newIndex < 0){
                newIndex += hashSize;
            }
            while(newHash[newIndex]!=null){
                if (newHash[hashIndex].getWord().equalsIgnoreCase(myword)){
                    return newHash[hashIndex].getOcc();
                }
                i++;
                newIndex = (newIndex + GetHash(myword)*i) % hashSize;
                if (newIndex < 0){
                    newIndex += hashSize;
                }
            }
            return -1;
             
        }
        
    }

    @Override
    public String showMaxRepeatedWord() {
        /*The most repeated word has to be
        returnd.*/
        int dummyIndex;
        int dummyOcc;
        dummyOcc = 0;
        dummyIndex = 0;
        for (int i = 0; i < hashSize;i++){
            if (newHash[i]!= null){
                if(newHash[i].getOcc() > dummyOcc){
                    dummyOcc = newHash[i].getOcc();
                    dummyIndex = i;
                } 
            }    
        }
        return newHash[dummyIndex].getWord();
    }

    @Override
    public boolean checkWord(String myword) {
        /* Checks whether myword is found in
        the text.*/
        int hashIndex = GetHash(myword)% hashSize;
        if (hashIndex < 0){
            hashIndex += hashSize;
        }
        if(newHash[hashIndex]== null){
            return false;
        }
        else if (newHash[hashIndex].getWord().equalsIgnoreCase(myword)){
            return true;
        }
        else{
            int newIndex;
            int i = 1;
            newIndex = (hashIndex + GetHash(myword)*i)%hashSize;
            if (newIndex < 0){
                newIndex += hashSize;
            }
            while(!newHash[newIndex].getWord().equalsIgnoreCase(myword)){
                i++;
                newIndex = (newIndex + GetHash(myword)*i)%hashSize;
                if (newIndex < 0){
                newIndex += hashSize;
                }
            }
            return true;  
        }
    }

    @Override
    public int TestEfficiency() {
        /* Returns the number of collusions during
        parsing the file.*/
        
        return this.collisions; // number of collisions
    }
    
}
