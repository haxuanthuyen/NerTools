package com.hust.soict.nlp.algorithm.knn.ner;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Luan
 */
class SimilarityFactory {

    static double computeSimilarity(Feature[] fTest, Feature[] fTrain) {
        double score=0.0;

        for(int i=0;i<fTest.length;i++)
        {
            Feature f1=fTest[i];
            Feature f2=fTrain[i];
            score+=computeScore(f1,f2);

        }
        return score;

    }

    public static double computeScore(Feature f1, Feature f2)

    {
        double score=0.0;
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
            case "postfixCWord":
            case "postfixPWord":
            case "postfixNWord":
            case "wordPairPN":

                if(f1.stringValue.equals(f2.stringValue))
                    score=1;
                else score=0;
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
                if(f1.intValue==f2.intValue) score=1;
                else score=0;
                break;
        }



        return score;
    }

}
