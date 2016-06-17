/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.nlp.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kho
 */
public class ConvertVietnameseFileToColumnFormat {
    
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        String dirName="E:\\TTCN\\NguyenCongHoan_DATN_All\\NguyenCongHoan_DATN_All\\source_code\\Data\\Label\\";
        File dir=new File(dirName);
        String[] fileName=dir.list();
        for(String file:fileName)
        {
            convertFile(dir.getPath()+"\\"+file);
        }
    }

    private static void convertFile(String filename) throws FileNotFoundException, IOException {
        
        String result="";
	BufferedReader in = new BufferedReader(new FileReader(filename));
        
        String currentLabel="O";
	for (String line = in.readLine(); line != null; line = in.readLine()) {
	    if (line.trim().length() == 0) {
		continue;
	    }
	    String[] bits = line.split("(\\]\\s+)| (\\[\\s+) | (\\)\\s+) |(\\>\\s+)");
            
	    for(int i=0;i<bits.length;i++)
            {
            String word=bits[i].replace("<", "").replace("[", "").replace("(", "").replace(",", "").replace(")", "").trim();
                switch (word) {
                    case "per":
                        currentLabel="PERSON";
                        break;
                    case "/per":
                        currentLabel="O";
                        break;
                    case "loc":
                        currentLabel="LOCATION";;
                        break;
                    case "/loc":
                        currentLabel="O";;
                        break;
                    case "org":
                        currentLabel="ORG";;
                        break;
                    case "/org":
                        currentLabel="O";
                        break;
                    default:
                
                    result+="["+word+"]"+" "+"["+currentLabel+"]"+"\n";
                  
                    break;
                        
                }
            
            }
	}
        writeToFile(result,filename.substring(0,filename.length()-3)  +"_new.txt");
      //
    }

    private static void writeToFile(String result,String filename) throws FileNotFoundException, IOException {
       // throw new UnsupportedOperationException("Not yet implemented");
        FileWriter outFile=new FileWriter(filename);
        PrintWriter out = new PrintWriter(outFile);
        out.print(result);
        out.close();
        
    }
    
}
