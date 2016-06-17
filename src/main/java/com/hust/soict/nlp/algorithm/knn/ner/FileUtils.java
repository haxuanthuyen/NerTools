package com.hust.soict.nlp.algorithm.knn.ner;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;

/**
 *
 * @author Luan
 */
public class FileUtils {
    
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
      BufferedReader br=new BufferedReader(new FileReader(new File("E:\\NER\\vnLastNames.txt")));
      String line="";
      String result="";
      while((line=br.readLine())!=null)
      {
          String[] part=line.split("\\s+");
          if(part.length>1)result+=part[1].trim()+"\n";
      }
      br.close();
      FileWriter fw=new FileWriter(new File("E:\\NER\\vnLastNames2.txt"));
      fw.write(result);
      fw.close();
              
    }
    
}



