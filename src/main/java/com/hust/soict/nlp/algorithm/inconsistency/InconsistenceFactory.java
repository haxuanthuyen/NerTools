package com.hust.soict.nlp.algorithm.inconsistency;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.hust.soict.nlp.algorithm.knn.ner.Datum;
import com.hust.soict.nlp.algorithm.knn.ner.Feature;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Luan
 */
public class InconsistenceFactory {


    public static double computeInconsistencyRate(List<Datum> computedTrain)
    {
        double result=0.0;
        HashMap<String, int[]> hm=new HashMap<>();
        for(Datum d:computedTrain)
        {
            String k="";
            for(Feature f:d.features)
            {
                k+="~"+f.intValue;
                if(f.stringValue!=null) k+="~"+f.stringValue;

            }
            int[] value=hm.get(k);
            if(value==null)
            {
                value=new int[7];
                value[0]=0;
                value[1]=0;
                value[2]=0;
                value[3]=0;
                value[4]=0;
                value[5]=0;
                value[6]=0;

            }
            switch (d.label) {
                case "B-PER":
                    value[0]++;
                    break;
                case "B-LOC":
                    value[1]++;
                    break;
                case "B-ORG":
                    value[2]++;
                    break;
                case "I-ORG":
                    value[3]++;
                    break;
                case "I-PER":
                    value[4]++;
                    break;
                case "I-LOC":
                    value[5]++;
                    break;
                case "O":
                    value[6]++;
                    break;
            }
            hm.put(k, value);


        }

        for(int[] value:hm.values())
        {
            int max=value[0]>value[1]?value[0]:value[1];
            max=max>value[2] ? max:value[2];
            max=max>value[3]? max :value[3];
            max=max>value[4]? max :value[4];
            max=max>value[5]? max :value[5];
            max=max>value[6]? max :value[6];

            result+=(value[0]+value[1]+value[2]+value[3]+value[4]+value[5]+value[6]-max);
//             System.out.println(result);
        }
        return -result/computedTrain.size();
    }

}
