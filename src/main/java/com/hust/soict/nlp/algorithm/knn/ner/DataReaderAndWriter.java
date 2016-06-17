package com.hust.soict.nlp.algorithm.knn.ner;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luan
 */
public class DataReaderAndWriter {

    public static List<Datum> readData(String fileName) throws FileNotFoundException, IOException
    {
        List<Datum> result=new ArrayList<Datum>();

        File f=new File(fileName);
        BufferedReader br=new BufferedReader(new FileReader(f));
        String line="";
        while((line=br.readLine())!=null)
        {
            String[] part=line.trim().split("\\]\\s+\\[");
//            for(int i=0;i<part.length;i++)
//            {
//               if(part[i].charAt(0)=='['||part[i].charAt(0)==']')
//                   part[i]=part[i].substring(1, part[i].length());
//               if(part[i].charAt(part[i].length()-1)==']'||part[i].charAt(part[i].length()-1)=='[')
//                   part[i]=part[i].substring(0, part[i].length()-1);
//            }
            if(part.length==3)
            {
                Datum datum=new Datum(part[0],part[1],part[2]);
                result.add(datum);
            }
            else if(part.length==2)
            {
                Datum datum=new Datum(part[0],part[1]);
                result.add(datum);
            }

        }

        return result;
    }
    public static List<Datum> readData(List<String> fileNames) throws FileNotFoundException, IOException
    {
        List<Datum> result=new ArrayList<Datum>();
        for(String fileName:fileNames)
        {
            File f=new File(fileName);
            BufferedReader br=new BufferedReader(new FileReader(f));
            String line="";
            while((line=br.readLine())!=null)
            {
                String[] part=line.trim().split("\\s+");
//            for(int i=0;i<part.length;i++)
//            {
//               if(part[i].charAt(0)=='['||part[i].charAt(0)==']')
//                   part[i]=part[i].substring(1, part[i].length());
//               if(part[i].charAt(part[i].length()-1)==']'||part[i].charAt(part[i].length()-1)=='[')
//                   part[i]=part[i].substring(0, part[i].length()-1);
//            }
                if(part.length==3)
                {
                    Datum datum=new Datum(part[0],part[1],part[2]);
                    result.add(datum);
                }
                else if(part.length==2)
                {
                    Datum datum=new Datum(part[0],part[1]);
                    result.add(datum);
                }

            }
            br.close();
        }

        return result;

    }
}
