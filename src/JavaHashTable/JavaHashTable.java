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
public class JavaHashTable {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String filename;
        int size;
        HashTable newHW = new HashTable();
        
        System.out.println(newHW.GetHash("--"));
        System.out.println(newHW.GetHash("What"));
        
        filename = "C:\\inputn.txt";
        size = 1000;
        newHW.ReadFileandGenerateHash(filename,size);
        
       
        newHW.DisplayResult("C:\\output.txt");
        newHW.DisplayResult();
        
        newHW.DisplayResultOrdered("C:\\outputord.txt");
        
        
        System.out.println("efficiency "+newHW.TestEfficiency());
        System.out.println("max repeated word "+newHW.showMaxRepeatedWord());
        
        System.out.println("there is a word named boy: " + newHW.checkWord("boy"));
        System.out.println("frequency " + newHW.showFrequency("house"));
    
    }
    
}
