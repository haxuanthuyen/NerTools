package com.hust.soict.nlp.algorithm.knn.ner;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luan
 */
public class test2 {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        List<String> trainFiles = new ArrayList<String>();
        List<String> testFiles = new ArrayList<String>();
//        trainFiles.add("E:\\NER\\BaomoiData-smallset\\BaomoiData-smallset\\23000-24000-300-11-tagged-LPO");
        trainFiles.add("E:\\NER\\BaomoiData-smallset\\BaomoiData-smallset\\23000-24000-300-12-tagged-LPO");
//        trainFiles.add("E:\\NER\\BaomoiData-smallset\\BaomoiData-smallset\\23000-24000-300-9-tagged-LPO");
//        trainFiles.add("E:\\NER\\BaomoiData-smallset\\BaomoiData-smallset\\23000-24000-300-10-tagged-LPO");
//        trainFiles.add("E:\\NER\\BaomoiData-smallset\\BaomoiData-smallset\\23000-24000-300-1-tagged-LPO");
//        trainFiles.add("E:\\NER\\BaomoiData-smallset\\BaomoiData-smallset\\23000-24000-300-2-tagged-LPO");
//        trainFiles.add("E:\\NER\\BaomoiData-smallset\\BaomoiData-smallset\\23000-24000-300-3-tagged-LPO");
//        trainFiles.add("E:\\NER\\BaomoiData-smallset\\BaomoiData-smallset\\23000-24000-300-4-tagged-LPO");
//        trainFiles.add("E:\\NER\\BaomoiData-smallset\\BaomoiData-smallset\\23000-24000-300-5-tagged-LPO");
//        trainFiles.add("E:\\NER\\BaomoiData-smallset\\BaomoiData-smallset\\23000-24000-300-6-tagged-LPO");
//        trainFiles.add("E:\\NER\\BaomoiData-smallset\\BaomoiData-smallset\\23000-24000-300-8-tagged-LPO");




        testFiles.add("E:\\NER\\BaomoiData-smallset\\BaomoiData-smallset\\23000-24000-300-7-tagged-LPO");
        List<Datum> train=DataReaderAndWriter.readData(trainFiles);
        List<Datum> test=DataReaderAndWriter.readData(testFiles);
        int numSenTrain=0, numSenTest=0;
        for(Datum d:train)
        {
            if(d.word.equals("."))  numSenTrain++;
        }
        for(Datum d: test)
        {
            if(d.word.equals(".")) numSenTest++;
        }
        System.out.println("Number of sentences in train: "+numSenTrain);
        System.out.println("Number of sentences in test: "+numSenTest);
    }
}
