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
public interface Interface {
    Integer GetHash(String mystring);
    void ReadFileandGenerateHash(String filename, int size);
    void DisplayResult(String Outputfile);
    void DisplayResult();
    void DisplayResultOrdered(String Outputfile);
    int showFrequency(String myword);
    String showMaxRepeatedWord();
    boolean checkWord(String myword);
    int TestEfficiency();
}
