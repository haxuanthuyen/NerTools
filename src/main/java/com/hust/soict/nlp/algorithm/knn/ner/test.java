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
public class test {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        List<String> trainFiles = new ArrayList<String>();
        List<String> testFiles = new ArrayList<String>();
//        trainFiles.add("C:\\Users\\Kho\\Documents\\NetBeansProjects\\NER\\10");
        trainFiles.add("E:\\NER\\BaomoiData-smallset\\BaomoiData-smallset\\23000-24000-300-11-tagged-LPO");
        trainFiles.add("E:\\NER\\BaomoiData-smallset\\BaomoiData-smallset\\23000-24000-300-12-tagged-LPO");
        trainFiles.add("E:\\NER\\BaomoiData-smallset\\BaomoiData-smallset\\23000-24000-300-9-tagged-LPO");
        trainFiles.add("E:\\NER\\BaomoiData-smallset\\BaomoiData-smallset\\23000-24000-300-10-tagged-LPO");




        testFiles.add("E:\\NER\\BaomoiData-smallset\\BaomoiData-smallset\\23000-24000-300-13-tagged-LPO");


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

//        for(String fs:features){
//            System.out.println(fs);
//        
//        }


        List<String> f2 = new ArrayList<>();
//        String r ="false:true:false:true:true:true:false:false:true:false:false:true:false:false:true:true:false:false:true:true:false:true:false:false:false:false:true:true:false:true:false:false:true:false:true:true:false:true:false:true:false:true:true:false:true:false:true:true:false:false:true:true:true:false:true:false:false:false:true:true:true:true:false:false:true:false:true:false:true:true:false:false:false:true:false:false:true:false:true:true:true:true:false:true:false:false:true:false:false";
//        String[] gens = r.split(":");
//        for (int i = 0; i < gens.length; i++) {
//            String g = gens[i];
//            
//            if (g.equals("true")) {
//                 System.out.println(features.get(i));
//               
//                f2.add(features.get(i));
//            }
//        }

        f2.add("inLocDictN2Word");
        f2.add("inLocDictP2Word");
        f2.add("vnTimeMarkersN2Word");
        f2.add("allCapNext2Word");
        f2.add("lowercaseNextWord");
        f2.add("isInCountryListN2Word");
        f2.add("orgIndicateNounN2Word");
        f2.add("postfixPWord");
        f2.add("allCapNextWord");
        f2.add("vnTimeMarkersP2Word");
        f2.add("locIndicateNounN2Word");
        f2.add("isInVnFirstNamePWord");
        f2.add("isBracketPWord");
        f2.add("prefixP2Word");
        f2.add("isInCountryListNWord");
        f2.add("vnTimeMarkersPWord");
        f2.add("isInVnFirstNameN2Word");



        KNNNER knn = new KNNNER();

        knn.run(trainFiles, testFiles, f2);

    }

    public void runTest(List<String> trainFiles, List<String> testFiles, boolean[] featureNST) throws FileNotFoundException, IOException
    {
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

//        for(String fs:features){
//            System.out.println(fs);
//        
//        }


        List<String> f2 = new ArrayList<>();
//        String r = featureNST;
//        System.out.println(featureNST);
//        String[] gens = r.split(":");
        for (int i = 0; i < featureNST.length; i++) {
            boolean g = featureNST[i];
            if (g==true) {
                System.out.println(features.get(i));
                f2.add(features.get(i));
            }
        }
        KNNNER knn = new KNNNER();
        knn.run(trainFiles, testFiles, f2);
    }
}
