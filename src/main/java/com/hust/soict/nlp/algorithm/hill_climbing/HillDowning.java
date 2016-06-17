package com.hust.soict.nlp.algorithm.hill_climbing;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.hust.soict.nlp.algorithm.knn.ner.DataReaderAndWriter;
import com.hust.soict.nlp.algorithm.knn.ner.Datum;
import com.hust.soict.nlp.algorithm.knn.ner.FeatureFactory;
import com.hust.soict.nlp.algorithm.knn.ner.KNNNER;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Luan
 */
public class HillDowning {

    public static List<String> features=new ArrayList<String>();

    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        List<String> trainFiles = new ArrayList<String>();
        List<String> testFiles = new ArrayList<String>();
        trainFiles.add("E:\\NER\\BaomoiData-smallset\\BaomoiData-smallset\\23000-24000-300-12-tagged-LPO");
        trainFiles.add("E:\\NER\\BaomoiData-smallset\\BaomoiData-smallset\\23000-24000-300-8-tagged-LPO");


        testFiles.add("E:\\NER\\BaomoiData-smallset\\BaomoiData-smallset\\23000-24000-300-7-tagged-LPO");


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


//      
        List<Datum> train = DataReaderAndWriter.readData(trainFiles);
        List<Datum> test = DataReaderAndWriter.readData(testFiles);

        List<String> featureResult=new ArrayList<String>();
        featureResult.addAll(features);
        int count=0;
        double lastFitness=-100.0;
        Random r=new Random();
        while(count<89)
        {
            int index=r.nextInt(features.size());
            String f=features.get(index);
            System.out.println("Removing feature:"+f);
            featureResult.remove(f);
            features.remove(index);
            double currentFitness = 0;
            int w=0;
            while(w<4)
            {
                int k=r.nextInt(train.size()-4000);
                List<Datum> trainWord=train.subList(k, k+4000);
                int p=r.nextInt(test.size()-1000);
                List<Datum> testWord=test.subList(p, p+1000);
                currentFitness+=getFitness(trainWord, testWord, featureResult);
                w++;
            }
            currentFitness=currentFitness/4;
            if(currentFitness>lastFitness-0.005)
            {
                lastFitness=currentFitness;
            }
            else featureResult.add(f);


            count++;
        }
        System.out.println("Result:");
        for(String f:featureResult) System.out.println(f);
    }
    public static double getFitness(List<Datum> train, List<Datum> test, List<String> features) throws FileNotFoundException, IOException
    {
        train= FeatureFactory.computeFeature(train, features);
        test=FeatureFactory.computeFeature(test, features);
        KNNNER knn=new KNNNER();
        return knn.run2(train, test, features);
    }

}
