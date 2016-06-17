package com.hust.soict.nlp.algorithm.ga;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.hust.soict.nlp.algorithm.knn.ner.DataReaderAndWriter;
import com.hust.soict.nlp.algorithm.knn.ner.Datum;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Luan
 */
public class NERGA {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        List<String> trainFiles = new ArrayList<String>();
        List<String> testFiles = new ArrayList<String>();
//        trainFiles.add("E:\\NER\\BaomoiData-smallset\\BaomoiData-smallset\\23000-24000-300-2-tagged-7label-LPO");
//        trainFiles.add("E:\\NER\\BaomoiData-smallset\\BaomoiData-smallset\\23000-24000-300-3-tagged-7label-LPO");
//        trainFiles.add("E:\\NER\\BaomoiData-smallset\\BaomoiData-smallset\\23000-24000-300-1-tagged-7label-LPO");
//        trainFiles.add("E:\\NER\\BaomoiData-smallset\\BaomoiData-smallset\\23000-24000-300-4-tagged-7label-LPO");
//        trainFiles.add("E:\\NER\\BaomoiData-smallset\\BaomoiData-smallset\\23000-24000-300-5-tagged-7label-LPO");
//        trainFiles.add("E:\\NER\\BaomoiData-smallset\\BaomoiData-smallset\\23000-24000-300-6-tagged-7label-LPO");
//        trainFiles.add("E:\\NER\\BaomoiData-smallset\\BaomoiData-smallset\\23000-24000-300-7-tagged-7label-LPO");
//        trainFiles.add("E:\\NER\\BaomoiData-smallset\\BaomoiData-smallset\\23000-24000-300-8-tagged-7label-LPO");
//        trainFiles.add("E:\\NER\\BaomoiData-smallset\\BaomoiData-smallset\\23000-24000-300-12-tagged-7label-LPO");
//        trainFiles.add("E:\\NER\\BaomoiData-smallset\\BaomoiData-smallset\\23000-24000-300-10-tagged-7label-LPO");
//        trainFiles.add("E:\\NER\\BaomoiData-smallset\\BaomoiData-smallset\\23000-24000-300-11-tagged-7label-LPO");
        trainFiles.add("E:\\NER\\BaomoiData-smallset\\BaomoiData-smallset\\23000-24000-300-12-tagged-7label-LPO");
        trainFiles.add("E:\\NER\\BaomoiData-smallset\\BaomoiData-smallset\\23000-24000-300-9-tagged-7label-LPO");
        trainFiles.add("E:\\NER\\BaomoiData-smallset\\BaomoiData-smallset\\23000-24000-300-10-tagged-7label-LPO");
        trainFiles.add("E:\\NER\\BaomoiData-smallset\\BaomoiData-smallset\\23000-24000-300-11-tagged-7label-LPO");
        trainFiles.add("E:\\NER\\BaomoiData-smallset\\BaomoiData-smallset\\23000-24000-300-6-tagged-7label-LPO");
        trainFiles.add("E:\\NER\\BaomoiData-smallset\\BaomoiData-smallset\\23000-24000-300-5-tagged-7label-LPO");
//        trainFiles.add("E:\\NER\\BaomoiData-smallset\\BaomoiData-smallset\\23000-24000-300-4-tagged-7label-LPO");


        testFiles.add("E:\\NER\\BaomoiData-smallset\\BaomoiData-smallset\\23000-24000-300-7-tagged-7label-LPO");
        testFiles.add("E:\\NER\\BaomoiData-smallset\\BaomoiData-smallset\\23000-24000-300-11-tagged-7label-LPO");
        testFiles.add("E:\\NER\\BaomoiData-smallset\\BaomoiData-smallset\\23000-24000-300-8-tagged-7label-LPO");


        List<String> features = new ArrayList<String>();
        // Feature f=new Feature("currentWord");
        features.add("currentWord");
        features.add("prevWord");
        features.add("prev2Word");
        features.add("next2Word");
        features.add("nextWord");
        features.add("initUpcaseCurrentWord");
        features.add("initUpcasePrevWord");
        features.add("initUpcasePrev2Word");
        features.add("initUpcaseNextWord");
        features.add("initUpcaseNext2Word");
        features.add("lowercaseCurrentWord");
        features.add("lowercasePrevWord");
        features.add("lowercaseNextWord");
        features.add("wordPairPC");
        features.add("wordPairCN");
        features.add("wordPairPN");
        features.add("allCapCurrentWord");
        features.add("allCapPrevWord");
        features.add("allCapPrev2Word");
        features.add("allCapNextWord");
        features.add("allCapNext2Word");
        features.add("letterAndDigitCWord");
        features.add("letterAndDigitPWord");
        features.add("letterAndDigitP2Word");
        features.add("letterAndDigitNWord");
        features.add("letterAndDigitN2Word");
        features.add("prefixCWord");
        features.add("prefixPWord");
        features.add("prefixP2Word");
        features.add("isPunctuationPWord");
        features.add("isPunctuationP2Word");
        features.add("isPunctuationNWord");
        features.add("isPunctuationN2Word");



        features.add("isBracket");
        features.add("isBracketPWord");
        features.add("isBracketP2Word");
        features.add("isBracketN2Word");
        features.add("isBracketNWord");
        features.add("isFirstSentenceWord");
        features.add("isFirstSentencePWord");
        features.add("isFirstSentenceP2Word");
        features.add("isInCountryList");
        features.add("isInCountryListPWord");
        features.add("isInCountryListP2Word");
        features.add("isInCountryListNWord");
        features.add("isInCountryListN2Word");
        features.add("isInVnFirstName");
        features.add("isInVnFirstNamePWord");
        features.add("isInVnFirstNameP2Word");
        features.add("isInVnFirstNameN2Word");
        features.add("isInVnFirstNameNWord");
        features.add("isInVnLastName");
        features.add("isInVnLastNamePWord");
        features.add("isInVnLastNameP2Word");
        features.add("isInVnLastNameNWord");
        features.add("isInVnLastNameN2Word");
        features.add("inLocDict");
        features.add("inLocDictPWord");
        features.add("inLocDictP2Word");
        features.add("inLocDictNWord");
        features.add("inLocDictN2Word");
        features.add("vnTimeMarkers");
        features.add("vnTimeMarkersPWord");
        features.add("vnTimeMarkersP2Word");
        features.add("vnTimeMarkersN2Word");
        features.add("vnTimeMarkersNWord");
        features.add("perIndicateNoun");
        features.add("perIndicateNounPWord");
        features.add("perIndicateNounP2Word");
        features.add("perIndicateNounNWord");
        features.add("perIndicateNounN2Word");
        features.add("locIndicateNounN2Word");
        features.add("locIndicateNounNWord");
        features.add("locIndicateNounPWord");
        features.add("locIndicateNounP2Word");
        features.add("locIndicateNoun");
        features.add("orgIndicateNoun");
        features.add("orgIndicateNounPWord");
        features.add("orgIndicateNounP2Word");
        features.add("orgIndicateNounNWord");
        features.add("orgIndicateNounN2Word");




        features.add("tag");
        features.add("pTag");
        features.add("p2Tag");
        features.add("nTag");
        features.add("n2Tag");

        features.add("postfixCWord");
        features.add("postfixPWord");
        features.add("postfixNWord");


//        List<Datum> words=DataReaderAndWriter.readData(trainFiles);
//        int size=words.size();
//        Random r=new Random();
//        int off=r.nextInt(size-5000);
        List<Datum> train = DataReaderAndWriter.readData(trainFiles);
        List<Datum> test = DataReaderAndWriter.readData(testFiles);
        Population p = new Population(features, 64);


        p.evolution2(train, test, features);

//        for(int i=0;i<20;i++){
////             off=r.nextInt(size-5000);
////         train=words.subList(off, off+4000);
////         test=words.subList(off+4000, off+5000);
//            p.evolution(train, test, features);
//        }
//        double t = 0.0, last_fitness = -100.0;
//        int count = 0;
//        int iter = 0;
//
//        System.out.println("Feature size :" + features.size());
//        while (iter<10) {
//
//            iter++;
//            System.out.println("Iter :" + iter);
////            if(iter>200) break;
//
//            double fitness = p.evolution(train, test, features);
//            Runtime.getRuntime().freeMemory();
//            System.gc();
//            
//            System.out.println("Fitness:" + fitness);
//            System.out.println("Last Fitness:" + last_fitness);
//            double incres = 0;
//            incres += (fitness - last_fitness);
//            last_fitness = fitness;
//            if (count == 10) {
//                if (incres < 0.000001) {
//                    break;
//                } else {
//                    incres = 0.0;
//                }
//            }
//
//            count++;
//            if (count > 20) {
//                count = 1;
//            }
////            count++;
//        }

        System.out.println("Run test the best feature set");
        /*
        for (NST nst : p.nsts) {

            List<String> test1 = new ArrayList<String>();
            test1.add("C:\\Users\\Kho\\Documents\\NetBeansProjects\\NER\\6_test");
//            List<String> test2 = new ArrayList<String>();
//            test2.add("E:\\NER\\BaomoiData-smallset\\BaomoiData-smallset\\23000-24000-300-11-tagged-LPO");


            String r="";
            for(boolean b:nst.gens) r+=b+":";
            System.out.println("Gens:"+r);
            test T = new test();
            List<String> tt=new ArrayList<>();
            tt.add("C:\\Users\\Kho\\Documents\\NetBeansProjects\\NER\\10");
            T.runTest(trainFiles, testFiles, nst.gens);
//            T.runTest(trainFiles, test2, nst.gens);
        }
        */
    }
}
