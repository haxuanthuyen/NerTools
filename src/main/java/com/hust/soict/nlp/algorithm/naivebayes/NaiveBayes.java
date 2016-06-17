package com.hust.soict.nlp.algorithm.naivebayes;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.hust.soict.nlp.algorithm.knn.ner.Datum;
import com.hust.soict.nlp.algorithm.knn.ner.Feature;
import com.hust.soict.nlp.algorithm.knn.ner.FeatureFactory;
import com.hust.soict.nlp.algorithm.knn.ner.KNNNER;

import java.util.List;

/**
 *
 * @author Luan
 */
public class NaiveBayes {

    public double run(List<Datum> train, List<Datum> test, List<String> features) {
        double countPer = 0, countLoc = 0, countOrg = 0, countO=0;
        List<Datum> computedTrain = FeatureFactory.computeFeature(train, features);
        List<Datum> computedTest = FeatureFactory.computeFeature(test, features);
        for (Datum d : computedTrain) {
            if (d.label.equals("PER")) {
                countPer++;
            }
            if (d.label.equals("LOC")) {
                countLoc++;
            }
            if (d.label.equals("ORG")) {
                countOrg++;
            }
            if (d.label.equals("O")) {
                countO++;
            }
        }
        double priorPer = Math.log(countPer / computedTrain.size());
        double priorLoc = Math.log(countLoc / computedTrain.size());
        double priorOrg = Math.log(countOrg / computedTrain.size());
        double priorO = Math.log(countO / computedTrain.size());


        for (Datum d : computedTest) {

            double pPer = priorPer, pLoc = priorLoc, pO = priorO,pOrg=priorOrg;
//             System.out.println("PriorB:"+pB);
//                System.out.println("PriorI:"+pI);
//                System.out.println("PriorO:"+pO);
            for (int i = 0; i < d.features.length; i++) {
                Feature f1 = d.features[i];
//                System.out.println("FeatureNamef1:"+f1.featureName);
                double cPer = 0, cLoc = 0, cO = 0,cOrg=0;
                double probPer=0.0;
                double probLoc=0.0;
                double probO=0.0;
                double probOrg=0.0;

                for (int j=0;j<computedTrain.size();j++) {
                    probPer+=1.0/((computedTrain.size())*(countPer+computedTrain.size()));
                    probLoc+=1.0/((computedTrain.size())*(countLoc+computedTrain.size()));
                    probOrg+=1.0/((computedTrain.size())*(countOrg+computedTrain.size()));
                    probO+=1.0/((computedTrain.size())*(countO+computedTrain.size()));
                    Datum datum=computedTrain.get(j);
                    Feature f2 = datum.features[i];
                    //                      System.out.println("FeatureNamef2:"+f2.featureName);
                    switch (f1.featureName) {
                        case "currentWord":
                        case "prevWord":
                        case "prev2Word":
                        case "next2Word":
                        case "nextWord":
                        case "lowercaseCurrentWord":
                        case "lowercasePrevWord":
                        case "lowercaseNextWord":
                        case "wordPairPC":
                        case "wordPairCN":
                        case "prefixCWord":
                        case "prefixPWord":
                        case "prefixP2Word":
                        case "tag":
                        case "pTag":
                        case "p2Tag":
                        case "nTag":
                        case "n2Tag":
                        case "wordPairPN":
                            if (f1.stringValue.equals(f2.stringValue)) {
                                switch (datum.label) {
                                    case "PER":
                                        //                                    cB++;
                                        //                                    probB+=1/(countB+computedTrain.size());
                                        //                                    System.out.println(1.0/(countB));
                                        probPer+=
                                                1.0/(countPer+countPer/(countPer+computedTrain.size()-1));


                                        break;
                                    case "LOC":
                                        //                                    cI++;
                                        probLoc+=1.0/(countLoc+countLoc/(countLoc+computedTrain.size()-1));


                                        break;
                                    case "ORG":
                                        //                                    cI++;
                                        probLoc+=1.0/(countOrg+countOrg/(countOrg+computedTrain.size()-1));


                                        break;
                                    case "O":
                                        //                                    cO++;
                                        probO+=1.0/(countO+countO/(countO+computedTrain.size()-1));


                                        break;
                                }
                            }
                            break;
                        case "initUpcaseCurrentWord":
                        case "initUpcasePrevWord":
                        case "initUpcasePrev2Word":
                        case "initUpcaseNextWord":
                        case "initUpcaseNext2Word":
                        case "allCapCurrentWord":
                        case "allCapPrevWord":
                        case "allCapPrev2Word":
                        case "allCapNextWord":
                        case "allCapNext2Word":
                        case "letterAndDigitCWord":
                        case "letterAndDigitPWord":

                        case "letterAndDigitNWord":
                        case "letterAndDigitN2Word":
                        case "isPunctuationPWord":
                        case "isPunctuationP2Word":
                        case "isPunctuationNWord":
                        case "isPunctuationN2Word":
                        case "isFirstSentenceWord":
                        case "isFirstSentencePWord":
                        case "isFirstSentenceP2Word":
                        case "isInCountryList":
                        case "isInCountryListPWord":
                        case "isInCountryListP2Word":
                        case "isInCountryListNWord":
                        case "isInCountryListN2Word":
                        case "isInVnFirstName":
                        case "isInVnFirstNamePWord":
                        case "isInVnFirstNameP2Word":
                        case "isInVnFirstNameNWord":
                        case "isInVnFirstNameN2Word":
                        case "isInVnLastName":
                        case "isInVnLastNamePWord":
                        case "isInVnLastNameP2Word":
                        case "isInVnLastNameN2Word":
                        case "isInVnLastNameNWord":
                        case "inLocDict":
                        case "inLocDictPWord":
                        case "inLocDictP2Word":
                        case "inLocDictNWord":
                        case "inLocDictN2Word":
                        case "vnTimeMarkers":
                        case "vnTimeMarkersPWord":
                        case "vnTimeMarkersP2Word":
                        case "vnTimeMarkersNWord":
                        case "vnTimeMarkersN2Word":
                        case "locIndicateNoun":
                        case "locIndicateNounPWord":
                        case "locIndicateNounP2Word":
                        case "locIndicateNounNWord":
                        case "locIndicateNounN2Word":
                        case "orgIndicateNounN2Word":
                        case "orgIndicateNounP2Word":
                        case "orgIndicateNounPWord":
                        case "orgIndicateNounNWord":
                        case "orgIndicateNoun":
                        case "isBracket":
                        case "isBracketPWord":
                        case "isBracketP2Word":
                        case "isBracketN2Word":
                        case "isBracketNWord":
                            if (f1.intValue == f2.intValue) {
                                switch (datum.label) {
                                    case "PER":
//                                    cB++;
                                        probPer+=1.0/(countPer+countPer/(countPer+computedTrain.size()-1));


                                        break;
                                    case "LOC":
//                                    cI++;
                                        probLoc+=1.0/(countLoc+countLoc/(countLoc+computedTrain.size()-1));


                                        break;
                                    case "ORG":
//                                    cI++;
                                        probOrg+=1.0/(countOrg+countOrg/(countOrg+computedTrain.size()-1));


                                        break;
                                    case "O":
//                                    cO++;
                                        probO+=1.0/(countO+countO/(countO+computedTrain.size()-1));


                                        break;
                                }
                            }
                            break;
                    }

                }
//                System.out.println("ProbB:"+probB);
//                System.out.println("ProbI:"+probI);
//                System.out.println("ProbO:"+probO);
                pPer+=Math.log(probPer);
                pLoc+=Math.log(probLoc);
                pOrg+=Math.log(probOrg);
                pO+=Math.log(probO);


            }
            System.out.println("PPer:"+pPer);
            System.out.println("PLoc:"+pLoc);
            System.out.println("POrg:"+pOrg);
            System.out.println("PO:"+pO);
//                double max=pB;
            double max=pPer>pLoc?pPer:pLoc;
            max=max>pOrg?max:pOrg;
            max=max>pO?max:pO;
            if(max==pPer) d.predictedLabel="PER";
            if(max==pLoc) d.predictedLabel="LOC";
            if(max==pOrg) d.predictedLabel="ORG";
            if(max==pO) d.predictedLabel="O";
            System.out.println(d.word+"~"+d.predictedLabel+"~"+d.label);

        }
        KNNNER knn=new KNNNER();
        return knn.computeFscore(computedTest);

    }
}